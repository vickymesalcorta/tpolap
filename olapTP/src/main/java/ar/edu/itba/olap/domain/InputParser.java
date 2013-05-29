package ar.edu.itba.olap.domain;

import java.io.File;
import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class InputParser {

	public static void main(String[] args) {
        InputParser ip = new InputParser();
        MultiDim multidim = ip.getMultiDim(new File("input.xml"));
		List<String> columns = multidim.getMultiDimNames();
		System.out.println("Columns:"+'\n');
		for(String s: columns){
			System.out.println(s + '\n');
		}
		//System.out.println(multidim);
        
        Document doc = InputParser.getDocumentFrom(multidim, null, "tableName" );
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
            System.out.println(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public static org.w3c.dom.Document loadXMLFrom(String xml)
			throws org.xml.sax.SAXException, java.io.IOException {
		return loadXMLFrom(new java.io.ByteArrayInputStream(xml.getBytes()));
	}

	public static org.w3c.dom.Document loadXMLFrom(java.io.InputStream is)
			throws org.xml.sax.SAXException, java.io.IOException {
		javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory
				.newInstance();
		factory.setNamespaceAware(true);
		javax.xml.parsers.DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (javax.xml.parsers.ParserConfigurationException ex) {
		}
		org.w3c.dom.Document doc = builder.parse(is);
		is.close();
		return doc;
	}

	public MultiDim getMultiDim(File input) {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc;
		MultiDim multidim = new MultiDim();
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(input);

			Node root = doc.getElementsByTagName("multidim").item(0);
			NodeList children = root.getChildNodes();

			for (int i = 0; i < children.getLength(); i++) {
				Node first = children.item(i);
				// En child esta el nodo dimension o cubo
				if (first.getNodeType() == Node.ELEMENT_NODE
						&& first.getNodeName().equals("dimension")) {
					Dimension dim = this.getDimension(first);
					multidim.addDimension(dim);
				} else if (first.getNodeType() == Node.ELEMENT_NODE
						&& first.getNodeName().equals("cubo")) {
					Cubo cubo = this.getCubo(first);
					multidim.addCubo(cubo);
				}
			}
			
			for(Cubo cubo : multidim.getCubos()){
                List<Dimension> dims = multidim.getDimensions();
                for(DimensionUsage dimUsage : cubo.getDimensionUsage()){
                    dimUsage.setDimension(this.getDimension(dimUsage.getPtr(), dims));
                }
            }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return multidim;

	}

	public Dimension getDimension(Node node) {
		Dimension dim = new Dimension(node.getAttributes().getNamedItem("name")
				.getNodeValue());
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node child = node.getChildNodes().item(i);
			// Aqui voy a tener un elemento
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				if (child.getNodeName().equals("level")) {
					Level level = this.getLevel(child, dim.getName());
					dim.addLevel(level);
				} else if (child.getNodeName().equals("hierarchy")) {
					Hierachy hierachy = this.getHierachy(child);
					dim.addHierachy(hierachy);
				}

			}
		}

		return dim;
	}

	public Level getLevel(Node node, String dimName) {
		Level level;
		Node nodeName = node.getAttributes().getNamedItem("name");
		Node nodePos = node.getAttributes().getNamedItem("pos");
		if (nodeName != null && nodePos != null) {
			level = new Level(nodeName.getNodeValue(), nodePos.getNodeValue());
		} else {
			// Nivel de dimension, no tiene posicion y adopta el nombre de la
			// dim
			level = new Level(dimName, 0);
		}
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node child = node.getChildNodes().item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE
					&& child.getNodeName().equals("property")) {
				Property property = this.getProperty(child);
				level.addProperty(property);
			}
		}

		return level;
	}

	public Hierachy getHierachy(Node node) {
		Hierachy hierachy;
		Node nodeName = node.getAttributes().getNamedItem("name");
		if (nodeName != null) {
			hierachy = new Hierachy(nodeName.getNodeValue());
		} else {
			hierachy = new Hierachy(null);
		}
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node child = node.getChildNodes().item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE
					&& child.getNodeName().equals("level")) {
				Level level = this.getLevel(child, null);
				hierachy.addLevel(level);
			}
		}

		return hierachy;
	}

	public Property getProperty(Node node) {
		String type = null;
		boolean id = false;
		String name = node.getFirstChild().getNodeValue().trim();
		Node aux = node.getAttributes().getNamedItem("type");
		if (aux != null) {
			type = aux.getNodeValue();
		}
		aux = node.getAttributes().getNamedItem("ID");
		if (aux != null) {
			id = aux.getNodeValue().equals("true");
		}
		Property property = new Property(name, type, id);

		return property;
	}

	public Cubo getCubo(Node node) {
		Cubo cubo;
		Node nodeName = node.getAttributes().getNamedItem("name");
		if (nodeName != null) {
			cubo = new Cubo(nodeName.getNodeValue());
		} else {
			cubo = new Cubo(null);
		}
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			Node child = node.getChildNodes().item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE
					&& child.getNodeName().equals("measure")) {
				Measure measure = this.getMeasure(child);
				cubo.addMeasure(measure);
			} else if (child.getNodeType() == Node.ELEMENT_NODE
					&& child.getNodeName().equals("dimension")) {
				DimensionUsage dimensionUsage = this.getDimensionUsage(child);
				cubo.addDimensionUsage(dimensionUsage);
			}
		}

		return cubo;
	}

	public Measure getMeasure(Node node) {
		String name = null;
		String type = null;
		String agg = null;
		Node nodeName = node.getAttributes().getNamedItem("name");
		Node nodeType = node.getAttributes().getNamedItem("type");
		Node nodeAgg = node.getAttributes().getNamedItem("agg");
		if (nodeName != null) {
			name = nodeName.getNodeValue();
		}
		if (nodeType != null) {
			type = nodeType.getNodeValue();
		}
		if (nodeAgg != null) {
			agg = nodeAgg.getNodeValue();
		}
		Measure measure = new Measure(name, type, agg);
		return measure;
	}

	public DimensionUsage getDimensionUsage(Node node) {
		String name = null;
		String ptr = null;
		Node nodeName = node.getAttributes().getNamedItem("name");
		Node nodePtr = node.getAttributes().getNamedItem("ptr");
		if (nodeName != null) {
			name = nodeName.getNodeValue();
		}
		if (nodePtr != null) {
			ptr = nodePtr.getNodeValue();
		}
		DimensionUsage du = new DimensionUsage(name, ptr);
		return du;
	}
	
	private static Document getDocumentFrom(MultiDim multidim, MultiDimToTablesDictionary dict, String tableName) {
        try {
            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().newDocument();
            Element schema = document.createElement("Schema");
            schema.setAttribute("name", tableName);
            for(Cubo cubo: multidim.getCubos()){
                Element cuboElement = document.createElement("Cube");
                cuboElement.setAttribute("name", cubo.getName());
                List<DimensionUsage> dimUsages = cubo.getDimensionUsage();
                for(int i = 0; i < dimUsages.size(); i++){
                    Dimension dim = dimUsages.get(i).getDimension();
                    Element dimElement = document.createElement(dim.getName());
                    dimElement.setAttribute("name", dim.getName());
                }
                schema.appendChild(cuboElement);
            }
            document.appendChild(schema);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	private Dimension getDimension(String ptr, List<Dimension> dims) {
        for(Dimension dim : dims){
            if(dim.getName().compareToIgnoreCase(ptr) == 0){
                return dim;
            }
        }
        throw new IllegalArgumentException();
    }
}
