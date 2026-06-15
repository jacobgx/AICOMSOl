/*
 * boltzmann_dry_air.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:07 by COMSOL 6.3.0.290. */
public class boltzmann_dry_air {

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

    model.param().set("xN2", "0.8");
    model.param().set("xO2", "1-xN2");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Ngas", "1[atm]/(k_B_const*300[K])");
    model.component("comp1").variable("var1").set("Emax", "300[V]");

    model.component("comp1").physics("be").prop("EEDFSettings").set("eedf", "BoltzmannTwoTerm");
    model.component("comp1").physics("be").prop("EEDFSettings").set("NelemEEDF", 300);
    model.component("comp1").physics("be").prop("EEDFSettings").set("RelemEEDF", 50);
    model.component("comp1").physics("be").prop("EEDFSettings").set("emax", "Emax");
    model.component("comp1").physics("be").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("be").feature("xsec1").set("Filepath", "N2_phelps_xsecs_air.txt");
    model.component("comp1").physics("be").feature("xsec1").runCommand("importData");
    model.component("comp1").physics("be").create("xsec2", "CrossSectionImport", -1);
    model.component("comp1").physics("be").feature("xsec2").set("Filepath", "O2_phelps_xsecs_air.txt");
    model.component("comp1").physics("be").feature("xsec2").runCommand("importData");

    model.nodeGroup().create("grp1", "Physics", "be");
    model.nodeGroup("grp1").placeAfter(null);
    model.nodeGroup("grp1").add("xsec1");
    model.nodeGroup("grp1").add("xsec2");
    model.nodeGroup("grp1").add("eir1");
    model.nodeGroup("grp1").add("eir2");
    model.nodeGroup("grp1").add("eir3");
    model.nodeGroup("grp1").add("eir4");
    model.nodeGroup("grp1").add("eir5");
    model.nodeGroup("grp1").add("eir6");
    model.nodeGroup("grp1").add("eir7");
    model.nodeGroup("grp1").add("eir8");
    model.nodeGroup("grp1").add("eir9");
    model.nodeGroup("grp1").add("eir10");
    model.nodeGroup("grp1").add("eir11");
    model.nodeGroup("grp1").add("eir12");
    model.nodeGroup("grp1").add("eir13");
    model.nodeGroup("grp1").add("eir14");
    model.nodeGroup("grp1").add("eir15");
    model.nodeGroup("grp1").add("eir16");
    model.nodeGroup("grp1").add("eir17");
    model.nodeGroup("grp1").add("eir18");
    model.nodeGroup("grp1").add("eir19");
    model.nodeGroup("grp1").add("eir20");
    model.nodeGroup("grp1").add("eir21");
    model.nodeGroup("grp1").add("eir22");
    model.nodeGroup("grp1").add("eir23");
    model.nodeGroup("grp1").add("eir24");
    model.nodeGroup("grp1").add("eir25");
    model.nodeGroup("grp1").add("eir26");
    model.nodeGroup("grp1").add("eir27");
    model.nodeGroup("grp1").add("eir28");
    model.nodeGroup("grp1").add("eir29");
    model.nodeGroup("grp1").add("eir30");
    model.nodeGroup("grp1").add("eir31");
    model.nodeGroup("grp1").add("eir32");
    model.nodeGroup("grp1").add("eir33");
    model.nodeGroup("grp1").add("eir34");
    model.nodeGroup("grp1").add("eir35");
    model.nodeGroup("grp1").add("eir36");
    model.nodeGroup("grp1").add("eir37");
    model.nodeGroup("grp1").add("eir38");
    model.nodeGroup("grp1").add("eir39");
    model.nodeGroup("grp1").add("eir40");
    model.nodeGroup("grp1").add("eir41");
    model.nodeGroup("grp1").add("eir42");
    model.nodeGroup("grp1").add("eir43");
    model.nodeGroup("grp1").label("\u7535\u5b50\u78b0\u649e\u53cd\u5e94");

    model.component("comp1").physics("be").feature("bmdl1").setIndex("x", "xN2", 0, 0);
    model.component("comp1").physics("be").feature("bmdl1").setIndex("x", "xO2", 1, 0);
    model.component("comp1").physics("be").feature("bmdl1").set("TownCoefPG", true);
    model.component("comp1").physics("be").feature("bmdl1").set("RateCoefPG", false);

