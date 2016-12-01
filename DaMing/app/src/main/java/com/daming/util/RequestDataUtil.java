package com.daming.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

import com.daming.entity.Anqll;
import com.daming.entity.Chanpjswj;
import com.daming.entity.Chanpjyjl;
import com.daming.entity.Chanplscl;
import com.daming.entity.Chanplszl;
import com.daming.entity.Chanpzlss;
import com.daming.entity.ChanpzlssEx;
import com.daming.entity.Chanpzlssjl;
import com.daming.entity.Chanxcpxx;
import com.daming.entity.Chanxjxzb;
import com.daming.entity.Chanxpcxx;
import com.daming.entity.Chanxtjtj;
import com.daming.entity.Chanxxx;
import com.daming.entity.Chanxzlhj;
import com.daming.entity.Chanxzlssjl;
import com.daming.entity.ConfirmChanxdm;
import com.daming.entity.Cp;
import com.daming.entity.Cpxx;
import com.daming.entity.Djll;
import com.daming.entity.Fzr;
import com.daming.entity.GetPaic;
import com.daming.entity.GetPaicList;
import com.daming.entity.GetPaichj;
import com.daming.entity.JH;
import com.daming.entity.Jx;
import com.daming.entity.Jxzb;
import com.daming.entity.Ldjl;
import com.daming.entity.PaicList;
import com.daming.entity.Pccl;
import com.daming.entity.Pcxx;
import com.daming.entity.Px;
import com.daming.entity.Renydjll;
import com.daming.entity.Renyjx;
import com.daming.entity.Renyldjl;
import com.daming.entity.Renypx;
import com.daming.entity.Renyxx;
import com.daming.entity.Renyzl;
import com.daming.entity.Ryxx;
import com.daming.entity.Ryzl;
import com.daming.entity.Shouqjs;
import com.daming.entity.Tjtj;
import com.daming.entity.TongZi;
import com.daming.entity.TongZiRecord;
import com.daming.entity.TongZis;
import com.daming.entity.WC;
import com.daming.entity.Zlhj;
import com.daming.entity.Zlssjl;

