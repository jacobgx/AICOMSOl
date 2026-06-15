/*
 * boltzmann_oxygen.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:07 by COMSOL 6.3.0.290. */
public class boltzmann_oxygen {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Two-Term_Boltzmann_Equation");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("be", "BoltzmannEquation");

    model.study().create("std1");
    model.study("std1").create("ebar", "MeanEnergies");
    model.study("std1").feature("ebar").set("ftplistmethod", "manual");
    model.study("std1").feature("ebar").set("solnum", "auto");
    model.study("std1").feature("ebar").set("notsolnum", "auto");
    model.study("std1").feature("ebar").set("outputmap", new String[]{});
    model.study("std1").feature("ebar").setSolveFor("/physics/be", true);

    model.component("comp1").physics("be").prop("EEDFSettings").set("eedf", "BoltzmannTwoTermQuadratic");
    model.component("comp1").physics("be").prop("EEDFSettings").set("eecolls", true);
    model.component("comp1").physics("be").prop("EEDFSettings").set("NelemEEDF", 500);
    model.component("comp1").physics("be").prop("EEDFSettings").set("RelemEEDF", 100);
    model.component("comp1").physics("be").prop("EEDFSettings").set("computeMaxEnergy", true);
    model.component("comp1").physics("be").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("be").feature("xsec1").set("Filepath", "O2_xsecs.txt");
    model.component("comp1").physics("be").feature("xsec1").runCommand("importData");
    model.component("comp1").physics("be").feature("bmdl1").set("MoleConstrainedSpecies", "O2");
    model.component("comp1").physics("be").feature("bmdl1").setIndex("x", "1.5e-5", 1, 0);
    model.component("comp1").physics("be").feature("bmdl1").setIndex("x", "1e-5", 2, 0);
    model.component("comp1").physics("be").feature("bmdl1").setIndex("x", "5e-6", 3, 0);
    model.component("comp1").physics("be").feature("bmdl1").set("ebar", false);
    model.component("comp1").physics("be").feature("bmdl1").set("RateCoefPG", false);
    model.component("comp1").physics("be").feature("bmdl1").set("TownCoefPG", true);
    model.component("comp1").physics("be").feature("bmdl1").set("EnergyLossFractionPG", true);
    model.component("comp1").physics("be").feature("init1").set("ebarinit", "0.25[V]");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1")
         .set("table", new String[][]{{"0.0050", "500"}, 
         {"0.0081", "800"}, 
         {"0.0112", "1110"}, 
         {"0.0204", "2160"}, 
         {"0.0297", "2600"}, 
         {"0.0420", "3300"}, 
         {"0.0543", "3900"}, 
         {"0.0697", "4100"}, 
         {"0.0851", "4100"}, 
         {"0.1128", "4700"}, 
         {"0.1186", "3800"}, 
         {"0.1313", "4500"}, 
         {"0.1466", "4700"}, 
         {"0.1467", "4800"}, 
         {"0.1621", "4900"}, 
         {"0.1775", "5100"}, 
         {"0.2237", "5200"}, 
         {"0.2825", "5500"}, 
         {"0.2853", "5800"}, 
         {"0.3010", "6400"}, 
         {"0.3470", "6000"}, 
         {"0.4930", "7700"}, 
         {"0.5650", "7400"}, 
         {"0.5970", "7700"}, 
         {"0.6160", "8800"}, 
         {"0.7700", "10000"}, 
         {"0.9060", "1.1e+004"}, 
         {"0.9240", "1.1e+004"}, 
         {"1.0780", "12300"}, 
         {"1.1860", "13300"}, 
         {"1.2320", "13400"}, 
         {"1.2640", "15000"}, 
         {"1.3860", "14300"}, 
         {"1.4120", "15100"}, 
         {"1.4400", "16900"}, 
         {"1.4860", "14600"}, 
         {"1.5400", "15200"}, 
         {"1.6860", "19300"}, 
         {"1.6940", "1.6e+004"}, 
         {"1.6950", "14000"}, 
         {"1.7560", "17900"}, 
         {"1.8480", "16900"}, 
         {"2.0020", "17700"}, 
         {"2.1560", "18500"}, 
         {"2.3100", "19100"}, 
         {"2.3180", "20900"}, 
         {"2.4640", "19900"}, 
         {"2.6180", "20500"}, 
         {"2.7720", "21000"}, 
         {"2.8250", "25000"}, 
         {"2.8800", "24300"}, 
         {"2.9260", "21600"}, 
         {"2.9650", "23800"}, 
         {"3.0000", "22300"}, 
         {"3.0800", "2.2e+004"}, 
         {"3.1400", "20000"}, 
         {"3.3700", "24500"}, 
         {"3.3800", "22000"}, 
         {"3.7300", "24500"}, 
         {"4.1400", "25400"}, 
         {"4.2700", "26700"}, 
         {"4.4900", "27000"}, 
         {"4.5000", "25600"}, 
         {"4.5500", "27000"}, 
         {"4.6400", "27000"}, 
         {"4.7200", "27000"}, 
         {"5.2700", "2.8e+004"}, 
         {"5.3400", "28000"}, 
         {"5.6500", "3.0e+004"}, 
         {"5.7700", "26300"}, 
         {"5.9100", "2.7e+004"}, 
         {"5.9700", "2.7e+004"}, 
         {"6.1600", "29500"}, 
         {"6.2400", "29400"}, 
         {"6.5000", "30000"}, 
         {"6.8100", "30500"}, 
         {"6.9400", "30000"}, 
         {"7.0600", "31000"}, 
         {"7.2400", "30000"}, 
         {"7.5800", "29500"}, 
         {"7.7700", "32000"}, 
         {"8.3700", "33000"}, 
         {"8.4200", "33000"}, 
         {"8.4800", "32000"}, 
         {"8.5000", "34000"}, 
         {"8.9300", "33000"}, 
         {"9.1200", "35000"}, 
         {"9.3200", "35000"}, 
         {"9.7600", "36000"}, 
         {"9.9200", "37000"}, 
         {"10.5900", "33000"}, 
         {"10.9000", "39000"}, 
         {"11.0300", "38000"}, 
         {"11.3000", "35000"}, 
         {"11.8900", "40000"}, 
         {"12.2400", "35000"}, 
         {"12.5700", "40000"}, 
         {"13.3100", "44000"}, 
         {"13.6700", "38000"}, 
         {"14.1000", "46000"}, 
         {"14.1200", "39000"}, 
         {"14.5500", "44000"}, 
         {"14.8600", "44000"}, 
         {"15.2800", "39000"}, 
         {"15.8200", "50000"}, 
         {"15.8800", "48000"}, 
         {"16.1000", "57000"}, 
         {"16.6000", "44000"}, 
         {"16.9500", "43000"}, 
         {"17.3500", "49000"}, 
         {"17.6600", "58000"}, 
         {"18.2400", "45000"}, 
         {"18.3600", "55000"}, 
         {"18.7600", "53000"}, 
         {"18.8200", "53000"}, 
         {"19.4900", "58000"}, 
         {"19.6300", "51000"}, 
         {"19.7700", "48000"}, 
         {"21.0700", "62000"}, 
         {"22.0700", "60000"}, 
         {"22.6000", "55000"}, 
         {"22.6300", "66000"}, 
         {"24.4200", "57000"}, 
         {"25.4200", "61000"}, 
         {"25.7100", "73000"}, 
         {"28.0400", "75000"}, 
         {"28.2500", "68000"}, 
         {"28.3300", "79000"}, 
         {"29.6500", "88000"}, 
         {"30.4000", "79000"}, 
         {"30.5000", "61000"}, 
         {"31.1000", "75000"}, 
         {"33.9000", "82000"}, 
         {"36.4000", "92000"}, 
         {"36.5000", "88000"}, 
         {"36.7000", "87000"}, 
         {"39.6000", "93000"}, 
         {"42.4000", "98000"}, 
         {"44.6000", "107000"}, 
         {"44.8000", "109000"}, 
         {"45.0000", "88000"}, 
         {"45.2000", "103000"}, 
         {"48.0000", "108000"}, 
         {"50.8000", "113000"}, 
         {"52.9000", "116000"}, 
         {"53.7000", "117000"}, 
         {"56.5000", "120000"}, 
         {"60.7000", "113000"}, 
         {"75.5000", "130000"}, 
         {"90.9000", "145000"}, 
         {"92.8000", "169000"}, 
         {"98.8000", "176000"}, 
         {"105.9000", "162000"}, 
         {"106.7000", "181000"}, 
         {"113.7000", "187000"}, 
         {"121.5000", "194000"}, 
         {"121.7000", "178000"}, 
         {"126.1000", "198000"}, 
         {"131.2000", "201000"}, 
         {"135.6000", "192000"}, 
         {"137.3000", "208000"}, 
         {"144.3000", "213000"}, 
         {"149.7000", "216000"}, 
         {"150.3000", "203000"}, 
         {"152.1000", "220000"}, 
         {"159.1000", "227000"}, 
         {"175.2000", "237000"}, 
         {"189.1000", "256000"}, 
         {"197.0000", "259000"}, 
         {"204.3000", "272000"}, 
         {"204.9000", "275000"}, 
         {"219.8000", "278000"}, 
         {"224.9000", "288000"}, 
         {"233.7000", "292000"}, 
         {"249.5000", "300000"}, 
         {"259.6000", "320000"}, 
         {"273.1000", "310000"}, 
         {"288.9000", "310000"}, 
         {"303.0000", "330000"}, 
         {"332.0000", "370000"}, 
         {"349.0000", "400000"}});
    model.component("comp1").func("int1").setIndex("argunit", "Td", 0);
    model.component("comp1").func("int1").setIndex("fununit", "m/s", 0);
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2")
         .set("table", new String[][]{{"0.0103", "0.0241"}, 
         {"0.0127", "0.0241"}, 
         {"0.0140", "0.0241"}, 
         {"0.0166", "0.0244"}, 
         {"0.0187", "0.0244"}, 
         {"0.0223", "0.0248"}, 
         {"0.0254", "0.0249"}, 
         {"0.0296", "0.0251"}, 
         {"0.0341", "0.0257"}, 
         {"0.0398", "0.0260"}, 
         {"0.0465", "0.0266"}, 
         {"0.0551", "0.0276"}, 
         {"0.0653", "0.0288"}, 
         {"0.0741", "0.0296"}, 
         {"0.0865", "0.0310"}, 
         {"0.0969", "0.0330"}, 
         {"0.1116", "0.0350"}, 
         {"0.1284", "0.0390"}, 
         {"0.1418", "0.0420"}, 
         {"0.1587", "0.0460"}, 
         {"0.1777", "0.0500"}, 
         {"0.1961", "0.0540"}, 
         {"0.2134", "0.0570"}, 
         {"0.2323", "0.0630"}, 
         {"0.2528", "0.0680"}, 
         {"0.2751", "0.0730"}, 
         {"0.3080", "0.0800"}, 
         {"0.3350", "0.0870"}, 
         {"0.3650", "0.0920"}, 
         {"0.4030", "0.0990"}, 
         {"0.4570", "0.1080"}, 
         {"0.5120", "0.1150"}, 
         {"0.5650", "0.1210"}, 
         {"0.6150", "0.1260"}, 
         {"0.6690", "0.1310"}, 
         {"0.7490", "0.1370"}, 
         {"0.8500", "0.1450"}, 
         {"0.9930", "0.1590"}, 
         {"1", "0.1400"}, 
         {"1.1890", "0.1850"}, 
         {"1.2120", "0.1880"}, 
         {"1.4890", "0.2050"}, 
         {"1.5160", "0.2070"}, 
         {"1.7850", "0.2250"}, 
         {"1.8190", "0.2250"}, 
         {"2", "0.2100"}, 
         {"2.3820", "0.2560"}, 
         {"2.4250", "0.2580"}, 
         {"2.9800", "0.2930"}, 
         {"3", "0.3100"}, 
         {"3.0300", "0.2930"}, 
         {"4", "0.4000"}, 
         {"4.5400", "0.4300"}, 
         {"4.5500", "0.4300"}, 
         {"5", "0.5300"}, 
         {"5.9900", "0.6000"}, 
         {"6", "0.6500"}, 
         {"6.0600", "0.6000"}, 
         {"7", "0.7600"}, 
         {"8", "0.8900"}, 
         {"9", "1.0100"}, 
         {"9.0700", "0.9900"}, 
         {"9.0900", "0.9800"}, 
         {"10", "1.0800"}, 
         {"12", "1.2700"}, 
         {"12.1200", "1.3100"}, 
         {"12.2200", "1.2900"}, 
         {"14", "1.4700"}, 
         {"14.9000", "1.5700"}, 
         {"15", "1.5500"}, 
         {"15.1600", "1.6000"}, 
         {"15.2000", "1.5700"}, 
         {"15.4000", "1.5700"}, 
         {"16", "1.7300"}, 
         {"17.8800", "1.8100"}, 
         {"18.1900", "1.8400"}, 
         {"18.3200", "1.8400"}, 
         {"22.3500", "2.0500"}, 
         {"29.8000", "2.3200"}, 
         {"30", "2.2300"}, 
         {"37.3000", "2.5100"}, 
         {"44.7000", "2.6800"}, 
         {"45", "2.5800"}, 
         {"52.2000", "2.8000"}, 
         {"59.6000", "2.9300"}, 
         {"60.1000", "2.8700"}, 
         {"75.2000", "3.1000"}, 
         {"90.2000", "3.3000"}, 
         {"105.9000", "3.5000"}, 
         {"121.2000", "3.6000"}, 
         {"136.9000", "3.8000"}, 
         {"150.7000", "4"}});
    model.component("comp1").func("int2").setIndex("argunit", "Td", 0);
    model.component("comp1").func("int2").setIndex("fununit", "V", 0);
    model.component("comp1").func("int2").set("extrap", "linear");

