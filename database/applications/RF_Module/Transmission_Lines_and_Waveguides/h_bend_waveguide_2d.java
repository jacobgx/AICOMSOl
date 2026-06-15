/*
 * h_bend_waveguide_2d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:41 by COMSOL 6.3.0.290. */
public class h_bend_waveguide_2d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Transmission_Lines_and_Waveguides");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/emw", true);
    model.study("std1").feature("freq").set("plist", "range(4[GHz],25[MHz],5.2[GHz])");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 0.08);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", 0.04);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", 0.08);
    model.component("comp1").geom("geom1").feature("sq1").set("pos", new double[]{0, -0.08});
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("co1", "Compose");
    model.component("comp1").geom("geom1").feature("co1").selection("input").set("c1", "c2", "sq1");
    model.component("comp1").geom("geom1").feature("co1").set("formula", "sq1*(c1-c2)");
    model.component("comp1").geom("geom1").run("co1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.04, 0.1});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{0.04, 0});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.1, 0.04});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{-0.1, -0.08});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7a7a\u6c14");
    model.component("comp1").material("mat1").selection().set(1, 3);
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u77f3\u82f1\u73bb\u7483");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"1.44"});

    model.component("comp1").physics("emw").prop("components").set("components", "outofplane");
    model.component("comp1").physics("emw").feature("wee1").set("DisplacementFieldModel", "RefractiveIndex");
    model.component("comp1").physics("emw").create("port1", "Port", 1);
    model.component("comp1").physics("emw").feature("port1").selection().set(1);
    model.component("comp1").physics("emw").feature("port1").set("PortType", "Rectangular");
    model.component("comp1").physics("emw").create("port2", "Port", 1);
    model.component("comp1").physics("emw").feature("port2").selection().set(7);
    model.component("comp1").physics("emw").feature("port2").set("PortType", "Rectangular");

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (emw)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"S11", "S21"});
    model.result("pg2").label("S \u53c2\u6570 (emw)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg2").feature("glob1").set("xdataexpr", "freq");
    model.result("pg2").feature("glob1").set("xdataunit", "GHz");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg3", "SmithGroup");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("rgr1", "ReflectionGraph");
    model.result("pg3").feature("rgr1").set("unit", new String[]{""});
    model.result("pg3").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg3").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg3").label("\u53f2\u5bc6\u65af\u56fe (emw)");
    model.result("pg3").feature("rgr1").set("titletype", "manual");
    model.result("pg3").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg3").feature("rgr1").set("linemarker", "point");
    model.result("pg3").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("rgr1").create("col1", "Color");
    model.result("pg3").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg3").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 10, 0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("linemarker", "cycle");
    model.result("pg2").feature("glob1").set("markerpos", "interp");
    model.result("pg2").run();
    model.result("pg3").run();

    model.study().create("std2");
    model.study("std2").create("fdss", "FrequencyDomainSourceSweep");
    model.study("std2").feature("fdss").set("plotgroup", "Default");
    model.study("std2").feature("fdss").set("solnum", "auto");
    model.study("std2").feature("fdss").set("notsolnum", "auto");
    model.study("std2").feature("fdss").set("outputmap", new String[]{});
    model.study("std2").feature("fdss").set("ngenAUX", "1");
    model.study("std2").feature("fdss").set("goalngenAUX", "1");
    model.study("std2").feature("fdss").set("ngenAUX", "1");
    model.study("std2").feature("fdss").set("goalngenAUX", "1");
    model.study("std2").feature("fdss").setSolveFor("/physics/emw", true);
    model.study("std2").feature("fdss").set("plist", "5.1[GHz]");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u7535\u573a (emw) 1");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").setIndex("looplevel", 1, 1);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().numerical().create("gmev1", "EvalGlobalMatrix");
    model.result().numerical("gmev1").label("S \u53c2\u6570 (emw)");
    model.result().numerical("gmev1").set("data", "dset2");
    model.result().numerical("gmev1").set("expr", "emw.SdB");
    model.result().numerical("gmev1").set("dataseries", "sum");
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gmev1").set("table", "tbl1");
    model.result().numerical("gmev1").run();
    model.result().numerical("gmev1").setResult();
    model.result("pg4").run();

    model.title("H \u5f2f\u6ce2\u5bfc - \u4e8c\u7ef4");

    model
         .description("\u672c\u4f8b\u5728\u4e8c\u7ef4\u6a21\u5f0f\u4e0b\u6a21\u62df TE10\u00a0\u6ce2\u901a\u8fc7\u6ce2\u5bfc\u4e2d 90\u00a0\u5f2f\u66f2\u90e8\u5206\u7684\u4f20\u8f93\u60c5\u51b5\u3002");

    model.label("h_bend_waveguide_2d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
