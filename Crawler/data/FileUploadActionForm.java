12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/struts/form/FileUploadActionForm.java
package ru.bgcrm.struts.form;

import org.apache.struts.upload.FormFile;

public class FileUploadActionForm
    extends DynActionForm
{
    private FormFile file;
    
    public void setFile(FormFile theFile)
    {
        file = theFile; 
    }
    
    public FormFile getFile()
    {
        return file;
    }
}
