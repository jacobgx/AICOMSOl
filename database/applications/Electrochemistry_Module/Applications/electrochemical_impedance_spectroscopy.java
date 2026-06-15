/*
 * electrochemical_impedance_spectroscopy.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:52 by COMSOL 6.3.0.290. */
public class electrochemical_impedance_spectroscopy {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrochemistry_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryElectroanalysis", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("cRed");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"cRed", "cOx"});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("ftplistmethod", "manual");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/tcd", true);
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
    model.param().set("A_el", "1[mm^2]", "Electrode area");
    model.param().set("c_bulk_ox", "1.0[mol/m^3]", "Bulk concentration");
    model.param().set("c_bulk_red", "1.0[mol/m^3]", "Bulk concentration");
    model.param().set("c_ref", "1.0[mol/m^3]", "Reference concentration");
    model.param().set("Cdl", "20[uF/cm^2]", "Double layer interfacial capacitance");
    model.param().set("Dox", "5e-10[m^2/s]", "Diffusion coefficient");
    model.param().set("Dred", "5e-10[m^2/s]", "Diffusion coefficient");
    model.param().set("freq_max", "10000[Hz]", "Maximum frequency");
    model.param().set("freq_min", "1[Hz]", "Minimum frequency");
    model.param().set("i0", "5.0[A/m^2]", "Exchange current density at reference concentrations");
    model.param().set("k0", "0.001[cm/s]", "Heterogeneous rate constant");
    model.param().set("L_el", "xdiff_max*10", "Electrolyte length");
    model.param().set("log_freq_max", "log10(freq_max[1/Hz])", "Log of max frequency");
    model.param().set("log_freq_min", "log10(freq_min[1/Hz])", "Log of min frequency");
    model.param().set("V_app", "5[mV]", "Applied perturbation potential");
    model.param().set("xdiff_max", "sqrt(Dox/(pi*freq_min))", "Mean diffusion layer thickness at minimum frequency");
    model.param().set("xdiff_min", "sqrt(Dox/(pi*freq_max))", "Mean diffusion layer thickness at maximum frequency");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L_el", 1);
    model.component("comp1").geom("geom1").run("i1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cRed", new String[]{"Dred", "0", "0", "0", "Dred", "0", "0", "0", "Dred"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cOx", new String[]{"Dox", "0", "0", "0", "Dox", "0", "0", "0", "Dox"});
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_bulk_red", 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_bulk_ox", 1);
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 0);
    model.component("comp1").physics("tcd").feature("conc1").selection().set(2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "c_bulk_red", 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "c_bulk_ox", 1);
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 0);
    model.component("comp1").physics("tcd").feature("es1").selection().set(1);
    model.component("comp1").physics("tcd").feature("es1").set("BoundaryCondition", "TotalCurrent");
    model.component("comp1").physics("tcd").feature("es1").set("Itl", "0[A]");
    model.component("comp1").physics("tcd").feature("es1").set("deltaItot", "5[mA]");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0_ref", "i0");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -1, 1);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("cref", "c_ref", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("cref", "c_ref", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").create("dlc1", "DoubleLayerCapacitance", 0);
    model.component("comp1").physics("tcd").feature("es1").feature("dlc1").set("Cdl", "Cdl");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "xdiff_min/25");

    model.study("std1").feature("frlin").set("plist", "10^range(log_freq_min,0.05,log_freq_max)");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("Impedance with respect to ground");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("xlabel", "Real impedance (\u03a9*m<sup>2</sup>)");
    model.result("pg1").set("ylabel", "Imaginary impedance (\u03a9*m<sup>2</sup>)");
    model.result("pg1").set("preserveaspect", true);
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("nyq1", "Nyquist");
    model.result("pg1").feature("nyq1").set("markerpos", "datapoints");
    model.result("pg1").feature("nyq1").set("linewidth", "preference");
    model.result("pg1").feature("nyq1").setIndex("expr", "conj(tcd.Zvsgrnd_es1)", 0);
    model.result("pg1").feature("nyq1").setIndex("descr", "Impedance with respect to ground", 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("Bode plot, absolute value of impedance");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "Frequency (Hz)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "Impedance (\u03a9*m<sup>2</sup>)");
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("ylog", true);
    model.result("pg2").set("showlegends", false);
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "Bode plot: Impedance");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "abs(tcd.Zvsgrnd_es1)", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "Absolute value of impedance", 0);
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "freq");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").set("title", "Phase angle");
    model.result("pg3").label("Bode phase plot");
    model.result("pg3").set("ylabel", "Phase angle (rad)");
    model.result("pg3").set("ylog", false);
    model.result("pg3").run();
    model.result("pg3").feature("glob1").setIndex("expr", "arg(tcd.Zvsgrnd_es1)", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "Phase angle", 0);
    model.result("pg3").run();

    model.title(null);

    model.description("");

    model.label("electrochemical_impedance_spectroscopy_embedded.mph");

    model.result("pg3").run();

    model.setExpectedComputationTime("7 \u79d2");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///impedancespec.docx");
    model.result().report("rpt1").set("imagesize", "large");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").label("\u7535\u5316\u5b66\u963b\u6297\u8c31");
    model.result().report("rpt1").feature("tp1").set("titleimage", "none");
    model.result().report("rpt1").feature("tp1").set("includeauthor", false);
    model.result().report("rpt1").feature("tp1").set("includecompany", false);
    model.result().report("rpt1").feature("tp1").set("includeversion", false);
    model.result().report("rpt1").feature("tp1")
         .set("summary", "\u7535\u5316\u5b66\u963b\u6297\u8c31\uff08EIS\uff09\u662f\u4e00\u79cd\u5e38\u7528\u6280\u672f\uff0c\u901a\u8fc7\u5728\u7535\u5316\u5b66\u7cfb\u7edf\u4e2d\u65bd\u52a0\u4e00\u4e2a\u6781\u5c0f\u7684\u632f\u8361\u6270\u52a8\uff0c\u63a2\u7a76\u5176\u52a8\u529b\u5b66\u548c\u4f20\u9012\u5c5e\u6027\u3002\u672c\u4f8b\u6a21\u62df\u4e86\u4e00\u7cfb\u5217\u7535\u6781\u53cd\u5e94\u901f\u7387\u7684 EIS\uff0c\u901a\u8fc7\u5948\u594e\u65af\u7279\u56fe\u548c\u6ce2\u7279\u56fe\u8bf4\u660e\u4e86\u53cd\u5e94\u5728\u52a8\u529b\u5b66\u6216\u4f20\u9012\u63a7\u5236\u4e0b\u8fdb\u884c\u7684\u8f6c\u53d8\u3002");
    model.result().report("rpt1").feature("tp1").set("includeacknowledgment", false);
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("\u5173\u4e8e\u8f6f\u4ef6");
    model.result().report("rpt1").feature("sec1").feature("root1").set("includeunitsystem", true);
    model.result().report("rpt1").feature("sec1").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u6a21\u578b\u53c2\u6570");
    model.result().report("rpt1").feature("sec2").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("param1")
         .label("\u5185\u5d4c\u6a21\u578b\u7684\u53c2\u6570");
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec3").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("pg1").label("\u5948\u594e\u65af\u7279\u56fe");
    model.result().report("rpt1").feature("sec3").feature().duplicate("pg2", "pg1");
    model.result().report("rpt1").feature("sec3").feature("pg2").set("noderef", "pg2");
    model.result().report("rpt1").feature("sec3").feature("pg2")
         .label("\u6ce2\u7279\u56fe\uff0c\u963b\u6297\u7684\u7edd\u5bf9\u503c");
    model.result().report("rpt1").feature("sec3").feature().duplicate("pg3", "pg2");
    model.result().report("rpt1").feature("sec3").feature("pg3").label("\u6ce2\u7279\u56fe\uff0c\u76f8\u89d2");
    model.result().report("rpt1").feature("sec3").feature("pg3").set("noderef", "pg3");

    model.title("\u7535\u5316\u5b66\u963b\u6297\u8c31");

    model
         .description("\u672c App \u7684\u76ee\u7684\u662f\u5e2e\u52a9\u60a8\u4e86\u89e3 EIS \u56fe\u3001\u5948\u594e\u65af\u7279\u56fe\u548c\u6ce2\u7279\u56fe\u3002\u60a8\u53ef\u4ee5\u66f4\u6539\u672c\u4f53\u6d53\u5ea6\u3001\u6269\u6563\u7cfb\u6570\u3001\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\u3001\u53cc\u7535\u5c42\u7535\u5bb9\u4ee5\u53ca\u6700\u5927\u548c\u6700\u5c0f\u9891\u7387\u3002\n\n\u7535\u5316\u5b66\u963b\u6297\u8c31 (EIS) \u662f\u4e00\u79cd\u5e38\u7528\u7684\u7535\u5206\u6790\u6280\u672f\uff0c\u7528\u4e8e\u7814\u7a76\u7535\u5316\u5b66\u7cfb\u7edf\u7684\u8c10\u6ce2\u54cd\u5e94\u3002\u672c\u4f8b\u5bf9\u5de5\u4f5c\u7535\u6781\u7684\u7535\u4f4d\u65bd\u52a0\u4e00\u4e2a\u5c0f\u7684\u6b63\u5f26\u53d8\u5316\uff0c\u5e76\u5728\u9891\u57df\u5206\u6790\u4ea7\u751f\u7684\u7535\u6d41\u3002\n\n\u963b\u6297\u7684\u5b9e\u90e8\u548c\u865a\u90e8\u7ed9\u51fa\u4e86\u6709\u5173\u7535\u6c60\u52a8\u529b\u5b66\u548c\u8d28\u91cf\u4f20\u9012\u5c5e\u6027\uff0c\u4ee5\u53ca\u53cc\u7535\u5c42\u7535\u5bb9\u7684\u8868\u9762\u5c5e\u6027\u7684\u4fe1\u606f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("electrochemical_impedance_spectroscopy.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