public class RequestDataUtil {
	/**
	 * 调用参数
	 * 
	 * @param username
	 * @param password
	 * @param param
	 * @param method_name
	 * @param ip
	 * @return
	 */
	public static String callMethod(String username, String password,
			String param, String method_name, String ip) {

		String namespace = "urn:MySOAPWebServiceIntf-IMySOAPWebService";
		String end_point = "http://" + ip + "/soap/IMySOAPWebService";
		String soap_action = "urn:MySOAPWebServiceIntf-IMySOAPWebService#"
				+ method_name;

		// 指定命名空间和方法名
		SoapObject soap = new SoapObject(namespace, method_name);
		soap.addProperty("AParam", param);
		soap.addProperty("AUserName", username);
		soap.addProperty("APassword", password);
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER10);
		envelope.bodyOut = soap;
		// envelope.dotNet = false;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(soap);
		HttpTransportSE transport = new HttpTransportSE(end_point);
		try {
			// 调用WebService
			transport.call(soap_action, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获取返回的数据
		SoapObject object = envelope.bodyIn != null
				&& envelope.bodyIn instanceof SoapObject ? (SoapObject) envelope.bodyIn
				: null;
		if (object != null) {
			return object.toString();
		}
		return "";
	}

	/**
	 * 调用统一参数
	 * 
	 * @param username
	 * @param password
	 * @param param
	 * @param method
	 * @param ip
	 * @return
	 */
	public static String callCommonMethod(String username, String password,
			String param, String method, String ip) {

		String namespace = "urn:MySOAPWebServiceIntf-IMySOAPWebService";
		String end_point = "http://" + ip + "/soap/IMySOAPWebService";
		String soap_action = "urn:MySOAPWebServiceIntf-IMySOAPWebService#CommonService";
		// 指定命名空间和方法名
		SoapObject soap = new SoapObject(namespace, "CommonService");
		soap.addProperty("AParam", param);
		soap.addProperty("AUserName", username);
		soap.addProperty("APassword", password);
		soap.addProperty("AFuncName", method);
		// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER10);
		envelope.bodyOut = soap;
		// envelope.dotNet = false;
		// 等价于envelope.bodyOut = rpc;
		// envelope.setOutputSoapObject(soap);
		HttpTransportSE transport = new HttpTransportSE(end_point);
		try {
			// 调用WebService
			transport.call(soap_action, envelope);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获取返回的数据
		SoapObject object = envelope.bodyIn != null
				&& envelope.bodyIn instanceof SoapObject ? (SoapObject) envelope.bodyIn
				: null;
		if (object != null) {
			return object.toString();
		}
		return "";
	}

	public static String splieRequestXml(String xmlStr) {
		return xmlStr.substring(xmlStr.indexOf("<root>"),
				xmlStr.indexOf("</root>") + "</root>".length());
	}
	public static String splieRequestJson(String jsonStr) {
		return jsonStr.substring(jsonStr.indexOf("="),
				jsonStr.length()-3);
	}

	/**
	 * 产品详细信息
	 * 
	 * @param xmlStr
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<Chanxxx> parseGetChanxxxXml(String xmlStr)
			throws XmlPullParserException, IOException {
		List<Chanxxx> chanxxxList = null;
		Chanxxx chanxxx = null;
		List<JH> jhList = null;
		List<WC> wcList = null;
		int count = 0;
		JH jh = null;
		WC wc = null;
		XmlPullParser parser = Xml.newPullParser();

		parser.setInput(new StringReader(xmlStr));
		int event = parser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {

			switch (event) {

			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("data")) {
					chanxxxList = new ArrayList<Chanxxx>();
					chanxxx = new Chanxxx();
				} else if (parser.getName().equals("result")) {
					event = parser.next();
					chanxxx.setResult(parser.getText());
				} else if (parser.getName().equals("msg")) {
					event = parser.next();
					chanxxx.setMsg(parser.getText());
				} else if (parser.getName().equals("cxdm")) {
					event = parser.next();
					chanxxx.setCxdm(parser.getText());
				} else if (parser.getName().equals("cxmc")) {
					event = parser.next();
					chanxxx.setCxmc(parser.getText());
				} else if (parser.getName().equals("scfw")) {
					event = parser.next();
					chanxxx.setScfw(parser.getText());
				} else if (parser.getName().equals("JHS")) {
					event = parser.next();
					jhList = new ArrayList<JH>();
				} else if (parser.getName().equals("JH")) {
					event = parser.next();
					jh = new JH();
				} else if (parser.getName().equals("WCS")) {
					event = parser.next();
					wcList = new ArrayList<WC>();
				} else if (parser.getName().equals("WC")) {
					event = parser.next();
					wc = new WC();
				} else if (parser.getName().equals("scrq")) {
					event = parser.next();
					if (count == 0) {
						jh.setScrq(parser.getText());
					} else if (count == 1) {
						wc.setScrq(parser.getText());
					}
				} else if (parser.getName().equals("bcdm")) {
					event = parser.next();
					if (count == 0) {
						jh.setBcdm(parser.getText());
					} else if (count == 1) {
						wc.setBcdm(parser.getText());
					}
				} else if (parser.getName().equals("cpdm")) {
					event = parser.next();
					if (count == 0) {
						jh.setCpdm(parser.getText());
					} else if (count == 1) {
						wc.setCpdm(parser.getText());
					}
				} else if (parser.getName().equals("sl")) {
					event = parser.next();
					if (count == 0) {
						jh.setSl(parser.getText());
					} else if (count == 1) {
						wc.setSl(parser.getText());
					}
				} else if (parser.getName().equals("wcsl")) {
					event = parser.next();
					if (count == 0) {
						jh.setWcsl(parser.getText());
					} else if (count == 1) {
						wc.setWcsl(parser.getText());
					}
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("data")) {
					chanxxxList.add(chanxxx);
					chanxxx = null;
				}
				if (parser.getName().equals("JH")) {
					jhList.add(jh);
					jh = null;
				}
				if (parser.getName().equals("JHS")) {
					chanxxx.setJhList(jhList);
					count++;
					jhList = null;
				}
				if (parser.getName().equals("WC")) {
					wcList.add(wc);
					wc = null;
				}
				if (parser.getName().equals("WCS")) {
					chanxxx.setWcList(wcList);
					wcList = null;
				}
				break;
			}
			event = parser.next();
		}

		return chanxxxList;
	}

	/**
	 * 产品信息
	 * 
	 * @param xmlStr
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<GetPaic> parseGetPaicXml(String xmlStr)
			throws XmlPullParserException, IOException {
		List<GetPaic> getPaicList = new ArrayList<GetPaic>();
		GetPaic getPaic = null;

		Fzr fzr = null;
		List<Fzr> czgList = null;

		Cp cp = null;
		List<Cp> cpList = null;

		Pccl pccl = null;
		List<Pccl> pcclList = null;

		int count = 0;
		XmlPullParser parser = Xml.newPullParser();

		parser.setInput(new StringReader(xmlStr));
		int event = parser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {

			switch (event) {

			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:

				if (parser.getName().equals("data")) { // 判断开始标签元素是否是book
					getPaic = new GetPaic();
				} else if (parser.getName().equals("result")) {
					event = parser.next();// 让解析器指向name属性的值
					// 得到name标签的属性值，并设置beauty的name
					getPaic.setResult(parser.getText());
				} else if (parser.getName().equals("msg")) { // 判断开始标签元素是否是book
					event = parser.next();// 让解析器指向age属性的值
					// 得到age标签的属性值，并设置beauty的age
					getPaic.setMsg(parser.getText());
				} else if (parser.getName().equals("pcid")) { // 判断开始标签元素是否是book
					event = parser.next();// 让解析器指向age属性的值
					// 得到age标签的属性值，并设置beauty的age
					getPaic.setPcid(parser.getText());
				} 

				else if (parser.getName().equals("scrq")) { // 判断开始标签元素是否是book
					event = parser.next();// 让解析器指向age属性的值
					// 得到age标签的属性值，并设置beauty的age
					getPaic.setScrq(parser.getText());

				} else if (parser.getName().equals("bcdm")) { // 判断开始标签元素是否是book
					event = parser.next();// 让解析器指向age属性的值
					// 得到age标签的属性值，并设置beauty的age
					getPaic.setBcdm(parser.getText());
				} else if (parser.getName().equals("fzr")) { // 判断开始标签元素是否是book
					event = parser.next();// 让解析器指向age属性的值
					// 得到age标签的属性值，并设置beauty的age
					fzr = new Fzr();
				} else if (parser.getName().equals("jyy")) { // 判断开始标签元素是否是book
					event = parser.next();// 让解析器指向age属性的值
					// 得到age标签的属性值，并设置beauty的age
					fzr = new Fzr();
				} else if (parser.getName().equals("czgs")) { // 判断开始标签元素是否是book
					event = parser.next();// 让解析器指向age属性的值
					// 得到age标签的属性值，并设置beauty的age
					czgList = new ArrayList<Fzr>();
				} else if (parser.getName().equals("czg")) { // 判断开始标签元素是否是book
					event = parser.next();// 让解析器指向age属性的值
					// 得到age标签的属性值，并设置beauty的age
					fzr = new Fzr();
				} else if (parser.getName().equals("rydm")) { // 判断开始标签元素是否是book
					event = parser.next();// 让解析器指向age属性的值
					// 得到age标签的属性值，并设置beauty的age
					if (count == 0) {
						fzr.setRydm(parser.getText());
					} else if (count == 1) {
						fzr.setRydm(parser.getText());
					} else if (count == 2) {
						fzr.setRydm(parser.getText());
					}
				} else if (parser.getName().equals("xm")) { // 判断开始标签元素是否是book
					event = parser.next();// 让解析器指向age属性的值
					// 得到age标签的属性值，并设置beauty的age
					if (count == 0) {
						fzr.setXm(parser.getText());
					} else if (count == 1) {
						fzr.setXm(parser.getText());
					} else if (count == 2) {
						fzr.setXm(parser.getText());
					}
				} else if (parser.getName().equals("src")) { // 判断开始标签元素是否是book
					event = parser.next();// 让解析器指向age属性的值
					// 得到age标签的属性值，并设置beauty的age
					if (count == 0) {
						fzr.setSrc(parser.getText());
					} else if (count == 1) {
						fzr.setSrc(parser.getText());
					} else if (count == 2) {
						fzr.setSrc(parser.getText());
					}
				} else if (parser.getName().equals("cps")) {
					event = parser.next();
					cpList = new ArrayList<Cp>();
				} else if (parser.getName().equals("cp")) {
					event = parser.next();
					cp = new Cp();
				} else if (parser.getName().equals("cpgxid")) {
					event = parser.next();
					cp.setCpgxid(parser.getText());
				} else if (parser.getName().equals("cpdm")) {
					event = parser.next();
					cp.setCpdm(parser.getText());
				} else if (parser.getName().equals("cpmc")) {
					event = parser.next();
					cp.setCpmc(parser.getText());
				} else if (parser.getName().equals("ggxh")) {
					event = parser.next();
					cp.setGgxh(parser.getText());
				} else if (parser.getName().equals("gxfl")) {
					event = parser.next();
					cp.setGxfl(parser.getText());
				} else if (parser.getName().equals("scgx")) {
					event = parser.next();
					cp.setScgx(parser.getText());
				} else if (parser.getName().equals("jp")) {
					event = parser.next();
					cp.setJp(parser.getText());
				} else if (parser.getName().equals("rs")) {
					event = parser.next();
					cp.setRs(parser.getText());
				} else if (parser.getName().equals("yxcc")) {
					event = parser.next();
					cp.setYxcc(parser.getText());
				} else if (parser.getName().equals("pccls")) {
					event = parser.next();
					pcclList = new ArrayList<Pccl>();
				} else if (parser.getName().equals("pccl")) {
					event = parser.next();
					pccl = new Pccl();
				} else if (parser.getName().equals("pcclid")) {
					event = parser.next();
					pccl.setPcclid(parser.getText());
				} else if (parser.getName().equals("bcid")) {
					event = parser.next();
					pccl.setBcid(parser.getText());
				} else if (parser.getName().equals("bcsdmch")) {
					event = parser.next();
					pccl.setBcsdmch(parser.getText());
				} else if (parser.getName().equals("sdstart")) {
					event = parser.next();
					pccl.setSdstart(parser.getText());
				} else if (parser.getName().equals("sdend")) {
					event = parser.next();
					pccl.setSdend(parser.getText());
				} else if (parser.getName().equals("sec")) {
					event = parser.next();
					pccl.setSec(parser.getText());
				} else if (parser.getName().equals("sl")) {
					event = parser.next();
					pccl.setSl(parser.getText());
				} else if (parser.getName().equals("wcsl")) {
					event = parser.next();
					pccl.setWcsl(parser.getText());
				}

				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("fzr")) { // 判断结束标签元素是否是book
					getPaic.setFzr(fzr); // 将book添加到books集合
					fzr = null;
					count++;
				}
				if (parser.getName().equals("jyy")) {
					getPaic.setJyy(fzr); // 将book添加到books集合
					fzr = null;
					count++;
				}
				if (parser.getName().equals("czg")) {
					czgList.add(fzr); // 将book添加到books集合
					fzr = null;

				}
				if (parser.getName().equals("czgs")) {
					getPaic.setCztList(czgList); // 将book添加到books集合
					czgList = null;
				}
				if (parser.getName().equals("data")) {
					getPaic.setCount(count);
					getPaicList.add(getPaic); // 将book添加到books集合

				}
				if (parser.getName().equals("pccl")) {
					pcclList.add(pccl);
					pccl = null;
				}
				if (parser.getName().equals("pccls")) {
					cp.setPcclList(pcclList);
					pcclList = null;
				}
				if (parser.getName().equals("cp")) {
					cpList.add(cp);
					cp = null;
				}
				if (parser.getName().equals("cps")) {
					getPaic.setCpList(cpList);
					cpList = null;
				}

				break;
			}
			event = parser.next();
		}

		return getPaicList;
	}

	/**
	 * 产品列表信息
	 * 
	 * @param xmlStr
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static GetPaicList parseGetPaicListXml(String xmlStr)
			throws XmlPullParserException, IOException {

		GetPaicList getPaicList = null;
		List<PaicList> paicLists = null;
		PaicList paicList = null;
		XmlPullParser parser = Xml.newPullParser();

		parser.setInput(new StringReader(xmlStr));
		int event = parser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {

			switch (event) {

			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("data")) { // 判断开始标签元素是否是book
                          getPaicList = new GetPaicList();
				} else if (parser.getName().equals("result")) {
					event = parser.next();// 让解析器指向name属性的值
					// 得到name标签的属性值，并设置beauty的name
					getPaicList.setResult(parser.getText());
				} else if (parser.getName().equals("msg")) { // 判断开始标签元素是否是book
					event = parser.next();// 让解析器指向age属性的值
					// 得到age标签的属性值，并设置beauty的age
					getPaicList.setMsg(parser.getText());
				} else if (parser.getName().equals("recordcount")) { // 判断开始标签元素是否是book
					event = parser.next();// 让解析器指向age属性的值
					// 得到age标签的属性值，并设置beauty的age
					getPaicList.setRecordcount(parser.getText());
				} else if (parser.getName().equals("records")) { // 判断开始标签元素是否是book
					event = parser.next();// 让解析器指向age属性的值
					// 得到age标签的属性值，并设置beauty的age
					paicLists = new ArrayList<>();
				}

				else if (parser.getName().equals("record")) { // 判断开始标签元素是否是book
					event = parser.next();// 让解析器指向age属性的值
					// 得到age标签的属性值，并设置beauty的age
					paicList = new PaicList();

				} else if (parser.getName().equals("pcid")) { // 判断开始标签元素是否是book
					event = parser.next();// 让解析器指向age属性的值
					// 得到age标签的属性值，并设置beauty的age
					paicList.setPcid(parser.getText());
				} else if (parser.getName().equals("scrq")) { // 判断开始标签元素是否是book
					event = parser.next();// 让解析器指向age属性的值
					// 得到age标签的属性值，并设置beauty的age
					paicList.setScrq(parser.getText());
				} else if (parser.getName().equals("bcdm")) { // 判断开始标签元素是否是book
					event = parser.next();// 让解析器指向age属性的值
					// 得到age标签的属性值，并设置beauty的age
					paicList.setBcdm(parser.getText());
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("record")) { // 判断结束标签元素是否是book
					paicLists.add(paicList); // 将book添加到books集合
					paicList = null;
				}
				if (parser.getName().equals("records")) { // 判断结束标签元素是否是book
					getPaicList.setPaicLists(paicLists); // 将book添加到books集合
					paicLists = null;
				}

				break;
			}
			event = parser.next();
		}

		return getPaicList;
	}

	/**
	 * 开始呼叫
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static List<ConfirmChanxdm> parseConfirmXml(String xmlStr) {
		List<ConfirmChanxdm> confirmList = new ArrayList<ConfirmChanxdm>();
		ConfirmChanxdm confirmChanxdm = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						confirmChanxdm = new ConfirmChanxdm();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();// 让解析器指向name属性的值
						// 得到name标签的属性值，并设置beauty的name
						confirmChanxdm.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();// 让解析器指向age属性的值
						// 得到age标签的属性值，并设置beauty的age
						confirmChanxdm.setMsg(parser.getText());
					}
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("data")) { // 判断结束标签元素是否是book
						confirmList.add(confirmChanxdm); // 将book添加到books集合
						confirmChanxdm = null;
					}
					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return confirmList;
	}

	/**
	 * 呼叫记录
	 *
	 * @param xmlStr
	 * @return
	 */
	public static GetPaichj parseGetPaichjXml(String xmlStr) {
		List<String> confirmList = new ArrayList<String>();
		GetPaichj confirmChanxdm = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

					case XmlPullParser.START_DOCUMENT:
						break;
					case XmlPullParser.START_TAG:

						if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
							confirmChanxdm = new GetPaichj();
						} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
							event = parser.next();// 让解析器指向name属性的值
							// 得到name标签的属性值，并设置beauty的name
							confirmChanxdm.setResult(parser.getText());
						} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
							event = parser.next();// 让解析器指向age属性的值
							// 得到age标签的属性值，并设置beauty的age
							confirmChanxdm.setMsg(parser.getText());
						}
						else if (parser.getName().equals("hjms")) { // 判断开始标签元素是否是book
							event = parser.next();// 让解析器指向age属性的值
							// 得到age标签的属性值，并设置beauty的age
							if(!parser.getText().equals(" ")) {
								confirmList.add(parser.getText());
							//	Log.e("RequestDataUtil", parser.getText());
							}
						}
						break;
					case XmlPullParser.END_TAG:
						if (parser.getName().equals("data")) { // 判断结束标签元素是否是book
							confirmChanxdm.setHjmss(confirmList); // 将book添加到books集合
							//confirmChanxdm = null;
						}
						break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return confirmChanxdm;
	}
	/**
	 * 通知信息
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static TongZi parseTongZi(String xmlStr) {
		TongZi tongZi = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						tongZi = new TongZi();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						tongZi.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						tongZi.setMsg(parser.getText());
					} else if (parser.getName().equals("tzid")) { // 判断开始标签元素是否是book
						event = parser.next();
						tongZi.setTzid(parser.getText());
					} else if (parser.getName().equals("bt")) { // 判断开始标签元素是否是book
						event = parser.next();
						tongZi.setBt(parser.getText());
					} else if (parser.getName().equals("zw")) { // 判断开始标签元素是否是book
						event = parser.next();
						tongZi.setZw(parser.getText());
					} else if (parser.getName().equals("fbbm")) { // 判断开始标签元素是否是book
						event = parser.next();
						tongZi.setFbbm(parser.getText());
					} else if (parser.getName().equals("tzsj")) { // 判断开始标签元素是否是book
						event = parser.next();
						tongZi.setTzsj(parser.getText());
					}
					break;
				case XmlPullParser.END_TAG:

					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return tongZi;
	}

	/**
	 * 通知列表
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static TongZis parseTongZis(String xmlStr) {
		TongZis tongZis = null;
		List<TongZiRecord> tongZiRList = null;
		TongZiRecord tongZiRecord = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						tongZis = new TongZis();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						tongZis.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						tongZis.setMsg(parser.getText());
					} else if (parser.getName().equals("records")) { // 判断开始标签元素是否是book
						event = parser.next();
						tongZiRList = new ArrayList<TongZiRecord>();
					} else if (parser.getName().equals("record")) { // 判断开始标签元素是否是book
						event = parser.next();
						tongZiRecord = new TongZiRecord();
					} else if (parser.getName().equals("tzid")) { // 判断开始标签元素是否是book
						event = parser.next();
						tongZiRecord.setTzid(parser.getText());
					} else if (parser.getName().equals("bt")) { // 判断开始标签元素是否是book
						event = parser.next();
						tongZiRecord.setBt(parser.getText());
					} else if (parser.getName().equals("fbbm")) { // 判断开始标签元素是否是book
						event = parser.next();
						tongZiRecord.setFbbm(parser.getText());
					} else if (parser.getName().equals("yd")) { // 判断开始标签元素是否是book
						event = parser.next();
						tongZiRecord.setYd(parser.getText());
					} else if (parser.getName().equals("tzsj")) { // 判断开始标签元素是否是book
						event = parser.next();
						tongZiRecord.setTzsj(parser.getText());
					}
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("record")) { // 判断结束标签元素是否是book
						tongZiRList.add(tongZiRecord);
						tongZiRecord = null;
					}
					if (parser.getName().equals("records")) { // 判断结束标签元素是否是book
						tongZis.setTongZiRecordList(tongZiRList);
					}
					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return tongZis;
	}

	/**
	 * 1.12 获取产线生产产品信息(产线基本信息)
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Chanxcpxx parseChanxcpxx(String xmlStr) {
		Cpxx cpxx = null;
		List<Cpxx> cpxxs = null;
		Chanxcpxx chanxcpxx = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						chanxcpxx = new Chanxcpxx();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						chanxcpxx.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						chanxcpxx.setMsg(parser.getText());
					} else if (parser.getName().equals("cpxxs")) { // 判断开始标签元素是否是book
						event = parser.next();
						cpxxs = new ArrayList<Cpxx>();
					} else if (parser.getName().equals("cpxx")) { // 判断开始标签元素是否是book
						event = parser.next();
						cpxx = new Cpxx();
					} else if (parser.getName().equals("cpdm")) { // 判断开始标签元素是否是book
						event = parser.next();
						cpxx.setCpdm(parser.getText());
					}
					else if (parser.getName().equals("cpmc")) { // 判断开始标签元素是否是book
						event = parser.next();
						cpxx.setCpmc(parser.getText());
					} else if (parser.getName().equals("ggxh")) { // 判断开始标签元素是否是book
						event = parser.next();
						cpxx.setGgxh(parser.getText());
					} else if (parser.getName().equals("gxfl")) { // 判断开始标签元素是否是book
						event = parser.next();
						cpxx.setGxfl(parser.getText());
					} 
					else if (parser.getName().equals("scgx")) { // 判断开始标签元素是否是book
						event = parser.next();
						cpxx.setScgx(parser.getText());
					} 
					else if (parser.getName().equals("jp")) { // 判断开始标签元素是否是book
						event = parser.next();
						cpxx.setJp(parser.getText());
					} else if (parser.getName().equals("rs")) { // 判断开始标签元素是否是book
						event = parser.next();
						cpxx.setRs(parser.getText());
					}
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("cpxx")) { // 判断结束标签元素是否是book

						cpxxs.add(cpxx);
						cpxx = null;
					}
					if (parser.getName().equals("cpxxs")) { // 判断结束标签元素是否是book

						chanxcpxx.setCpxxs(cpxxs);
						cpxxs = null;
					}
					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return chanxcpxx;
	}

	/**
	 * 1.13 获取产线排产信息(生产排程)
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Chanxpcxx parseChanxpcxx(String xmlStr) {
		Pcxx pcxx = null;
		List<Pcxx> pcxxs = null;
		Chanxpcxx chanxpcxx = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						chanxpcxx = new Chanxpcxx();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						chanxpcxx.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						chanxpcxx.setMsg(parser.getText());
					} else if (parser.getName().equals("pcxxs")) { // 判断开始标签元素是否是book
						event = parser.next();
						pcxxs = new ArrayList<Pcxx>();
					} else if (parser.getName().equals("pcxx")) { // 判断开始标签元素是否是book
						event = parser.next();
						pcxx = new Pcxx();
					} else if (parser.getName().equals("pcid")) { // 判断开始标签元素是否是book
						event = parser.next();
						pcxx.setPcid(parser.getText());
					} else if (parser.getName().equals("bcdm")) { // 判断开始标签元素是否是book
						event = parser.next();
						pcxx.setBcdm(parser.getText());
					} else if (parser.getName().equals("cpdm")) { // 判断开始标签元素是否是book
						event = parser.next();
						pcxx.setCpdm(parser.getText());
					}
					else if (parser.getName().equals("scrq")) { // 判断开始标签元素是否是book
						event = parser.next();
						pcxx.setScrq(parser.getText());
					}else if (parser.getName().equals("cpmc")) { // 判断开始标签元素是否是book
						event = parser.next();
						pcxx.setCpmc(parser.getText());
					} else if (parser.getName().equals("ggxh")) { // 判断开始标签元素是否是book
						event = parser.next();
						pcxx.setGgxh(parser.getText());
					} else if (parser.getName().equals("gxfl")) { // 判断开始标签元素是否是book
						event = parser.next();
						pcxx.setGxfl(parser.getText());
					}
					else if (parser.getName().equals("scgx")) { // 判断开始标签元素是否是book
						event = parser.next();
						pcxx.setScgx(parser.getText());
					}
					else if (parser.getName().equals("sl")) { // 判断开始标签元素是否是book
						event = parser.next();
						pcxx.setSl(parser.getText());
					} else if (parser.getName().equals("fzr")) { // 判断开始标签元素是否是book
						event = parser.next();
						pcxx.setFzr(parser.getText());
					} else if (parser.getName().equals("jyy")) { // 判断开始标签元素是否是book
						event = parser.next();
						pcxx.setJyy(parser.getText());
					} else if (parser.getName().equals("czy1")) { // 判断开始标签元素是否是book
						event = parser.next();
						pcxx.setCzy1(parser.getText());
					} else if (parser.getName().equals("czy2")) { // 判断开始标签元素是否是book
						event = parser.next();
						pcxx.setCzy2(parser.getText());
					} else if (parser.getName().equals("czy3")) { // 判断开始标签元素是否是book
						event = parser.next();
						pcxx.setCzy3(parser.getText());
					} else if (parser.getName().equals("czy4")) { // 判断开始标签元素是否是book
						event = parser.next();
						pcxx.setCzy4(parser.getText());
					}
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("pcxx")) { // 判断结束标签元素是否是book
						pcxxs.add(pcxx);
						pcxx = null;
					}
					if (parser.getName().equals("pcxxs")) { // 判断结束标签元素是否是book
						chanxpcxx.setPcxxs(pcxxs);
						pcxxs = null;
					}
					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return chanxpcxx;
	}

	/**
	 * 1.14 获取产线绩效指标
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Chanxjxzb parseChanxjxzb(String xmlStr) {
		Jxzb jxzb = null;
		List<Jxzb> jxzbs = null;
		Chanxjxzb chanxjxzb = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						chanxjxzb = new Chanxjxzb();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						chanxjxzb.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						chanxjxzb.setMsg(parser.getText());
					}
					else if (parser.getName().equals("cxdcl")) { // 判断开始标签元素是否是book
						event = parser.next();
						chanxjxzb.setCxdcl(parser.getText());
					}
					else if (parser.getName().equals("cxhgl")) { // 判断开始标签元素是否是book
						event = parser.next();
						chanxjxzb.setCxhgl(parser.getText());
					}
					else if (parser.getName().equals("jxzbs")) { // 判断开始标签元素是否是book
						event = parser.next();
						jxzbs = new ArrayList<Jxzb>();
					} else if (parser.getName().equals("jxzb")) { // 判断开始标签元素是否是book
						event = parser.next();
						jxzb = new Jxzb();
					} else if (parser.getName().equals("scrq")) { // 判断开始标签元素是否是book
						event = parser.next();
						jxzb.setScrq(parser.getText());
					} else if (parser.getName().equals("jhwcl")) { // 判断开始标签元素是否是book
						event = parser.next();
						jxzb.setJhwcl(parser.getText());
					} else if (parser.getName().equals("bcdcl")) { // 判断开始标签元素是否是book
						event = parser.next();
						jxzb.setBcdcl(parser.getText());
					} else if (parser.getName().equals("hgl")) { // 判断开始标签元素是否是book
						event = parser.next();
						jxzb.setHgl(parser.getText());
					} else if (parser.getName().equals("gfl")) { // 判断开始标签元素是否是book
						event = parser.next();
						jxzb.setGfl(parser.getText());
					} else if (parser.getName().equals("lfl")) { // 判断开始标签元素是否是book
						event = parser.next();
						jxzb.setLfl(parser.getText());
					}

					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("jxzb")) { // 判断结束标签元素是否是book
						jxzbs.add(jxzb);
						jxzb = null;
					}
					if (parser.getName().equals("jxzbs")) { // 判断结束标签元素是否是book
						chanxjxzb.setJxzbs(jxzbs);
						jxzbs = null;
					}
					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return chanxjxzb;
	}

	/**
	 * 1.15 获取产线质量呼叫
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Chanxzlhj parseChanxzlhj(String xmlStr) {
		Zlhj zlhj = null;
		List<Zlhj> zlhjs = null;
		Chanxzlhj chanxzlhj = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						chanxzlhj = new Chanxzlhj();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						chanxzlhj.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						chanxzlhj.setMsg(parser.getText());
					} else if (parser.getName().equals("zlhjs")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlhjs = new ArrayList<Zlhj>();

					} else if (parser.getName().equals("zlhj")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlhj = new Zlhj();

					} else if (parser.getName().equals("scrq")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlhj.setScrq(parser.getText());
					} else if (parser.getName().equals("cpdm")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlhj.setCpdm(parser.getText());
					} else if (parser.getName().equals("qxlx")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlhj.setQxlx(parser.getText());
					} else if (parser.getName().equals("wtms")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlhj.setWtmx(parser.getText());
					} else if (parser.getName().equals("hjclzt")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlhj.setHjclzt(parser.getText());
					}

					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("zlhj")) { // 判断结束标签元素是否是book
						zlhjs.add(zlhj);
						zlhj = null;
					}
					if (parser.getName().equals("zlhjs")) { // 判断结束标签元素是否是book
						chanxzlhj.setZlhjs(zlhjs);
						zlhjs = null;
					}
					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return chanxzlhj;
	}

	/**
	 * 1.16 获取产线停机统计
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Chanxtjtj parseChanxtjtj(String xmlStr) {
		Tjtj tjtj = null;
		List<Tjtj> tjtjs = null;
		Chanxtjtj chanxtjtj = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						chanxtjtj = new Chanxtjtj();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						chanxtjtj.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						chanxtjtj.setMsg(parser.getText());
					} 
					else if (parser.getName().equals("src")) { // 判断开始标签元素是否是book
						event = parser.next();
						chanxtjtj.setSrc(parser.getText());
					} 
					else if (parser.getName().equals("tjtjs")) { // 判断开始标签元素是否是book
						event = parser.next();
						tjtjs = new ArrayList<Tjtj>();

					}

					else if (parser.getName().equals("tjtj")) { // 判断开始标签元素是否是book
						event = parser.next();
						tjtj = new Tjtj();

					} else if (parser.getName().equals("yclx")) { // 判断开始标签元素是否是book
						event = parser.next();
						tjtj.setYclx(parser.getText());
					} else if (parser.getName().equals("tjsj")) { // 判断开始标签元素是否是book
						event = parser.next();
						tjtj.setTjsj(parser.getText());
					}

					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("tjtj")) { // 判断结束标签元素是否是book
						tjtjs.add(tjtj);
						tjtj = null;
					}
					if (parser.getName().equals("tjtjs")) { // 判断结束标签元素是否是book
						chanxtjtj.setTjtjs(tjtjs);
						tjtjs = null;
					}
					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return chanxtjtj;
	}

	/**
	 * 1.17 获取人员信息
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Renyxx parseRenyxx(String xmlStr) {
		Ryxx ryxx = null;
		Renyxx renyxx = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						renyxx = new Renyxx();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						renyxx.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						renyxx.setMsg(parser.getText());
					} else if (parser.getName().equals("ryxx")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryxx = new Ryxx();
					} else if (parser.getName().equals("rydm")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryxx.setRydm(parser.getText());
					} else if (parser.getName().equals("xm")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryxx.setXm(parser.getText());
					} else if (parser.getName().equals("sfzh")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryxx.setSfzh(parser.getText());
					} else if (parser.getName().equals("rylb")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryxx.setRylb(parser.getText());
					} else if (parser.getName().equals("csrq")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryxx.setCsrq(parser.getText());
					} else if (parser.getName().equals("src")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryxx.setSrc(parser.getText());
					}

					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("ryxx")) { // 判断结束标签元素是否是book
						renyxx.setRyxx(ryxx);
						ryxx = null;
					}

					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return renyxx;
	}

	/**
	 * 1.18 获取人员等级履历
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Renydjll parseRenydjll(String xmlStr) {
		Djll djll = null;
		List<Djll> djlls = null;
		Renydjll renydjll = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						renydjll = new Renydjll();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						renydjll.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						renydjll.setMsg(parser.getText());
					} else if (parser.getName().equals("djlls")) { // 判断开始标签元素是否是book
						event = parser.next();
						djlls = new ArrayList<Djll>();
					} else if (parser.getName().equals("djll")) { // 判断开始标签元素是否是book
						event = parser.next();
						djll = new Djll();
					} else if (parser.getName().equals("pdrq")) { // 判断开始标签元素是否是book
						event = parser.next();
						djll.setPdrq(parser.getText());
					} else if (parser.getName().equals("jndj")) { // 判断开始标签元素是否是book
						event = parser.next();
						djll.setJndj(parser.getText());
					} else if (parser.getName().equals("shengxrq")) { // 判断开始标签元素是否是book
						event = parser.next();
						djll.setShengxrq(parser.getText());
					} else if (parser.getName().equals("shixrq")) { // 判断开始标签元素是否是book
						event = parser.next();
						djll.setShixrq(parser.getText());
					} else if (parser.getName().equals("zt")) { // 判断开始标签元素是否是book
						event = parser.next();
						djll.setZt(parser.getText());
					}

					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("djlls")) { // 判断结束标签元素是否是book
						renydjll.setDjlls(djlls);
						djlls = null;
					}
					if (parser.getName().equals("djll")) { // 判断结束标签元素是否是book
						djlls.add(djll);
						djll = null;
					}
					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return renydjll;
	}

	/**
	 * 1.19 获取人员培训
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Renypx parseRenypx(String xmlStr) {
		Px px = null;
		List<Px> pxs = null;
		Renypx renypx = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						renypx = new Renypx();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						renypx.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						renypx.setMsg(parser.getText());
					} else if (parser.getName().equals("pxs")) { // 判断开始标签元素是否是book
						event = parser.next();
						pxs = new ArrayList<Px>();
					} else if (parser.getName().equals("px")) { // 判断开始标签元素是否是book
						event = parser.next();
						px = new Px();
					} else if (parser.getName().equals("pxrq")) { // 判断开始标签元素是否是book
						event = parser.next();
						px.setPxrq(parser.getText());
					} else if (parser.getName().equals("pxnr")) { // 判断开始标签元素是否是book
						event = parser.next();
						px.setPxnr(parser.getText());
					} else if (parser.getName().equals("pxjs")) { // 判断开始标签元素是否是book
						event = parser.next();
						px.setPxjs(parser.getText());
					} else if (parser.getName().equals("pxjg")) { // 判断开始标签元素是否是book
						event = parser.next();
						px.setPxjg(parser.getText());
					}

					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("pxs")) { // 判断结束标签元素是否是book
						renypx.setPxs(pxs);
						pxs = null;
					}
					if (parser.getName().equals("px")) { // 判断结束标签元素是否是book
						pxs.add(px);
						px = null;
					}

					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return renypx;
	}
	/**
	 * 1.20	获取人员质量
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Renyzl parseRenyzl(String xmlStr) {
		Ryzl ryzl = null;
		List<Ryzl> ryzls = null;
		Renyzl renyzl = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						renyzl = new Renyzl();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						renyzl.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						renyzl.setMsg(parser.getText());
					} else if (parser.getName().equals("ryzls")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryzls = new ArrayList<Ryzl>();
					} else if (parser.getName().equals("ryzl")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryzl = new Ryzl();
					} else if (parser.getName().equals("xh")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryzl.setXh(parser.getText());
					} else if (parser.getName().equals("xm")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryzl.setXm(parser.getText());
					} else if (parser.getName().equals("rydm")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryzl.setRydm(parser.getText());
					} else if (parser.getName().equals("rq")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryzl.setRq(parser.getText());
					}else if (parser.getName().equals("sjly")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryzl.setSlly(parser.getText());
					}else if (parser.getName().equals("cpdm")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryzl.setCpdm(parser.getText());
					}else if (parser.getName().equals("wtms")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryzl.setWtms(parser.getText());
					}else if (parser.getName().equals("jjfz")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryzl.setJjfz(parser.getText());
					}else if (parser.getName().equals("zxyj")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryzl.setZxyj(parser.getText());
					}else if (parser.getName().equals("bz")) { // 判断开始标签元素是否是book
						event = parser.next();
						ryzl.setBz(parser.getText());
					}

					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("ryzls")) { // 判断结束标签元素是否是book
						renyzl.setRyzls(ryzls);
						ryzls = null;
					}
					if (parser.getName().equals("ryzl")) { // 判断结束标签元素是否是book
						ryzls.add(ryzl);
						ryzl = null;
					}

					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return renyzl;
	}
	/**
	 * 1.21	获取产品历史产量
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Chanplscl parseChanplscl(String xmlStr) {
		
		Chanplscl chanplscl = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						chanplscl = new Chanplscl();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						chanplscl.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						chanplscl.setMsg(parser.getText());
					} else if (parser.getName().equals("cplscl-month")) { // 判断开始标签元素是否是book
						event = parser.next();
						chanplscl.setCplscl_month(parser.getText());
					} else if (parser.getName().equals("cplscl-year")) { // 判断开始标签元素是否是book
						event = parser.next();
						chanplscl.setCplscl_year(parser.getText());
					} 

					break;
				case XmlPullParser.END_TAG:
					

					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return chanplscl;
	}
	/**
	 * 1.22	获取产品历史质量
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Chanplszl parseChanplszl(String xmlStr) {
		
		Chanplszl chanplszl = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						chanplszl = new Chanplszl();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						chanplszl.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						chanplszl.setMsg(parser.getText());
					} else if (parser.getName().equals("cplszl-year")) { // 判断开始标签元素是否是book
						event = parser.next();
						chanplszl.setCplszl_year(parser.getText());
					} 

					break;
				case XmlPullParser.END_TAG:
					

					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return chanplszl;
	}
	/**
	 * 1.23	获取产品质量损失
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Chanpzlss parseChanpzlss(String xmlStr) {
		
		Chanpzlss chanpzlss = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						chanpzlss = new Chanpzlss();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						chanpzlss.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						chanpzlss.setMsg(parser.getText());
					} else if (parser.getName().equals("cpzlss")) { // 判断开始标签元素是否是book
						event = parser.next();
						chanpzlss.setCpzlss(parser.getText());
					}

					break;
				case XmlPullParser.END_TAG:
					

					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return chanpzlss;
	}
	/**
	 * 1.24	获取产品技术文件
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Chanpjswj parseChanpjswj(String xmlStr) {
		
		Chanpjswj chanpjswj = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						chanpjswj = new Chanpjswj();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						chanpjswj.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						chanpjswj.setMsg(parser.getText());
					} else if (parser.getName().equals("src_tzwj")) { // 判断开始标签元素是否是book
						event = parser.next();
						chanpjswj.setSrc_tzwj(parser.getText());
					}
					else if (parser.getName().equals("src_zyzd")) { // 判断开始标签元素是否是book
						event = parser.next();
						chanpjswj.setSrc_zyzd(parser.getText());
					}
					else if (parser.getName().equals("src_bzzy")) { // 判断开始标签元素是否是book
						event = parser.next();
						chanpjswj.setSrc_bzzy(parser.getText());
					}
					break;
				case XmlPullParser.END_TAG:
					

					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return chanpjswj;
	}
	/**
	 * 1.25	获取产品检验记录
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Chanpjyjl parseChanpjyjl(String xmlStr) {
		
		Chanpjyjl chanpjyjl = null;
		List<String> lists = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						chanpjyjl = new Chanpjyjl();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						chanpjyjl.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						chanpjyjl.setMsg(parser.getText());
					} else if (parser.getName().equals("jyjls")) { // 判断开始标签元素是否是book
						event = parser.next();
						lists = new ArrayList<>();

					}else if (parser.getName().equals("src_jyjl")) { // 判断开始标签元素是否是book
						event = parser.next();
						lists.add(parser.getText());
					}
					
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("jyjls")) { // 判断结束标签元素是否是book
						chanpjyjl.setSrc_jyjls(lists);

					}

					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return chanpjyjl;
	}
	
	/**
	 * 1.27获取产品质量损失记录
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Chanpzlssjl parseChapzlssjl(String xmlStr) {
		Zlssjl zlssjl = null;
		List<Zlssjl> zlssjls = null;
		Chanpzlssjl chapzlssjl = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						chapzlssjl = new Chanpzlssjl();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						chapzlssjl.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						chapzlssjl.setMsg(parser.getText());
					} else if (parser.getName().equals("zlssjls")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlssjls = new ArrayList<Zlssjl>();

					} else if (parser.getName().equals("zlssjl")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlssjl = new Zlssjl();

					} else if (parser.getName().equals("scrq")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlssjl.setScrq(parser.getText());
					} else if (parser.getName().equals("sjly")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlssjl.setSjly(parser.getText());
					} else if (parser.getName().equals("qxlx")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlssjl.setQxlx(parser.getText());
					} else if (parser.getName().equals("wtms")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlssjl.setWtms(parser.getText());
					} else if (parser.getName().equals("zt")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlssjl.setZt(parser.getText());
					}

					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("zlssjl")) { // 判断结束标签元素是否是book
						zlssjls.add(zlssjl);
						zlssjl = null;
					}
					if (parser.getName().equals("zlssjls")) { // 判断结束标签元素是否是book
						chapzlssjl.setZlssjls(zlssjls);
						zlssjl = null;
					}
					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return chapzlssjl;
	}
	/**
	 * 1.28获取产品质量损失html
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static ChanpzlssEx parseChanpzlssEx(String xmlStr) {
		
		ChanpzlssEx chanpzlssEx = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						chanpzlssEx = new ChanpzlssEx();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						chanpzlssEx.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						chanpzlssEx.setMsg(parser.getText());
					} else if (parser.getName().equals("src")) { // 判断开始标签元素是否是book
						event = parser.next();
						chanpzlssEx.setSrc(parser.getText());
					}

					break;
				case XmlPullParser.END_TAG:
					

					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return chanpzlssEx;
	}
	/**
	 * 1.29获取产品质量损失记录
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Chanpzlssjl parseChanxzlssjl(String xmlStr) {
		Zlssjl zlssjl = null;
		List<Zlssjl> zlssjls = null;
		Chanpzlssjl chapzlssjl = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						chapzlssjl = new Chanpzlssjl();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						chapzlssjl.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						chapzlssjl.setMsg(parser.getText());
					} else if (parser.getName().equals("zlssjls")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlssjls = new ArrayList<Zlssjl>();

					} else if (parser.getName().equals("zjssjl")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlssjl = new Zlssjl();

					} else if (parser.getName().equals("scrq")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlssjl.setScrq(parser.getText());
					} 
					else if (parser.getName().equals("cpdm")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlssjl.setCpdm(parser.getText());
					}
					else if (parser.getName().equals("sjly")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlssjl.setSjly(parser.getText());
					} else if (parser.getName().equals("qxlx")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlssjl.setQxlx(parser.getText());
					} else if (parser.getName().equals("wtms")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlssjl.setWtms(parser.getText());
					} else if (parser.getName().equals("zt")) { // 判断开始标签元素是否是book
						event = parser.next();
						zlssjl.setZt(parser.getText());
					}

					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("zjssjl")) { // 判断结束标签元素是否是book
						zlssjls.add(zlssjl);
						zlssjl = null;
					}
					if (parser.getName().equals("zlssjls")) { // 判断结束标签元素是否是book
						chapzlssjl.setZlssjls(zlssjls);
						zlssjl = null;
					}
					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return chapzlssjl;
	}
	/**
	 * 1.31获取安全履历
	 * 
	 * @param xmlStr
	 * @return
	 */
	public static Anqll parseAnqll(String xmlStr) {
		Anqll anqll = null;
		List<String> lists = null;
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						anqll = new Anqll();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						anqll.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						anqll.setMsg(parser.getText());
					} else if (parser.getName().equals("aqlls")) { // 判断开始标签元素是否是book
						event = parser.next();
						lists = new ArrayList<String>();
					} else if (parser.getName().equals("src_aqll")) { // 判断开始标签元素是否是book
						event = parser.next();
						lists.add(parser.getText());
					} 
					break;
				case XmlPullParser.END_TAG:
					
					if (parser.getName().equals("aqlls")) { // 判断结束标签元素是否是book
						anqll.setSrkc_aqlls(lists);
					}
					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return anqll;
	}
	/**
	 * 1.32获取授权解锁
	 */
	public static Shouqjs parseShouqjs(String xmlStr) {
		Shouqjs shouqjs = null;
		
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:

					if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
						shouqjs = new Shouqjs();
					} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
						event = parser.next();
						shouqjs.setResult(parser.getText());
					} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
						event = parser.next();
						shouqjs.setMsg(parser.getText());
					} 
					break;
				case XmlPullParser.END_TAG:
					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return shouqjs;
	}
	/**
	 * 1.34获取人员劳动纪律
	 */
	public static Renyldjl parseRenyldjl(String xmlStr) {
		Renyldjl renyldjl = null;
		List<Ldjl> ldjls = null;
        Ldjl ldjl = null;

		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

					case XmlPullParser.START_DOCUMENT:
						break;
					case XmlPullParser.START_TAG:

						if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
							renyldjl = new Renyldjl();
						} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
							event = parser.next();
							renyldjl.setResult(parser.getText());
						} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
							event = parser.next();
							renyldjl.setMsg(parser.getText());
						}else if (parser.getName().equals("ldjls")) { // 判断开始标签元素是否是book
							event = parser.next();
							ldjls = new ArrayList<Ldjl>();
						}else if (parser.getName().equals("ldjl")) { // 判断开始标签元素是否是book
							event = parser.next();
							ldjl = new Ldjl();

						}else if (parser.getName().equals("rq")) { // 判断开始标签元素是否是book
							event = parser.next();
							ldjl.setRq(parser.getText());
						}else if (parser.getName().equals("wftl")) { // 判断开始标签元素是否是book
							event = parser.next();
							ldjl.setWftl(parser.getText());
						}else if (parser.getName().equals("clcs")) { // 判断开始标签元素是否是book
							event = parser.next();
							ldjl.setClcs(parser.getText());
						}else if (parser.getName().equals("clr")) { // 判断开始标签元素是否是book
							event = parser.next();
							ldjl.setClr(parser.getText());
						}
						break;
					case XmlPullParser.END_TAG:
						if (parser.getName().equals("ldjl")) { // 判断结束标签元素是否是book
							ldjls.add(ldjl);
							ldjl = null;
						}
						if (parser.getName().equals("ldjls")) { // 判断结束标签元素是否是book
							renyldjl.setLdjls(ldjls);
							ldjls = null;
						}
						break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return renyldjl;
	}

	/**
	 * 1.34获取人员劳动纪律
	 */
	public static Renyjx parseRenyjx(String xmlStr) {
		Renyjx renyjx = null;
		List<Jx> jxes = null;
		Jx jx = null;

		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlStr));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				switch (event) {

					case XmlPullParser.START_DOCUMENT:
						break;
					case XmlPullParser.START_TAG:

						if (parser.getName().equals(ConfirmChanxdm.DATA)) { // 判断开始标签元素是否是book
							renyjx = new Renyjx();
						} else if (parser.getName().equals(ConfirmChanxdm.RESULT)) {
							event = parser.next();
							renyjx.setResult(parser.getText());
						} else if (parser.getName().equals(ConfirmChanxdm.MSG)) { // 判断开始标签元素是否是book
							event = parser.next();
							renyjx.setMsg(parser.getText());
						}else if (parser.getName().equals("ryjxs")) { // 判断开始标签元素是否是book
							event = parser.next();
							jxes = new ArrayList<>();
						}else if (parser.getName().equals("ryjx")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx = new Jx();

						}else if (parser.getName().equals("xh")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setXh(parser.getText());
						}else if (parser.getName().equals("scrq")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setScrq(parser.getText());
						}else if (parser.getName().equals("bcdm")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setBcdm(parser.getText());
						}else if (parser.getName().equals("cxdm")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setCxdm(parser.getText());
						}else if (parser.getName().equals("czgrs")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setCzgrs(parser.getText());
						}else if (parser.getName().equals("fzr")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setFzr(parser.getText());
						}else if (parser.getName().equals("jyy")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setJyy(parser.getText());
						}else if (parser.getName().equals("czgs")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setCzgs(parser.getText());
						}else if (parser.getName().equals("cpdm")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setCpdm(parser.getText());
						}else if (parser.getName().equals("cpmc")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setCpmc(parser.getText());
						}else if (parser.getName().equals("ggxh")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setGgxh(parser.getText());
						}else if (parser.getName().equals("gxfl")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setGxfl(parser.getText());
						}else if (parser.getName().equals("scgx")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setScgx(parser.getText());
						}else if (parser.getName().equals("jhsl")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setJhsl(parser.getText());
						}else if (parser.getName().equals("wcsl")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setWcsl(parser.getText());
						}else if (parser.getName().equals("bcsj")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setBcsj(parser.getText());
						}else if (parser.getName().equals("rwgfs")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setRwgfs(parser.getText());
						}else if (parser.getName().equals("qtgfs")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setQtgfs(parser.getText());
						}else if (parser.getName().equals("tjs")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setTjs(parser.getText());
						}else if (parser.getName().equals("lfs")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setLfs(parser.getText());
						}else if (parser.getName().equals("txsj")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setTxsj(parser.getText());
						}
						else if (parser.getName().equals("jcsl")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setJcsl(parser.getText());
						}
						else if (parser.getName().equals("pd5sdf")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setPd5sdf(parser.getText());
						}
						else if (parser.getName().equals("jhwcl")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setJhwcl(parser.getText());
						}
						else if (parser.getName().equals("bcdcl")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setBcdcl(parser.getText());
						}
						else if (parser.getName().equals("hgl")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setHgl(parser.getText());
						}
						else if (parser.getName().equals("gfl")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setGfl(parser.getText());
						}
						else if (parser.getName().equals("lfl")) { // 判断开始标签元素是否是book
							event = parser.next();
							jx.setLfl(parser.getText());
						}
						break;
					case XmlPullParser.END_TAG:
						if (parser.getName().equals("ryjx")) { // 判断结束标签元素是否是book
							jxes.add(jx);
							jx = null;
						}
						if (parser.getName().equals("ryjxs")) { // 判断结束标签元素是否是book
							renyjx.setJxes(jxes);
							jxes = null;
						}
						break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			return null;
		}
		return renyjx;
	}
	}