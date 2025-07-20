package fr.funecirce.tilemapMaker.events;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.funecirce.tilemapMaker.TilemapProject;

public class Exporter {

	@SuppressWarnings("unused")
	public static void exportTilemapAsJson(TilemapProject tilemapProject) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNodeFactory factory = JsonNodeFactory.instance;
		
		ObjectNode root = factory.objectNode();
		
		String mapName = tilemapProject.getName();
		int rows = tilemapProject.getRowNumber();
		int columns = tilemapProject.getColumnNumber();
		int[][] tilemap = tilemapProject.getIdTilemap();
		
		JsonNode nameNode = root.set("name", factory.textNode(mapName));
		ObjectNode dimensionNode = factory.objectNode();
		dimensionNode.set("rows", factory.numberNode(rows));
		dimensionNode.set("columns", factory.numberNode(columns));
		root.set("dimension", dimensionNode);
		ArrayNode mapArrayNode = factory.arrayNode();
		for (int i = 0; i < tilemap.length; i++) {
			ArrayNode mapInsideNode = factory.arrayNode();
			for (int k = 0; k < tilemap[i].length; k++) {
				mapInsideNode.add(tilemap[i][k]);
			}
			mapArrayNode.add(mapInsideNode);
		}
		root.set("map", mapArrayNode);
		root.set("game_objects", factory.arrayNode());
		root.set("loading_zone", factory.arrayNode());
		ObjectNode  playerStartNode = factory.objectNode();
		playerStartNode.set("rows", factory.numberNode(0));
		playerStartNode.set("columns", factory.numberNode(0));
		root.set("player_start", playerStartNode);
		try {
			mapper.writer().withDefaultPrettyPrinter().writeValue(new FileOutputStream(mapName+".json"), root);
			System.out.println("Sucessfully exported as "+ mapName+".json");
		} catch (StreamWriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
