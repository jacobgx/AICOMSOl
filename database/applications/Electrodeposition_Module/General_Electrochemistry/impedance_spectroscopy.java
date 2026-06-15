/*
 * impedance_spectroscopy.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:56 by COMSOL 6.3.0.290. */
public class impedance_spectroscopy {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\General_Electrochemistry");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryElectroanalysis", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("cRed");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"cRed", "cOx"});

    model.study().create("std1");
    model.study("std1").create("frlin", "Frequencylinearized");
    model.study("std1").feature("frlin").set("ftplistmethod", "manual");
    model.study("std1").feature("frlin").set("solnum", "auto");
    model.study("std1").feature("frlin").set("notsolnum", "auto");
    model.study("std1").feature("frlin").set("outputmap", new String[]{});
    model.study("std1").feature("frlin").set("ngenAUX", "1");
    model.study("std1").feature("frlin").set("goalngenAUX", "1");
    model.study("std1").feature("frlin").set("ngenAUX", "1");
    model.study("std1").feature("frlin").set("goalngenAUX", "1");
    model.study("std1").feature("frlin").setSolveFor("/physics/tcd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("D", "2e-10[m^2/s]", "\u6269\u6563\u7cfb\u6570");
    model.param().set("c_bulk", "1[mol/m^3]", "\u672c\u4f53\u6d53\u5ea6");
    model.param().set("Cdl", "20[uF/cm^2]", "\u53cc\u7535\u5c42\u754c\u9762\u7535\u5bb9");
    model.param().set("k0", "0.001[cm/s]", "\u5f02\u6784\u901f\u7387\u5e38\u6570");
    model.param().set("i0ref", "k0*F_const*1[M]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("freq_min", "1[Hz]", "\u6700\u5c0f\u9891\u7387");
    model.param().set("freq_max", "1000[Hz]", "\u6700\u5927\u9891\u7387");
    model.param().set("log_freq_min", "log10(freq_min[1/Hz])", "\u6700\u5c0f\u9891\u7387\u7684\u5bf9\u6570");
    model.param().set("log_freq_max", "log10(freq_max[1/Hz])", "\u6700\u5927\u9891\u7387\u7684\u5bf9\u6570");
    model.param()
         .set("xdiff_max", "sqrt(D/(2*pi*freq_min))", "\u6700\u5c0f\u9891\u7387\u7684\u5e73\u5747\u6269\u6563\u5c42\u539a\u5ea6");
    model.param()
         .set("xdiff_min", "sqrt(D/(2*pi*freq_max))", "\u6700\u5927\u9891\u7387\u7684\u5e73\u5747\u6269\u6563\u5c42\u539a\u5ea6");
    model.param().set("L_el", "xdiff_max*10", "\u7535\u89e3\u8d28\u957f\u5ea6");
    model.param().set("A_el", "1[mm^2]", "\u7535\u6781\u9762\u79ef");
    model.param().set("V_app", "5[mV]", "\u65bd\u52a0\u7684\u6270\u52a8\u7535\u4f4d");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L_el", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cRed", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cOx", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_bulk", 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_bulk", 1);
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 0);
    model.component("comp1").physics("tcd").feature("conc1").selection().set(2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "c_bulk", 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "c_bulk", 1);
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 0);
    model.component("comp1").physics("tcd").feature("es1").selection().set(1);
    model.component("comp1").physics("tcd").feature("es1").set("deltaphisext", "V_app");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -1, 1);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0_ref", "i0ref");
    model.component("comp1").physics("tcd").feature("es1").create("dlc1", "DoubleLayerCapacitance", 0);
    model.component("comp1").physics("tcd").feature("es1").feature("dlc1").set("Cdl", "Cdl");

    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "xdiff_min/25");

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "D", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m^2/s", 0);
    model.study("std1").feature("param").setIndex("pname", "D", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m^2/s", 0);
    model.study("std1").feature("param").setIndex("pname", "k0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "10^range(-1,-1,-3)", 0);
    model.study("std1").feature("param").setIndex("punit", "cm/s", 0);
    model.study("std1").feature("frlin").set("plist", "10^range(log_freq_min,0.05,log_freq_max)");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("nyq1", "Nyquist");
    model.result("pg1").feature("nyq1").set("unit", new String[]{""});
    model.result("pg1").feature("nyq1").set("expr", new String[]{"conj(tcd.Zvsgrnd_es1) "});
    model.result("pg1").feature("nyq1").set("descr", new String[]{""});
    model.result("pg1").label("\u5bf9\u5730\u963b\u6297\uff0c\u5948\u594e\u65af\u7279 (tcd)");
    model.result("pg1").feature("nyq1").setIndex("descr", "\u5bf9\u5730\u963b\u6297", 0);
    model.result("pg1").feature("nyq1").set("differential", "off");
    model.result("pg1").feature("nyq1").set("autodescr", "off");
    model.result("pg1").set("preserveaspect", "on");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "real(Z) (\\Omega m<sup>2</sup>)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "-imag(Z) (\\Omega m<sup>2</sup>)");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"real(conj(tcd.Zvsgrnd_es1)) "});
    model.result("pg2").feature("glob1").set("descr", new String[]{""});
    model.result("pg2").label("\u5bf9\u5730\u963b\u6297\uff0c\u5b9e\u90e8 (tcd)");
    model.result("pg2").feature("glob1").setIndex("descr", "Impedance_vs_ground_real_part", 0);
    model.result("pg2").feature("glob1").set("differential", "off");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "freq");
    model.result("pg2").feature("glob1").set("autodescr", "off");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg2").set("xlog", "on");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "real(Z) (\\Omega m<sup>2</sup>)");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("unit", new String[]{""});
    model.result("pg3").feature("glob1").set("expr", new String[]{"imag(conj(tcd.Zvsgrnd_es1)) "});
    model.result("pg3").feature("glob1").set("descr", new String[]{""});
    model.result("pg3").label("\u5bf9\u5730\u963b\u6297\uff0c\u865a\u90e8 (tcd)");
    model.result("pg3").feature("glob1").setIndex("descr", "Impedance_vs_ground_imaginary_part", 0);
    model.result("pg3").feature("glob1").set("differential", "off");
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "freq");
    model.result("pg3").feature("glob1").set("autodescr", "off");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg3").set("xlog", "on");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "-imag(Z) (\\Omega m<sup>2</sup>)");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28");
    model.result("pg4").label("\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28 (tcd)");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"cRed"});
    model.result("pg4").feature("lngr1").label("\u7269\u8d28 Red");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("autosolution", false);
    model.result("pg4").feature("lngr1").set("autoexpr", false);
    model.result("pg4").feature("lngr1").set("autodescr", false);
    model.result("pg4").feature("lngr1").set("legendprefix", "Red ");
    model.result("pg4").feature("lngr1").set("descractive", true);
    model.result("pg4").feature("lngr1").set("descr", "\u6d53\u5ea6");
    model.result("pg4").create("lngr2", "LineGraph");
    model.result("pg4").feature("lngr2").set("xdata", "expr");
    model.result("pg4").feature("lngr2").set("xdataexpr", "x");
    model.result("pg4").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr2").selection().set(1);
    model.result("pg4").feature("lngr2").set("expr", new String[]{"cOx"});
    model.result("pg4").feature("lngr2").label("\u7269\u8d28 Ox");
    model.result("pg4").feature("lngr2").set("legend", true);
    model.result("pg4").feature("lngr2").set("autosolution", false);
    model.result("pg4").feature("lngr2").set("autoexpr", false);
    model.result("pg4").feature("lngr2").set("autodescr", false);
    model.result("pg4").feature("lngr2").set("legendprefix", "Ox ");
    model.result("pg4").feature("lngr2").set("descractive", true);
    model.result("pg4").feature("lngr2").set("descr", "\u6d53\u5ea6");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").label("\u6d53\u5ea6, Red (tcd)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"cRed"});
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").label("\u6d53\u5ea6, Ox (tcd)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr1").selection().set(1);
    model.result("pg6").feature("lngr1").set("expr", new String[]{"cOx"});
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().duplicate("pg7", "pg2");
    model.result("pg7").run();
    model.result("pg7").label("\u963b\u6297\u76f8\u5bf9\u4e8e\u63a5\u5730\uff0c\u7edd\u5bf9\u503c");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").setIndex("expr", "abs(conj(tcd.Zvsgrnd_es1))", 0);
    model.result("pg7").feature("glob1")
         .setIndex("descr", "\u963b\u6297\u76f8\u5bf9\u4e8e\u63a5\u5730\uff0c\u7edd\u5bf9\u503c", 0);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u963b\u6297\u76f8\u5bf9\u4e8e\u63a5\u5730\uff0c\u76f8\u4f4d\u89d2");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").setIndex("expr", "arg(tcd.Zvsgrnd_es1)", 0);
    model.result("pg8").feature("glob1")
         .setIndex("descr", "\u963b\u6297\u76f8\u5bf9\u4e8e\u63a5\u5730\uff0c\u76f8\u4f4d\u89d2", 0);
    model.result("pg8").run();
    model.result("pg1").run();

    model.title("\u7535\u5316\u5b66\u963b\u6297\u8c31");

    model
         .description("\u7535\u5316\u5b66\u963b\u6297\u8c31 (EIS) \u662f\u4e00\u79cd\u5e38\u7528\u6280\u672f\uff0c\u901a\u8fc7\u5bf9\u7535\u5316\u5b66\u7cfb\u7edf\u65bd\u52a0\u5c0f\u5e45\u632f\u8361\u6270\u52a8\u6765\u63a2\u6d4b\u5176\u52a8\u529b\u5b66\u548c\u4f20\u9012\u5c5e\u6027\u3002\u672c\u4f8b\u5bf9\u4e00\u7cfb\u5217\u7535\u6781\u53cd\u5e94\u901f\u7387\u4e0b\u7684 EIS \u8fdb\u884c\u5efa\u6a21\u3002\u901a\u8fc7\u5948\u594e\u65af\u7279\u56fe\u548c\u6ce2\u7279\u56fe\u5c55\u793a\u4e86\u53cd\u5e94\u5728\u52a8\u529b\u5b66\u6216\u4f20\u9012\u63a7\u5236\u4e0b\u8fdb\u884c\u7684\u8fc7\u6e21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("impedance_spectroscopy.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
