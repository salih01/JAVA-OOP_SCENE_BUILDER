/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aracbakim;

import java.time.LocalDate;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 *
 * @author alfa
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    //Button burada
    public Button KaydetButton;
    public Button KmKontrolButton;
    public Button AraButton;
    public Button SilButton;
    public Button ListeleButton;
    public Button DeleteTableButton;
    
    //TextFieldlar burada 
    public TextField PlakaAraField;
    public TextField PlakaField;
    public TextField SahibiField;
    public TextField KmField;
    
    
    public TextArea NotBirakArea;
    
    //ComboBoxlar burada
    public ComboBox<String> AracTuruBox;
    public ComboBox<String> MarkaBox;
    public ComboBox<String> ModelBox;
    
    //CheckBox burada
    public CheckBox EngineBox;
    public CheckBox TurboBox;
    public CheckBox N2OBox;
    public CheckBox RocketBunnyBox;
    public CheckBox CarbonFiberBox;
    
    //DatePicker burada
    public DatePicker TarihDatePicker;
    
    public TableView<TableViewList> TableView;
    public TableColumn<TableViewList, String> AracTuruColumn;
    public TableColumn<TableViewList, String> MarkaColumn;
    public TableColumn<TableViewList, String> ModelColumn;
    public TableColumn<TableViewList, String> PlakaColumn;
    public TableColumn<TableViewList, String> SahipColumn;
    public TableColumn<TableViewList, String> DateColumn;
    public TableColumn<TableViewList, String> KmColumn;
    private GenelArac g_arac;
    private VeriTabani vts;
     
    public String Line,Date,lnr,SaveString;
    //Boolean türü YAZACAM !!!!!!
    String EngineBoxString=null,TurboBoxString=null,N2OBoxString=null,RocketBunnyBoxString=null,CarbonFiberBoxString=null,
            KmFieldString=null,SahibiFieldString=null,NotBirakAreaString=null,AracTuruBoxString=null,MarkaBoxString=null,ModelBoxString=null,
            TarihDatePickerString=null,PlakaFieldString=null;

    
    @FXML
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

        
        public void Transfer(){
        
        setGenelArac(new GenelArac());
        g_arac.setAracTuruBoxG(AracTuruBox.getValue());
        g_arac.setSahibiFieldG(SahibiField.getText());
        g_arac.setMarkaBoxG(MarkaBox.getValue());
        g_arac.setModelBoxG(ModelBox.getValue());
        g_arac.setPlakaFieldG(PlakaField.getText());
        g_arac.setKmFieldG(KmField.getText());
        //Tarihi al

        
        if ((TarihDatePicker.getValue() != null))
        {
            Date = TarihDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
        g_arac.setTarihDatePickerG(Date);
        //   USTAYA NOT BİRAK
        
        g_arac.setNotBirakAreaG(NotBirakArea.getText());
        
        if (NotBirakArea.getText() == null)
            NotBirakArea.setText("Ustaya Notum yok .Hayırlı işler");
        else {
                String NotePad=new String();
                for (String line_s:NotBirakArea.getText().split("\\n")) //REGEX ISLEMİ YAPIYORUZ
                    NotePad=NotePad+" "+line_s;
                NotBirakArea.setText(NotePad);
            }
 
        g_arac.setNotBirakAreaG(NotBirakArea.getText());
        g_arac.setCarbonFiberBoxG(CarbonFiberBox.isSelected());//Boolean
        g_arac.setEngineBoxG(EngineBox.isSelected());//Boolean
        g_arac.setTurboBoxG(TurboBox.isSelected());//Boolean
        g_arac.setN2OBoxG(N2OBox.isSelected());//Boolean
        g_arac.setRocketBunnyBoxG(RocketBunnyBox.isSelected());//Boolean
       
    }
             
        public void Save(ActionEvent actionEvent) 
        {
            Transfer();
            int temp=isEmpty();
            
            if (temp == 0) {
                            String SaveString = g_arac.getAracTuruBoxG()+ "&"+ g_arac.getMarkaBoxG() + "&" + g_arac.getModelBoxG() + "&" 
                    +g_arac.getPlakaFieldG() + "&"+ g_arac.getSahibiFieldG() + "&"
                    +  g_arac.getKmFieldG() +"&" + g_arac.getTarihDatePickerG() + "&" 
                    + g_arac.getNotBirakAreaG() +"&" + g_arac.isCarbonFiberBoxG() + "&" + g_arac.isEngineBoxG() +"&" + g_arac.isN2OBoxG()+ "&"
                    + g_arac.isRocketBunnyBoxG() + "&" + g_arac.isTurboBoxG() + "&";
                    
                            setVts(new VeriTabani()); // S İ N G L E T O N    TASARIM KALIBI KULLANDIĞIMIZ ÖRNEK ************************************************___________________--
                            vts.TextWrite(SaveString);
                        
                            Alert UserAlert = new Alert(Alert.AlertType.INFORMATION);
                            UserAlert.setTitle("registration Done");
                            UserAlert.setHeaderText("Done !");
                            UserAlert.setContentText("Car is saved");
                            UserAlert.showAndWait();
                            TableColumnList(actionEvent);
                            }else{
                                  Alert warningAlert = new Alert(Alert.AlertType.WARNING);
                                  warningAlert.setTitle("Error 1 ");
                                  warningAlert.setWidth(200);
                                  warningAlert.setHeight(150);
                                  warningAlert.setContentText("Atladığın bir yer var !");
                                  warningAlert.showAndWait();
                                }
                            
                            // olusturucu tasarım kalıbı factory kullanıcam 
                            

            
        }
    
    
    //   BOS OLUP OLMADIGINI KONTROL EDECEK FONKSİYONU OLUSTURUYORUZ. EMPTY İSE SAVE FONKSİYONUNA YOLLUCAZ .EMPTY ISE 1 YOLLUCAZ  
    
        public int isEmpty() {
        int iE = 0;
        if ((g_arac.getSahibiFieldG()== null)||(g_arac.getPlakaFieldG() == null) || (g_arac.getMarkaBoxG() == null) 
                || (g_arac.getModelBoxG()== null) ||(g_arac.getAracTuruBoxG() == null)
                || (g_arac.getTarihDatePickerG()== null) || (g_arac.getKmFieldG() == null))
            iE = 1;
        return iE;
    }

        public void Delete(ActionEvent actionEvent)
        {
            String ara = PlakaField.getText();
            int i, ndx, j, check = 0, delete = 0;
            
            setVts(new VeriTabani()); 
            String EngineBoxString=null,TurboBoxString=null,N2OBoxString=null,RocketBunnyBoxString=null,CarbonFiberBoxString=null,
            KmFieldString=null,SahibiFieldString=null,NotBirakAreaString=null,AracTuruBoxString=null,MarkaBoxString=null,ModelBoxString=null,
            TarihDatePickerString=null,PlakaFieldString=null;
            String  str, t = "true";
            try {
                 File file=new File("VeriTabani.txt");
                 BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                 File TempFile = new File("VeriTabaniTemp.txt");
                 BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(TempFile));
                 while ((str = bufferedReader.readLine()) != null) {
                    char[] strc = str.toCharArray();
                    char tmp[] = new char[255];
                    ndx = 0;
                    delete = 0;
                    j = 0;
                    for (i = 0; i < str.length(); i++) {
                        if (strc[i] != '&') {
                            tmp[j] = strc[i];
                            j = j + 1;
                    }
                        if (strc[i] == '&') {
                            char[] tmp1 = new char[j];
                            for (int k = 0; k < j; k++)
                                tmp1[k] = tmp[k];
                            switch (ndx) {
                                case 0:
                                    AracTuruBoxString = new String(tmp1);
                                    break;
                                case 1:
                                    MarkaBoxString = new String(tmp1);
                                    break;
                                case 2:
                                    ModelBoxString = new String(tmp1);
                                    break;
                                case 3:
                                    PlakaFieldString = new String(tmp1);
                                    break;
                                case 4:
                                    SahibiFieldString = new String(tmp1);
                                    break;
                                case 5:
                                    KmFieldString = new String(tmp1);
                                    break;
                                case 6:
                                    TarihDatePickerString = new String(tmp1);
                                    break;
                                case 7:
                                    EngineBoxString = new String(tmp1);
                                    break;
                                case 8:
                                    N2OBoxString = new String(tmp1);
                                    break;
                                case 9:
                                    RocketBunnyBoxString = new String(tmp1);
                                    break;
                                case 10:
                                    TurboBoxString= new String(tmp1);
                                    break;
                                case 11:
                                    CarbonFiberBoxString = new String(tmp1);
                                    break;
                                case 12:
                                    NotBirakAreaString = new String(tmp1);
                                    break;
                            
                                default:
                                    System.out.println("Error 2");
                                    break;
                        }
                        tmp = new char[255];
                        ndx = ndx + 1;
                        j = 0;
                    }
                }

                if (ara.equalsIgnoreCase(PlakaFieldString)) {
                    setGenelArac(new GenelArac());
                    check = 1;
                    g_arac.setSahibiFieldG(SahibiFieldString);
                    g_arac.setPlakaFieldG(PlakaFieldString);
                    g_arac.setAracTuruBoxG(AracTuruBoxString);
                    g_arac.setMarkaBoxG(MarkaBoxString);
                    g_arac.setModelBoxG(ModelBoxString);    
                    g_arac.setKmFieldG(KmFieldString);
                    g_arac.setTarihDatePickerG(TarihDatePickerString);
                    g_arac.setNotBirakAreaG(NotBirakAreaString);
                    
                    if (EngineBoxString.equals(t))
                        g_arac.setEngineBoxG(true);
                    else
                        g_arac.setEngineBoxG(false);
                    if (N2OBoxString.equals(t))
                        g_arac.setN2OBoxG(true);
                    else
                        g_arac.setN2OBoxG(false);
                    if (RocketBunnyBoxString.equals(t))
                        g_arac.setRocketBunnyBoxG(true);
                    else
                        g_arac.setRocketBunnyBoxG(false);
                    if (TurboBoxString.equals(t))
                        g_arac.setTurboBoxG(true);
                    else
                        g_arac.setTurboBoxG(false);
                    if (CarbonFiberBoxString.equals(t))
                        g_arac.setCarbonFiberBoxG(true);
                    else
                        g_arac.setCarbonFiberBoxG(false);
                    
                    
                    AracTuruBox.setValue(g_arac.getAracTuruBoxG());
                    MarkaBox.setValue(g_arac.getMarkaBoxG());
                    ModelBox.setValue(g_arac.getModelBoxG());
                    PlakaField.setText(g_arac.getPlakaFieldG());
                    SahibiField.setText(g_arac.getSahibiFieldG());
                    KmField.setText(g_arac.getKmFieldG());
                    TarihDatePicker.setPromptText(g_arac.getTarihDatePickerG());
                    
                    NotBirakArea.setText(g_arac.getNotBirakAreaG());
                    
                    EngineBox.setSelected(g_arac.isEngineBoxG());
                    CarbonFiberBox.setSelected(g_arac.isCarbonFiberBoxG());
                    N2OBox.setSelected(g_arac.isN2OBoxG());
                    TurboBox.setSelected(g_arac.isTurboBoxG());
                    RocketBunnyBox.setSelected(g_arac.isRocketBunnyBoxG());
                    
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Silme");
                    alert.setHeaderText(MarkaBoxString);
                    alert.setContentText("Bak silerim  ?");
                    Optional<ButtonType> karar = alert.showAndWait();
                    if (karar.get() == ButtonType.OK) {
                        delete = 1;
                    } else {
                        check = 0;
                    }
                }
                if (delete == 0) {
                    bufferedWriter.write(str);
                    bufferedWriter.newLine();
                }
            }
            if (check == 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Bulunamadı");
                alert.setWidth(250);
                alert.setHeight(250);
                alert.setHeaderText("PLaka Bulunamadi!");
                alert.setContentText("Plakayı Dogru Girin !");
                alert.showAndWait();
            }
            bufferedReader.close();
            bufferedWriter.close();
            file.delete();
            TempFile.renameTo(file);
                 
            } catch (IOException e) {
                System.out.println("Dosya Hatasi!!");
            }
            TableColumnList(actionEvent);
        }
        
        public void CheckKm(ActionEvent actionEvent) {
        int i, ndx, j, KNTRL = 0;
        setVts(new VeriTabani());
        String t = "true";
        try {
            FileReader fr = new FileReader("VeriTabani.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((lnr = br.readLine()) != null) {
                char[] strc = lnr.toCharArray();
                char tmp[] = new char[100];
                ndx = 0;
                j = 0;
                for (i = 0; i < lnr.length(); i++) {
                    if (strc[i] != '&') {
                        tmp[j] = strc[i];
                        j = j + 1;
                    }
                    if (strc[i] == '&') {
                        char[] tmp1 = new char[j];
                        for (int k = 0; k < j; k++)
                            tmp1[k] = tmp[k];
                        switch (ndx) {
                            case 0:
                                AracTuruBoxString = new String(tmp1);
                                break;
                            case 1:
                                MarkaBoxString = new String(tmp1);
                                break;
                            case 2:
                                ModelBoxString = new String(tmp1);
                                break;
                            case 3:
                                PlakaFieldString = new String(tmp1);
                                break;
                            case 4:
                                SahibiFieldString = new String(tmp1);
                                break;
                            case 5:
                                KmFieldString = new String(tmp1);
                                break;
                            case 6:
                                TarihDatePickerString = new String(tmp1);
                                break;

                            case 7:
                                NotBirakAreaString = new String(tmp1);
                                break;
                            case 8:
                                EngineBoxString = new String(tmp1);
                                break;
                            case 9:
                                TurboBoxString = new String(tmp1);
                                break;
                            case 10:
                                N2OBoxString = new String(tmp1);
                                break;
                            case 11:
                                RocketBunnyBoxString = new String(tmp1);
                                break;
                            case 12:
                                CarbonFiberBoxString = new String(tmp1);
                                break;
                            default:
                                System.out.println("Error 3");
                                break;
                        }
                        tmp = new char[100];
                        ndx = ndx + 1;
                        j = 0;
                    } 
                }
                
                //--------------------Factory Tasarım Kalibi--------------------
                
                
                
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate trhdnm = LocalDate.parse(TarihDatePickerString, dateTimeFormatter);
                LocalDate bugun = LocalDate.now();
                int farkay = bugun.getMonthValue() - trhdnm.getMonthValue();
                int farkyil = bugun.getYear() - trhdnm.getYear();
                Random random = new Random();
                int NewKM = random.nextInt(50000);
                int OldKM = Integer.parseInt(KmFieldString);
                //----------------------------------------
                

                if ((((farkay >= 1) && (farkyil >= 1)) || (farkyil > 1)) || (NewKM >= 15000)) {
                    setGenelArac(new GenelArac());
                    KNTRL = 1;
                    
                    g_arac.setSahibiFieldG(SahibiFieldString);
                    g_arac.setPlakaFieldG(PlakaFieldString);
                    g_arac.setAracTuruBoxG(AracTuruBoxString);
                    g_arac.setMarkaBoxG(MarkaBoxString);
                    g_arac.setModelBoxG(ModelBoxString);    
                    g_arac.setKmFieldG(KmFieldString);
                    g_arac.setTarihDatePickerG(TarihDatePickerString);
                    g_arac.setNotBirakAreaG(NotBirakAreaString);
                    
                    
                    if (EngineBoxString.equals(t))
                        g_arac.setEngineBoxG(true);
                    else
                        g_arac.setEngineBoxG(false);
                    if (N2OBoxString.equals(t))
                        g_arac.setN2OBoxG(true);
                    else
                        g_arac.setN2OBoxG(false);
                    if (RocketBunnyBoxString.equals(t))
                        g_arac.setRocketBunnyBoxG(true);
                    else
                        g_arac.setRocketBunnyBoxG(false);
                    if (TurboBoxString.equals(t))
                        g_arac.setTurboBoxG(true);
                    else
                        g_arac.setTurboBoxG(false);
                    if (CarbonFiberBoxString.equals(t))
                        g_arac.setCarbonFiberBoxG(true);
                    else
                        g_arac.setCarbonFiberBoxG(false);
                    
                    AracTuruBox.setValue(g_arac.getAracTuruBoxG());
                    MarkaBox.setValue(g_arac.getMarkaBoxG());
                    ModelBox.setValue(g_arac.getModelBoxG());
                    PlakaField.setText(g_arac.getPlakaFieldG());
                    SahibiField.setText(g_arac.getSahibiFieldG());
                    KmField.setText(g_arac.getKmFieldG());
                    TarihDatePicker.setPromptText(g_arac.getTarihDatePickerG());
                    
                    NotBirakArea.setText(g_arac.getNotBirakAreaG());
                    
                    EngineBox.setSelected(g_arac.isEngineBoxG());
                    CarbonFiberBox.setSelected(g_arac.isCarbonFiberBoxG());
                    N2OBox.setSelected(g_arac.isN2OBoxG());
                    TurboBox.setSelected(g_arac.isTurboBoxG());
                    RocketBunnyBox.setSelected(g_arac.isRocketBunnyBoxG());
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("KM Bilgi :" + ((OldKM - NewKM) + "KM"));
                    alert.setHeaderText(PlakaFieldString + " Modifiye İşlemleri Başlansın mı ?");
                    alert.setContentText("OK or Cancel !.");
                    Optional<ButtonType> karar = alert.showAndWait();
                    if (karar.get() == ButtonType.CANCEL) {
                        break;
                    } else {
                        continue;
                    }

                }
            }
            if (KNTRL == 0) {
                Alert dikkat = new Alert(Alert.AlertType.WARNING);
                dikkat.setTitle("Kontrol Tamamland?");
                dikkat.setHeaderText("Kilometresi dolan ara? yok.");
                dikkat.setContentText("SERV?SE GELMES? GEREKEN ARA? YOK!");
                dikkat.showAndWait();
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Dosya Hatas?!!");
        }

    }
        
        public void TableColumnList(ActionEvent actionEvent) {

        TableView.setDisable(false);
        setVts(new VeriTabani());
        ObservableList<TableViewList> DetailList = FXCollections.observableArrayList(); // KULLANDIĞIMIZ LİSTELEME TÜRÜ
        AracTuruColumn.setCellValueFactory(new PropertyValueFactory<>("AracTuru"));
        MarkaColumn.setCellValueFactory(new PropertyValueFactory<>("Marka"));
        ModelColumn.setCellValueFactory(new PropertyValueFactory<>("Model"));
        PlakaColumn.setCellValueFactory(new PropertyValueFactory<>("Plaka"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        SahipColumn.setCellValueFactory(new PropertyValueFactory<>("Sahip"));
        KmColumn.setCellValueFactory(new PropertyValueFactory<>("Km"));
        try {
            FileReader fileReader = new FileReader("VeriTabani.txt");
            BufferedReader br = new BufferedReader(fileReader);
                String str;
                while ((str = br.readLine()) != null) {
                    char[] strc = str.toCharArray();
                    char tmp[] = new char[100];
                    int ndx = 0;
                    int j = 0;
                    
                    for (int i = 0; i < str.length(); i++) {
                        if (strc[i] != '&') {
                            tmp[j] = strc[i];
                            j = j + 1;
                        }
                        if (strc[i] == '&') {
                            char[] tmp1 = new char[j];
                            for (int k = 0; k < j; k++)
                                tmp1[k] = tmp[k];
                            switch (ndx) {
                                case 0:
                                    AracTuruBoxString = new String(tmp1);
                                    break;
                                case 1:
                                    MarkaBoxString = new String(tmp1);
                                    break;
                                case 2:
                                    ModelBoxString = new String(tmp1);
                                    break;
                                case 3:
                                    PlakaFieldString = new String(tmp1);
                                    break;
                                case 4:
                                    SahibiFieldString = new String(tmp1);
                                    break;
                                case 5:
                                    KmFieldString = new String(tmp1);
                                    break;
                                case 6:
                                    TarihDatePickerString = new String(tmp1);
                                    break;
                                case 7:
                                    EngineBoxString = new String(tmp1);
                                    break;
                                case 8:
                                    TurboBoxString = new String(tmp1);
                                    break;
                                case 9:
                                    RocketBunnyBoxString = new String(tmp1);
                                    break;
                                case 10:
                                    N2OBoxString = new String(tmp1);
                                    break;
                                case 11:
                                    CarbonFiberBoxString = new String(tmp1);
                                    break;
                                case 12:
                                    NotBirakAreaString = new String(tmp1);
                                    break;
                                    
                                default:
                                    System.out.println("Hata Olu?tu.");
                                    break;
                            }
                            tmp = new char[100];
                            ndx = ndx + 1;
                            j = 0;
                        }
                    }
                    TableViewList WList = new TableViewList();
                    WList.setAracTuru(AracTuruBoxString);
                    WList.setMarka(MarkaBoxString);
                    WList.setModel(ModelBoxString);
                    WList.setPlaka(PlakaFieldString);
                    WList.setDate(TarihDatePickerString);
                    WList.setSahip(SahibiFieldString);
                    WList.setKm(KmFieldString);
                    DetailList.add(WList);
                    
                }
                TableView.setItems(DetailList);
                TableView.getSortOrder().add(DateColumn);
            

        } catch (IOException e) {
            System.out.println("Error 4");
        }
       // ClearTable();
    }
  
        public void Arama(MouseEvent mouseEvent) {
		//Aranacak aracın plaka bilgisini ilgili textField dan alma
                
		String aran = PlakaField.getText();
		//Plaka bilgisini Arama fonksiyonuna gönderme. Burada gönderilen 0 Tablo tıklama mı yapıldı ara Düğmesine mi
		//basıldı kontrol etmek.
		Arama(aran, 0);
	}
   
        public void Arama(String ara, int q) {

        int i, ndx, j, KNTRL = 0;
        setVts(new VeriTabani());
        String t = "true";
        try {
            FileReader fileReader = new FileReader("VeriTabani.txt");
            BufferedReader br = new BufferedReader(fileReader);
            while ((lnr = br.readLine()) != null) {
                char[] strc = lnr.toCharArray();
                char tmp[] = new char[100];
                ndx = 0;
                j = 0;
                for (i = 0; i < lnr.length(); i++) {
                    if (strc[i] != '&') {
                        tmp[j] = strc[i];
                        j = j + 1;
                    }
                    if (strc[i] == '&') {
                        char[] tmp1 = new char[j];
                        for (int k = 0; k < j; k++)
                            tmp1[k] = tmp[k];
                        switch (ndx) {
                            case 0:
                                AracTuruBoxString = new String(tmp1);
                                break;
                            case 1:
                                MarkaBoxString = new String(tmp1);
                                break;
                            case 2:
                                ModelBoxString = new String(tmp1);
                                break;
                            case 3:
                                PlakaFieldString = new String(tmp1);
                                break;
                            case 4:
                                SahibiFieldString = new String(tmp1);
                                break;
                            case 5:
                                KmFieldString = new String(tmp1);
                                break;
                            case 6:
                                TarihDatePickerString = new String(tmp1);
                                break;

                            case 7:
                                NotBirakAreaString = new String(tmp1);
                                break;
                            case 8:
                                EngineBoxString = new String(tmp1);
                                break;
                            case 9:
                                TurboBoxString = new String(tmp1);
                                break;
                            case 10:
                                CarbonFiberBoxString = new String(tmp1);
                                break;
                            case 11:
                                N2OBoxString = new String(tmp1);
                                break;
                            case 12:
                                RocketBunnyBoxString = new String(tmp1);
                                break;
                            default:
                                System.out.println("Error 5");
                                break;
                        }
                        tmp = new char[100];
                        ndx = ndx + 1;
                        j = 0;
                    }
                }
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate trhdnm = LocalDate.parse(TarihDatePickerString, format);

                if (ara.equalsIgnoreCase(PlakaFieldString)) {
                    setGenelArac(new GenelArac());
                    KNTRL = 1;
                    g_arac.setAracTuruBoxG(AracTuruBoxString);
                    g_arac.setMarkaBoxG(MarkaBoxString);
                    g_arac.setModelBoxG(ModelBoxString);
                    g_arac.setPlakaFieldG(PlakaFieldString);
                    g_arac.setSahibiFieldG(SahibiFieldString);
                    g_arac.setKmFieldG(KmFieldString);
                    g_arac.setTarihDatePickerG(TarihDatePickerString);

                    g_arac.setNotBirakAreaG(NotBirakAreaString);
                    
                    if (EngineBoxString.equals(t))
                        g_arac.setEngineBoxG(true);
                    else
                        g_arac.setEngineBoxG(false);
                    if (N2OBoxString.equals(t))
                        g_arac.setN2OBoxG(true);
                    else
                        g_arac.setN2OBoxG(false);
                    if (RocketBunnyBoxString.equals(t))
                        g_arac.setRocketBunnyBoxG(true);
                    else
                        g_arac.setRocketBunnyBoxG(false);
                    if (TurboBoxString.equals(t))
                        g_arac.setTurboBoxG(true);
                    else
                        g_arac.setTurboBoxG(false);
                    if (CarbonFiberBoxString.equals(t))
                        g_arac.setCarbonFiberBoxG(true);
                    else
                        g_arac.setCarbonFiberBoxG(false);
                    AracTuruBox.setValue(g_arac.getAracTuruBoxG());
                    MarkaBox.setValue(g_arac.getMarkaBoxG());
                    ModelBox.setValue(g_arac.getModelBoxG());
                    PlakaField.setText(g_arac.getPlakaFieldG());
                    SahibiField.setText(g_arac.getSahibiFieldG());
                    KmField.setText(g_arac.getKmFieldG());
                    TarihDatePicker.setPromptText(g_arac.getTarihDatePickerG());
                    
                    NotBirakArea.setText(g_arac.getNotBirakAreaG());
                    
                    EngineBox.setSelected(g_arac.isEngineBoxG());
                    CarbonFiberBox.setSelected(g_arac.isCarbonFiberBoxG());
                    N2OBox.setSelected(g_arac.isN2OBoxG());
                    TurboBox.setSelected(g_arac.isTurboBoxG());
                    RocketBunnyBox.setSelected(g_arac.isRocketBunnyBoxG());

                    if (q == 0) {
                        Alert uyari = new Alert(AlertType.CONFIRMATION);
                        uyari.setTitle("Arama");
                        uyari.setHeaderText(TarihDatePickerString);
                        uyari.setContentText("Kayıt Bu mu?");
                        Optional<ButtonType> karar = uyari.showAndWait();

                        if (karar.get() == ButtonType.OK) {
                            break;
                        } else {
                            KNTRL = 0;
                            continue;
                        }
                    }

                }
            }
            if (KNTRL == 0) {
                Alert dikkat = new Alert(Alert.AlertType.WARNING);
                dikkat.setTitle("Bulunamad?");
                dikkat.setWidth(200);
                dikkat.setHeight(200);
                dikkat.setHeaderText("Böyle Bir Plaka yok");
                dikkat.setContentText("Hatali Plaka ! Dogru girdiginizden emin olun ..");
                dikkat.showAndWait();
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Dosya Hatas?!!");
        }
    }
        
        public void tablotikla(MouseEvent mouseEvent) {
        
       //Seçtiğimiz satırın pozisyonunu Çekme
        TablePosition<?, ?> pos = TableView.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        TableViewList item = TableView.getItems().get(row);
        
        // Plaka bilgisini Çekme
        String data = item.getPlaka();
        
        //Tabloda seçili aracın plaka bilgisini Arama fonksiyonuna gönderme
        PlakaField.setText(data);
        Arama(data, 1);
        
        
    }
        
        public void ClearTable() {

            AracTuruBox.setValue(null);
            MarkaBox.setValue(null);
            ModelBox.setValue(null);
            PlakaField.setText(null);
            SahibiField.setText(null);
            KmField.setText(null);
            NotBirakArea.setText(null);
            EngineBox.setSelected(false);
            TurboBox.setSelected(false);
            CarbonFiberBox.setSelected(false);
            RocketBunnyBox.setSelected(false);
            N2OBox.setSelected(false);
            TarihDatePicker.setValue(LocalDate.now());
        }

        public GenelArac getGenelArac() {
            return g_arac;
        }
        
        public void setGenelArac(GenelArac g_arac) {
            this.g_arac = g_arac;
        }
        
        public VeriTabani getVts() {
            return vts;
        }
        
        public void setVts(VeriTabani vts) {
            this.vts = vts;
        }

    

   
}