    model.study("std1").feature("ebar").set("plist", "10^{range(log10(0.5),1/10,log10(20))}");
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
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"be.ebar"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u7535\u5b50\u5e73\u5747\u80fd\u91cf"});
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "be.EN");
    model.result("pg2").feature("glob1").set("xdataunit", "Td");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", "on");
    model.result("pg2").set("xlabel", "Reduced electric field (Td)");
    model.result("pg2").set("ylabelactive", "on");
    model.result("pg2").set("ylabel", "Mean electron energy (eV)");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").label("\u7535\u5b50\u5e73\u5747\u80fd\u91cf (be)");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg3").feature("glob1").set("expr", new String[]{"be.muN", "be.DeN", "be.mueN", "be.DenN"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u7ea6\u5316\u7535\u5b50\u8fc1\u79fb\u7387", "\u7ea6\u5316\u7535\u5b50\u6269\u6563\u7cfb\u6570", "\u7ea6\u5316\u7535\u5b50\u80fd\u91cf\u8fc1\u79fb\u7387", "\u7ea6\u5316\u7535\u5b50\u80fd\u91cf\u6269\u6563\u7cfb\u6570"});
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "be.ebar");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("ylabelactive", "on");
    model.result("pg3").set("ylabel", "Reduced transport properties");
    model.result("pg3").label("\u4f20\u9012\u5c5e\u6027 (be)");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1")
         .set("unit", new String[]{"", "", "", "", "", "", "", "", "", "", 
         "", "", "", "", "", "", "", "", "", "", 
         "", "", "", "", "", "", "", "", "", "", 
         "", "", "", "", "", "", "", "", "", "", 
         "", "", ""});
    model.result("pg4").feature("glob1")
         .set("expr", new String[]{"be.alpha_1", "be.alpha_2", "be.alpha_3", "be.alpha_4", "be.alpha_5", "be.alpha_6", "be.alpha_7", "be.alpha_8", "be.alpha_9", "be.alpha_10", 
         "be.alpha_11", "be.alpha_12", "be.alpha_13", "be.alpha_14", "be.alpha_15", "be.alpha_16", "be.alpha_17", "be.alpha_18", "be.alpha_19", "be.alpha_20", 
         "be.alpha_21", "be.alpha_22", "be.alpha_23", "be.alpha_24", "be.alpha_25", "be.alpha_26", "be.alpha_27", "be.alpha_28", "be.alpha_29", "be.alpha_30", 
         "be.alpha_31", "be.alpha_32", "be.alpha_33", "be.alpha_34", "be.alpha_35", "be.alpha_36", "be.alpha_37", "be.alpha_38", "be.alpha_39", "be.alpha_40", 
         "be.alpha_41", "be.alpha_42", "be.alpha_43"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u6c64\u68ee\u7cfb\u6570 1: e+N2=>e+N2", "\u6c64\u68ee\u7cfb\u6570 2: e+N2=>e+N2(rot)", "\u6c64\u68ee\u7cfb\u6570 3: e+N2=>e+N2(v1)", "\u6c64\u68ee\u7cfb\u6570 4: e+N2=>e+N2(v1res)", "\u6c64\u68ee\u7cfb\u6570 5: e+N2=>e+N2(v2)", "\u6c64\u68ee\u7cfb\u6570 6: e+N2=>e+N2(v3)", "\u6c64\u68ee\u7cfb\u6570 7: e+N2=>e+N2(v4)", "\u6c64\u68ee\u7cfb\u6570 8: e+N2=>e+N2(v5)", "\u6c64\u68ee\u7cfb\u6570 9: e+N2=>e+N2(v5)", "\u6c64\u68ee\u7cfb\u6570 10: e+N2=>e+N2(v6)", 
         "\u6c64\u68ee\u7cfb\u6570 11: e+N2=>e+N2(v8)", "\u6c64\u68ee\u7cfb\u6570 12: e+N2=>e+N2(A3)", "\u6c64\u68ee\u7cfb\u6570 13: e+N2=>e+N2(A3)", "\u6c64\u68ee\u7cfb\u6570 14: e+N2=>e+N2(B3)", "\u6c64\u68ee\u7cfb\u6570 15: e+N2=>e+N2(W3)", "\u6c64\u68ee\u7cfb\u6570 16: e+N2=>e+N2(A3)", "\u6c64\u68ee\u7cfb\u6570 17: e+N2=>e+N2(B3)", "\u6c64\u68ee\u7cfb\u6570 18: e+N2=>e+N2(a1)", "\u6c64\u68ee\u7cfb\u6570 19: e+N2=>e+N2(a1)", "\u6c64\u68ee\u7cfb\u6570 20: e+N2=>e+N2(w1)", 
         "\u6c64\u68ee\u7cfb\u6570 21: e+N2=>e+N2(c3)", "\u6c64\u68ee\u7cfb\u6570 22: e+N2=>e+N2(E3)", "\u6c64\u68ee\u7cfb\u6570 23: e+N2=>e+N2(a1)", "\u6c64\u68ee\u7cfb\u6570 24: e+N2=>e+N2(Sum)", "\u6c64\u68ee\u7cfb\u6570 25: e+N2=>2e+N2+", "\u6c64\u68ee\u7cfb\u6570 26: e+N2=>2e+N2+", "\u6c64\u68ee\u7cfb\u6570 27: e+O2=>O+O-", "\u6c64\u68ee\u7cfb\u6570 28: e+O2=>O+O-", "\u6c64\u68ee\u7cfb\u6570 29: e+O2=>e+O2", "\u6c64\u68ee\u7cfb\u6570 30: e+O2=>e+O2(rot)", 
         "\u6c64\u68ee\u7cfb\u6570 31: e+O2=>e+O2(v1)", "\u6c64\u68ee\u7cfb\u6570 32: e+O2=>e+O2(v1res)", "\u6c64\u68ee\u7cfb\u6570 33: e+O2=>e+O2(v2)", "\u6c64\u68ee\u7cfb\u6570 34: e+O2=>e+O2(v2res)", "\u6c64\u68ee\u7cfb\u6570 35: e+O2=>e+O2(v3)", "\u6c64\u68ee\u7cfb\u6570 36: e+O2=>e+O2(v4)", "\u6c64\u68ee\u7cfb\u6570 37: e+O2=>e+O2(a1)", "\u6c64\u68ee\u7cfb\u6570 38: e+O2=>e+O2(b1)", "\u6c64\u68ee\u7cfb\u6570 39: e+O2=>e+O2(e4.5)", "\u6c64\u68ee\u7cfb\u6570 40: e+O2=>e+O2(e6.0)", 
         "\u6c64\u68ee\u7cfb\u6570 41: e+O2=>e+ O2(e8.4)", "\u6c64\u68ee\u7cfb\u6570 42: e+O2=>e+O2(e9.97)", "\u6c64\u68ee\u7cfb\u6570 43: e+O2=>2e+O2+"});
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "be.ebar");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("ylog", "on");
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").feature("glob1").set("autoexpr", "off");
    model.result("pg4").feature("glob1").set("autodescr", "on");
    model.result("pg4").label("\u6c64\u68ee\u7cfb\u6570 (be)");
    model.result("pg4").set("ylabel", "Townsend coefficient (m<sup>2</sup>)");
    model.result("pg1").run();
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 0.1);
    model.result("pg1").set("xmax", 300);
    model.result("pg1").set("ymin", "1e-7");
    model.result("pg1").set("ymax", 10);
    model.result("pg1").set("xlog", true);
    model.result("pg1").setIndex("looplevelinput", "manual", 0);
    model.result("pg1").setIndex("looplevel", new int[]{1, 6, 11, 15, 17}, 0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("ylog", true);
    model.result("pg3").run();
    model.result("pg3").set("xlog", true);
    model.result("pg3").set("ylog", true);
    model.result("pg3").set("legendpos", "lowerleft");
    model.result("pg4").run();
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("xmin", 0);
    model.result("pg4").set("xmax", 20);
    model.result("pg4").set("ymin", "1e-25");
    model.result("pg4").set("ymax", "2e-19");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").set("expr", new String[]{});
    model.result("pg4").feature("glob1").set("descr", new String[]{});
    model.result("pg4").feature("glob1").setIndex("expr", "be.alpha_25+be.alpha_26", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "N2 \u7535\u79bb", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "be.alpha_43", 1);
    model.result("pg4").feature("glob1").setIndex("descr", "O2 \u7535\u79bb", 1);
    model.result("pg4").feature("glob1").setIndex("expr", "be.alpha_27*Ngas[cm^3]", 2);
    model.result("pg4").feature("glob1").setIndex("descr", "3 \u4f53\u9ecf\u9644", 2);
    model.result("pg4").feature("glob1").setIndex("expr", "be.alpha_28", 3);
    model.result("pg4").feature("glob1").setIndex("descr", "2 \u4f53\u9ecf\u9644", 3);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("N2 \u901f\u7387\u7cfb\u6570");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "N2 \u901f\u7387\u7cfb\u6570");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("xlabel", "\u7535\u5b50\u5e73\u5747\u80fd\u91cf (V)");
    model.result("pg5").set("ylabel", "\u901f\u7387\u7cfb\u6570 (m<sup>3</sup>/s)");
    model.result("pg5").set("ylog", true);
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("xmin", 0);
    model.result("pg5").set("xmax", 20);
    model.result("pg5").set("ymin", "1e-20");
    model.result("pg5").set("ymax", "1e-12");
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "be.k_1", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u5f39\u6027", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "be.k_2", 1);
    model.result("pg5").feature("glob1").setIndex("descr", "\u8f6c\u52a8", 1);
    model.result("pg5").feature("glob1")
         .setIndex("expr", "be.k_3+be.k_4+be.k_5+be.k_6+be.k_7+be.k_8+be.k_9+be.k_10+be.k_11", 2);
    model.result("pg5").feature("glob1").setIndex("descr", "\u632f\u52a8", 2);
    model.result("pg5").feature("glob1")
         .setIndex("expr", "be.k_12+be.k_13+be.k_14+be.k_15+be.k_16+be.k_17+be.k_18+be.k_19+be.k_20+be.k_21+be.k_22+be.k_23+be.k_24", 3);
    model.result("pg5").feature("glob1").setIndex("descr", "\u7535\u5b50", 3);
    model.result("pg5").feature("glob1").setIndex("expr", "be.k_25+be.k_26", 4);
    model.result("pg5").feature("glob1").setIndex("descr", "\u7535\u79bb", 4);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "be.ebar");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("O2 \u901f\u7387\u7cfb\u6570 1");
    model.result("pg6").set("title", "O2 \u901f\u7387\u7cfb\u6570");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").set("expr", new String[]{});
    model.result("pg6").feature("glob1").set("descr", new String[]{});
    model.result("pg6").feature("glob1").setIndex("expr", "be.k_29", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u5f39\u6027", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "be.k_30", 1);
    model.result("pg6").feature("glob1").setIndex("descr", "\u8f6c\u52a8", 1);
    model.result("pg6").feature("glob1").setIndex("expr", "be.k_31+be.k_32+be.k_33+be.k_34+be.k_35+be.k_36", 2);
    model.result("pg6").feature("glob1").setIndex("descr", "\u632f\u52a8", 2);
    model.result("pg6").feature("glob1").setIndex("expr", "be.k_37+be.k_38+be.k_39+be.k_40+be.k_41+be.k_42", 3);
    model.result("pg6").feature("glob1").setIndex("descr", "\u7535\u5b50", 3);
    model.result("pg6").feature("glob1").setIndex("expr", "be.k_43", 4);
    model.result("pg6").feature("glob1").setIndex("descr", "\u7535\u79bb", 4);
    model.result("pg6").feature("glob1").setIndex("expr", "be.k_27*Ngas[cm^3]", 5);
    model.result("pg6").feature("glob1").setIndex("descr", "3 \u4f53\u9ecf\u9644", 5);
    model.result("pg6").feature("glob1").setIndex("expr", "be.k_28", 6);
    model.result("pg6").feature("glob1").setIndex("descr", "2 \u4f53\u9ecf\u9644", 6);
    model.result("pg6").run();
    model.result().dataset().create("par1", "Parametric1D");
    model.result().dataset().create("tran1", "Transformation2D");
    model.result().dataset("tran1").set("enablescale", true);
    model.result().dataset("tran1").set("scale", new String[]{"Emax/1[V]", "1"});
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").run();
    model.result("pg7").label("EEDF \u4e8c\u7ef4");
    model.result("pg7").set("data", "tran1");
    model.result("pg7").set("titletype", "none");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "log10(be.f)");
    model.result("pg7").feature("surf1").set("colortable", "Prism");
    model.result("pg7").feature("surf1").create("hght1", "Height");
    model.result("pg7").run();
    model.result().export().create("data1", "Data");
    model.result().export("data1").set("data", "tran1");
    model.result().export("data1").setIndex("expr", "be.f", 0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg7").run();

    model.title("\u5e72\u7a7a\u6c14\u73bb\u5c14\u5179\u66fc\u5206\u6790");

    model
         .description("\u672c\u6a21\u578b\u901a\u8fc7\u6c42\u89e3\u4e24\u9879\u8fd1\u4f3c\u73bb\u5c14\u5179\u66fc\u65b9\u7a0b\u6765\u7814\u7a76\u6c2e\u6c14\u548c\u6c27\u6c14\u6df7\u5408\u7269\uff08\u80cc\u666f\u5e72\u7a7a\u6c14\uff09\uff0c\u5e76\u901a\u8fc7\u5728\u7535\u5b50\u78b0\u649e\u622a\u9762\u4e0a\u5bf9\u7535\u5b50\u80fd\u91cf\u5206\u5e03\u51fd\u6570\u8fdb\u884c\u9002\u5f53\u79ef\u5206\u6765\u8ba1\u7b97\u7535\u5b50\u4f20\u8f93\u7cfb\u6570\u548c\u6e90\u9879\u3002\u6b64\u5916\uff0c\u8be5\u6a21\u578b\u8fd8\u6f14\u793a\u4e86\u5982\u4f55\u51c6\u5907\u53ef\u5bfc\u51fa\u7684\u7535\u5b50\u80fd\u91cf\u5206\u5e03\u51fd\u6570\uff0c\u4ee5\u4fbf\u7528\u4e8e\u201c\u7b49\u79bb\u5b50\u4f53\u201d\u63a5\u53e3\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("boltzmann_dry_air.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
