package com.snackpub.office.sample;

import lombok.SneakyThrows;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.util.StringUtils;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author snackpub
 * @date 2020/4/22
 */
public class WordUtil {

    private String tempLocalPath = null;
    private XWPFDocument xwpfDocument = null;
    private FileInputStream inputStream = null;
    private OutputStream outputStream = null;

    public WordUtil() {
    }

    public WordUtil(String tempLocalPath) {
        this.tempLocalPath = tempLocalPath;
    }

    /**
     * 初始化
     *
     * @throws IOException
     */
    public void init() throws IOException {
        String localDocPaht = "E:\\123.docx";
        inputStream = new FileInputStream(new File(localDocPaht));
        xwpfDocument = new XWPFDocument(inputStream);

        // 返回带有段落和表的迭代器
        List<IBodyElement> bodyElements = xwpfDocument.getBodyElements();
        Iterator<IBodyElement> it = xwpfDocument.getBodyElementsIterator();

        bodyElements.forEach(m -> {
            BodyElementType elementType = m.getElementType();
            BodyType partType = m.getPartType();
            // 段落
            int i2 = BodyElementType.PARAGRAPH.compareTo(elementType);
            if (m instanceof XWPFParagraph) {
                System.out.println("当前获取的元素类型: XWPFParagraph");
                System.out.println("partType(身体类型): " + partType);
                System.out.println("elementType(元素类型): " + elementType);
                String s = handlerByIBodyElement(m);
            }
            // 表格
            int i = BodyElementType.TABLE.compareTo(elementType);
            if (i == 0) {
                System.out.println("表格：" + elementType);
                System.out.println("PartType: " + m.getPartType());
                IBody body = m.getBody();
                List<XWPFTable> tables = body.getTables();
                for (XWPFTable t : tables) {
                    List<XWPFTableRow> rows = t.getRows();
                    for (int i1 = 0; i1 < rows.size(); i1++) {
                        XWPFTableRow row = rows.get(i1);
                        // 获取当前row所有的单元格
                        List<XWPFTableCell> tableCells = row.getTableCells();
                        System.out.println(tableCells.size());
                        XWPFTableCell cell = row.getCell(0);
                        String text = cell.getText();
                        String color = cell.getColor(); // 获取单元格的底纹颜色
                        int width = cell.getWidth();

                        System.out.println("text:" + text + " color:" + color + " width:" + width);
                    }
                }
            }
        });

//        while (it.hasNext()) {
//
////            if (it.next() instanceof XWPFTable) {
////                XWPFTable table = (XWPFTable) it.next();
////                List<XWPFTableRow> rows = table.getRows();
////                rows.stream().forEach(System.out::println);
////            } else {
////
////            }
//            XWPFParagraph paragraph = (XWPFParagraph) it.next();
//            String text = paragraph.getText();
//            System.out.println(text);
//        }
//        bodyElements.forEach(System.out::println);
    }


