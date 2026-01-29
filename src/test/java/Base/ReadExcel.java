package Base;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class ReadExcel {
    @DataProvider(name="Readexcel")
    public Object[][] readExcel(Method method)
    {
        String tcname= method.getName();
        String sheetName;
        String[] userType;
        List<Object[]> list=new ArrayList<>();
        SheetName sheetNameAnnotation=method.getAnnotation(SheetName.class);
        if(sheetNameAnnotation!=null)
        {
            sheetName=sheetNameAnnotation.value();
        }
        else {
            sheetName="default";
        }
        Usertype usertypeAnnotation=method.getAnnotation(Usertype.class);
        if(usertypeAnnotation!=null)
        {
            userType=usertypeAnnotation.value();
        }
        else {
            userType=new String[0];
        }
        int tcnamecol=-1; int usernamecol=-1; int passwordcol=-1; int usertypecol=-1;
        try(FileInputStream fis=new FileInputStream("input/input.xlsx"); XSSFWorkbook wb=new XSSFWorkbook(fis)) {
            XSSFSheet sheet=wb.getSheet(sheetName);
            Row row=sheet.getRow(0);
            DataFormatter dataFormatter=new DataFormatter();
            for(Cell cell:row)
            {
                if(cell.getStringCellValue().equalsIgnoreCase("test case name"))
                {
                    tcnamecol=cell.getColumnIndex();
                }
                if(cell.getStringCellValue().equalsIgnoreCase("username"))
                {
                    usernamecol=cell.getColumnIndex();
                }
                if(cell.getStringCellValue().equalsIgnoreCase("password"))
                {
                    passwordcol=cell.getColumnIndex();
                }
                if(cell.getStringCellValue().equalsIgnoreCase("usertype")) {
                    usertypecol = cell.getColumnIndex();
                }
            }
            if(usernamecol==-1||tcnamecol==-1||usertypecol==-1||passwordcol==-1)
            {
                throw new RuntimeException("Please check the row header in the excel file");
            }
            int lastrownum=sheet.getLastRowNum();
            for(int i=1;i<=lastrownum;i++)
            {
                Row row1=sheet.getRow(i);
                if(row1==null)
                    continue;
                 if(row1.getCell(tcnamecol).getStringCellValue().equalsIgnoreCase(tcname)) {
                    for (String user : userType) {
                        if(row1.getCell(usertypecol).getStringCellValue().equalsIgnoreCase(user))
                        {
                         String username=dataFormatter.formatCellValue(row1.getCell(usernamecol));
                         String password=dataFormatter.formatCellValue(row1.getCell(passwordcol));
                         list.add(new Object[] {username,password});
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list.toArray(new Object[0][0]);
    }
}
