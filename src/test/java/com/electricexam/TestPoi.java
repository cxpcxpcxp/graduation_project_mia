//package com.electricexam;
//
//import org.apache.poi.POIXMLDocument;
//import org.apache.poi.POIXMLTextExtractor;
//import org.apache.poi.hwpf.extractor.WordExtractor;
//import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
//
//import java.io.FileInputStream;
//
///**
// *  .doc .docx 文档测试类
// *
// */
//public class TestPoi {
//
//
//	public static void main(String[] args) {
//		TestPoi tp = new TestPoi();
//		//.docx和doc文件的读取
////		String content = tp.readWord("D:/电力安规考试题答案.doc");
//		String content = tp.readWord("D:/电力安规考试题答案 - 副本.doc");
//		String[] splits = content.split("Split");
//		int i=1;
//		for (String split : splits) {
//			System.out.println("第"+(i++)+"条-->"+split);
//		}
////		System.out.println("content====" + content);
//	}
//
//	/**
//	 * 读取word文件内容
//	 *
//	 * @param path
//	 * @return buffer
//	 */
//	public String readWord(String path) {
//		String buffer = "";
//		try {
//			if (path.endsWith(".doc")) {
//				FileInputStream is = new FileInputStream(path);
//				WordExtractor ex = new WordExtractor(is);
//				buffer = ex.getText();
//				is.close();
//			} else if (path.endsWith("docx")) {
//				OPCPackage opcPackage = POIXMLDocument.openPackage(path);
//				POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
//				buffer = extractor.getText();
//				opcPackage.close();
//			} else {
//				System.out.println("此文件不是word文件！");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return buffer;
//	}
//}