    public void handlerWordFile(File file) throws Exception {
        String fileName = file.getName();
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            throw new IllegalArgumentException("当前传入的文件格式不合法！");
        }
        String suffex = fileName.substring(lastIndexOf + 1, fileName.length());
        try (InputStream is = new FileInputStream(file)) {
            switch (suffex) {
                case "doc":
//                    handlerByDocFile(is);
                    break;
                case "docx":
//                    handlerByDocxFile(is);
                    break;
                default:
                    throw new IllegalArgumentException("不能解析的文档类型，请输入正确的word文档类型的文件！");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void handlerByDocxFile(InputStream is) throws IOException, InvalidFormatException {
        XWPFDocument xwpfDocument = new XWPFDocument(is);
        Iterator<IBodyElement> bodyElementsIterator = xwpfDocument.getBodyElementsIterator();
        List<Object> datas = new ArrayList<>();
        while (bodyElementsIterator.hasNext()) {
            IBodyElement bodyElement = bodyElementsIterator.next();
            String content = handlerByBodyType(bodyElement, bodyElement.getPartType());
            datas.add(content);
        }
        xwpfDocument.close();
        is.close();
        printAllDatas(datas);
    }

    public void printAllDatas(Collection<?> datas) {
        System.out.println(datas);
    }

    //开始处理当前的身体元素
    public String handlerByIBodyElement(IBodyElement bodyElement) {
        String content = null;
        //用于处理XWPFParagraph
        if (bodyElement instanceof XWPFParagraph) {
            System.out.println("当前获取的元素类型为：XWPFParagraph");
            content = handlerXWPFParagraphType(bodyElement);
        }
        return content;
    }

    /**
     * 用于处理当前的XWPFParagraph类型的数据
     *
     * @param bodyElement 身体元素
     */
    public String handlerXWPFParagraphType(IBodyElement bodyElement) {
        XWPFParagraph xwpfParagraph = (XWPFParagraph) bodyElement;
        BodyElementType elementType = xwpfParagraph.getElementType();
        String content = getStringByBodyElementType(xwpfParagraph, elementType);
        System.out.println("当前文本的内容为：" + content);
        return content;
    }

    /**
     * 通过当前的类型和元素进行相对应的处理
     *
     * @param xwpfParagraph   段落对象
     * @param bodyElementType 内容类型
     */
    public String getStringByBodyElementType(XWPFParagraph xwpfParagraph, BodyElementType bodyElementType) {
        //当前测试结果为：PARAGRAPH
        System.out.println(bodyElementType);
        String content = "";
        switch (bodyElementType) {
            case CONTENTCONTROL:
                //如果使用的是文本控件
                break;
            case PARAGRAPH:
                //如果是段落的处理结果
                content = xwpfParagraph.getParagraphText();
                break;
            case TABLE:
                //如果当前的的元素部分为表格
                break;

            default:
                break;
        }
        return content;
    }

    //通过身体类型来处理
    public String handlerByBodyType(IBodyElement bodyElement, BodyType partType) {
        System.out.println("当前的BodyType为：" + partType);
        String content = null;
        switch (partType) {
            case CONTENTCONTROL:
                break;
            case DOCUMENT:
                content = handlerByIBodyElement(bodyElement);
                break;
            case HEADER:

                break;
            case FOOTER:

                break;
            case FOOTNOTE:

                break;
            case TABLECELL:

                break;
            default:
                throw new IllegalArgumentException("there is no this document type !please check this type!");
        }
        return content;
    }


    public void createHeader(XWPFDocument document, String orgFullName) {

        CTSectPr ctSectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy xwpfHeaderFooterPolicy = new XWPFHeaderFooterPolicy(document, ctSectPr);
        XWPFHeader header = xwpfHeaderFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);

        XWPFParagraph paragraph = header.getParagraphArray(0);
        paragraph.setBorderBottom(Borders.THICK);

        if (StringUtils.isEmpty(orgFullName)) {
            // 添加字体页眉，公司全称
            XWPFRun run = paragraph.createRun();
            run.setText(orgFullName);
        }
    }

    @SneakyThrows
    public void createHeader(XWPFDocument document, String orgFullName, String logoFilePath) {

        CTSectPr ctSectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy xwpfHeaderFooterPolicy = new XWPFHeaderFooterPolicy(document, ctSectPr);
        XWPFHeader header = xwpfHeaderFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);

        XWPFParagraph paragraph = header.getParagraphArray(0);
        paragraph.setAlignment(ParagraphAlignment.BOTH); // 设置段落左对齐
        paragraph.setBorderBottom(Borders.THICK); // 设置下划线

        XWPFRun run1 = paragraph.createRun();
        setXWPFRunStyle(run1, "新宋体", 20);

        if (!StringUtils.isEmpty(orgFullName)) {
            // 添加字体页眉，公司全称
            XWPFRun run = paragraph.createRun();
            run.setText(orgFullName);
            setXWPFRunStyle(run, "新宋体", 10);
        }
        //处理两个段落之间的制表符宽度
        run1.addTab();


        CTTabStop tabStop = paragraph.getCTP().getPPr().addNewTabs().addNewTab();
        tabStop.setVal(STTabJc.CENTER);
        int twipsPerInch = 1450;
        tabStop.setPos(BigInteger.valueOf(6 * twipsPerInch));

        /*
         * 取到二维码的图片的字节流
         * */
        if (!StringUtils.isEmpty(logoFilePath)) {


            File file = new File(logoFilePath);
            InputStream is = new FileInputStream(file);

            XWPFPicture picture = run1.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, logoFilePath, Units.toEMU(40), Units.toEMU(40));

            String blipID = "";
            //这段必须有，不然打开的logo图片不显示
            for (XWPFPictureData picturedata : header.getAllPackagePictures()) {
                blipID = header.getRelationId(picturedata);
            }
            picture.getCTPicture().getBlipFill().getBlip().setEmbed(blipID);
            run1.addTab();
            is.close();
        }


    }


    /**
     * 设置页脚的字体样式
     *
     * @param run 段落元素
     */
    private void setXWPFRunStyle(XWPFRun run, String font, int fontSize) {
        run.setFontSize(fontSize);
        CTRPr ctrPr = run.getCTR().isSetRPr() ? run.getCTR().getRPr() : run.getCTR().addNewRPr();
        CTFonts fonts = ctrPr.isSetRFonts() ? ctrPr.getRFonts() : ctrPr.addNewRFonts();
        fonts.setAscii(font);
        fonts.setEastAsia(font);
        fonts.setHAnsi(font);
    }
}