    model.study("std1").feature("ebar").set("plist", "range(0.25,0.25,10)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "xe_be*root.comp1.be.emax");
    model.result("pg1").feature("lngr1").selection().all();
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"be.f"});
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("ylog", true);
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "Energy (eV)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "EEDF (eV<sup>-3/2</sup>)");
    model.result("pg1").label("EEDF (be)");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"be.muN", "be.DeN", "be.mueN", "be.DenN"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u7ea6\u5316\u7535\u5b50\u8fc1\u79fb\u7387", "\u7ea6\u5316\u7535\u5b50\u6269\u6563\u7cfb\u6570", "\u7ea6\u5316\u7535\u5b50\u80fd\u91cf\u8fc1\u79fb\u7387", "\u7ea6\u5316\u7535\u5b50\u80fd\u91cf\u6269\u6563\u7cfb\u6570"});
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "be.ebar");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("ylabelactive", "on");
    model.result("pg2").set("ylabel", "Reduced transport properties");
    model.result("pg2").label("\u4f20\u9012\u5c5e\u6027 (be)");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1")
         .set("unit", new String[]{"", "", "", "", "", "", "", "", "", "", 
         "", "", "", "", "", "", "", "", ""});
    model.result("pg3").feature("glob1")
         .set("expr", new String[]{"be.alpha_1", "be.alpha_2", "be.alpha_3", "be.alpha_4", "be.alpha_5", "be.alpha_6", "be.alpha_7", "be.alpha_8", "be.alpha_9", "be.alpha_10", 
         "be.alpha_11", "be.alpha_12", "be.alpha_13", "be.alpha_14", "be.alpha_15", "be.alpha_16", "be.alpha_17", "be.alpha_18", "be.alpha_19"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u6c64\u68ee\u7cfb\u6570 1: e+O2=>e+O2", "\u6c64\u68ee\u7cfb\u6570 2: e+O2=>O+O-", "\u6c64\u68ee\u7cfb\u6570 3: e+O2=>e+O2", "\u6c64\u68ee\u7cfb\u6570 4: e+O2=>e+O2", "\u6c64\u68ee\u7cfb\u6570 5: e+O2=>e+O2", "\u6c64\u68ee\u7cfb\u6570 6: e+O2=>e+O2", "\u6c64\u68ee\u7cfb\u6570 7: e+O2=>e+O2", "\u6c64\u68ee\u7cfb\u6570 8: e+O2=>e+O2", "\u6c64\u68ee\u7cfb\u6570 9: e+O2=>e+O2", "\u6c64\u68ee\u7cfb\u6570 10: e+O2=>e+O2a1d", 
         "\u6c64\u68ee\u7cfb\u6570 11: e+O2a1d=>e+O2", "\u6c64\u68ee\u7cfb\u6570 12: e+O2=>e+O2b1s", "\u6c64\u68ee\u7cfb\u6570 13: e+O2b1s=>e+O2", "\u6c64\u68ee\u7cfb\u6570 14: e+O2=>e+O245", "\u6c64\u68ee\u7cfb\u6570 15: e+O245=>e+O2", "\u6c64\u68ee\u7cfb\u6570 16: e+O2=>e+O+O", "\u6c64\u68ee\u7cfb\u6570 17: e+O2=>e+O+O1d", "\u6c64\u68ee\u7cfb\u6570 18: e+O2=>e+O+O1s", "\u6c64\u68ee\u7cfb\u6570 19: e+O2=>2e+O2+"});
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "be.ebar");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("ylog", "on");
    model.result("pg3").set("legendpos", "lowerright");
    model.result("pg3").feature("glob1").set("autoexpr", "off");
    model.result("pg3").feature("glob1").set("autodescr", "on");
    model.result("pg3").label("\u6c64\u68ee\u7cfb\u6570 (be)");
    model.result("pg3").set("ylabel", "Townsend coefficient (m<sup>2</sup>)");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1")
         .set("unit", new String[]{"", "", "", "", "", "", "", "", "", "", 
         "", "", "", "", "", "", "", "", ""});
    model.result("pg4").feature("glob1")
         .set("expr", new String[]{"be.xe_1", "be.xe_2", "be.xe_3", "be.xe_4", "be.xe_5", "be.xe_6", "be.xe_7", "be.xe_8", "be.xe_9", "be.xe_10", 
         "be.xe_11", "be.xe_12", "be.xe_13", "be.xe_14", "be.xe_15", "be.xe_16", "be.xe_17", "be.xe_18", "be.xe_19"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u80fd\u91cf\u635f\u5931\u5206\u6570 1: e+O2=>e+O2", "\u80fd\u91cf\u635f\u5931\u5206\u6570 2: e+O2=>O+O-", "\u80fd\u91cf\u635f\u5931\u5206\u6570 3: e+O2=>e+O2", "\u80fd\u91cf\u635f\u5931\u5206\u6570 4: e+O2=>e+O2", "\u80fd\u91cf\u635f\u5931\u5206\u6570 5: e+O2=>e+O2", "\u80fd\u91cf\u635f\u5931\u5206\u6570 6: e+O2=>e+O2", "\u80fd\u91cf\u635f\u5931\u5206\u6570 7: e+O2=>e+O2", "\u80fd\u91cf\u635f\u5931\u5206\u6570 8: e+O2=>e+O2", "\u80fd\u91cf\u635f\u5931\u5206\u6570 9: e+O2=>e+O2", "\u80fd\u91cf\u635f\u5931\u5206\u6570 10: e+O2=>e+O2a1d", 
         "\u80fd\u91cf\u635f\u5931\u5206\u6570 11: e+O2a1d=>e+O2", "\u80fd\u91cf\u635f\u5931\u5206\u6570 12: e+O2=>e+O2b1s", "\u80fd\u91cf\u635f\u5931\u5206\u6570 13: e+O2b1s=>e+O2", "\u80fd\u91cf\u635f\u5931\u5206\u6570 14: e+O2=>e+O245", "\u80fd\u91cf\u635f\u5931\u5206\u6570 15: e+O245=>e+O2", "\u80fd\u91cf\u635f\u5931\u5206\u6570 16: e+O2=>e+O+O", "\u80fd\u91cf\u635f\u5931\u5206\u6570 17: e+O2=>e+O+O1d", "\u80fd\u91cf\u635f\u5931\u5206\u6570 18: e+O2=>e+O+O1s", "\u80fd\u91cf\u635f\u5931\u5206\u6570 19: e+O2=>2e+O2+"});
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "be.ebar");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("ylog", "on");
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").feature("glob1").set("autoexpr", "off");
    model.result("pg4").feature("glob1").set("autodescr", "on");
    model.result("pg4").label("\u80fd\u91cf\u635f\u5931\u5206\u6570 (be)");
    model.result("pg4").set("ylabel", "Energy loss fraction (1)");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevelinput", "manual", 0);
    model.result("pg1").setIndex("looplevel", new int[]{8, 16, 24, 32, 40}, 0);
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 0);
    model.result("pg1").set("xmax", 80);
    model.result("pg1").set("ymin", "1e-8");
    model.result("pg1").set("ymax", 1);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("glob1").set("linestyle", "cycle");
    model.result("pg3").feature("glob1").set("autodescr", false);
    model.result("pg3").feature("glob1").set("autoexpr", true);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("ylog", false);
    model.result("pg4").feature("glob1").set("linestyle", "cycle");
    model.result("pg4").feature("glob1").set("autodescr", false);
    model.result("pg4").feature("glob1").set("autoexpr", true);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "none");
    model.result("pg5").label("\u6f02\u79fb\u901f\u5ea6");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u7ea6\u5316\u7535\u573a (Td)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u6f02\u79fb\u901f\u5ea6 (m/s)");
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("xmin", 1);
    model.result("pg5").set("xmax", "1e3");
    model.result("pg5").set("ymin", "1e4");
    model.result("pg5").set("ymax", "1e6");
    model.result("pg5").set("xlog", true);
    model.result("pg5").set("ylog", true);
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "be.w", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u6f02\u79fb\u901f\u5ea6\u8ba1\u7b97\u7ed3\u679c", 0);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "be.EN");
    model.result("pg5").feature("glob1").set("xdataunit", "Td");
    model.result("pg5").run();
    model.result("pg5").create("glob2", "Global");
    model.result("pg5").feature("glob2").set("markerpos", "datapoints");
    model.result("pg5").feature("glob2").set("linewidth", "preference");
    model.result("pg5").feature("glob2").setIndex("expr", "int1(be.EN)", 0);
    model.result("pg5").feature("glob2").setIndex("descr", "\u6f02\u79fb\u901f\u5ea6\u5b9e\u9a8c\u7ed3\u679c", 0);
    model.result("pg5").feature("glob2").set("xdata", "expr");
    model.result("pg5").feature("glob2").set("xdataexpr", "be.EN");
    model.result("pg5").feature("glob2").set("xdataunit", "Td");
    model.result("pg5").feature("glob2").set("linestyle", "none");
    model.result("pg5").feature("glob2").set("linewidth", 3);
    model.result("pg5").feature("glob2").set("linemarker", "circle");
    model.result("pg5").feature("glob2").set("markerpos", "interp");
    model.result("pg5").feature("glob2").set("markers", 25);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("titletype", "none");
    model.result("pg6").label("\u7279\u6027\u80fd\u91cf");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u7ea6\u5316\u7535\u573a (Td)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "D/\\mu (eV)");
    model.result("pg6").set("axislimits", true);
    model.result("pg6").set("xmin", 1);
    model.result("pg6").set("xmax", "1e3");
    model.result("pg6").set("ymin", 0.1);
    model.result("pg6").set("ymax", 10);
    model.result("pg6").set("xlog", true);
    model.result("pg6").set("ylog", true);
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "be.DeN/be.muN", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "D/\\mu \u8ba1\u7b97\u7ed3\u679c", 0);
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "be.EN");
    model.result("pg6").feature("glob1").set("xdataunit", "Td");
    model.result("pg6").run();
    model.result("pg6").create("glob2", "Global");
    model.result("pg6").feature("glob2").set("markerpos", "datapoints");
    model.result("pg6").feature("glob2").set("linewidth", "preference");
    model.result("pg6").feature("glob2").setIndex("expr", "int2(be.EN)", 0);
    model.result("pg6").feature("glob2").setIndex("descr", "D/\\mu \u5b9e\u9a8c\u7ed3\u679c", 0);
    model.result("pg6").feature("glob2").set("xdata", "expr");
    model.result("pg6").feature("glob2").set("xdataexpr", "be.EN");
    model.result("pg6").feature("glob2").set("xdataunit", "Td");
    model.result("pg6").feature("glob2").set("linestyle", "none");
    model.result("pg6").feature("glob2").set("linewidth", 3);
    model.result("pg6").feature("glob2").set("linemarker", "circle");
    model.result("pg6").feature("glob2").set("markerpos", "interp");
    model.result("pg6").feature("glob2").set("markers", 25);
    model.result("pg6").run();

    model.title("\u6c27\u6c14\u73bb\u5c14\u5179\u66fc\u5206\u6790");

    model
         .description("\u672c\u6559\u7a0b\u6bd4\u8f83\u4e86\u8ba1\u7b97\u5f97\u5230\u7684\u6f02\u79fb\u7ba1\u4e2d\u6c27\u6c14\u653e\u7535\u65f6\u7684\u6f02\u79fb\u901f\u5ea6\u4e0e\u5b9e\u9a8c\u7684\u7ed3\u679c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("boltzmann_oxygen.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
