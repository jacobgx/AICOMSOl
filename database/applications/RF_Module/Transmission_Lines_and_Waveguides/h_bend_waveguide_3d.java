/*
 * h_bend_waveguide_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:41 by COMSOL 6.3.0.290. */
public class h_bend_waveguide_3d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Transmission_Lines_and_Waveguides");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

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

    model.param().set("l_wg", "10[cm]");
    model.param().descr("l_wg", "\u6ce2\u5bfc\u957f\u5ea6");
    model.param().set("w_wg", "4[cm]");
    model.param().descr("w_wg", "\u6ce2\u5bfc\u5bbd\u5ea6");
    model.param().set("h_wg", "2[cm]");
    model.param().descr("h_wg", "\u6ce2\u5bfc\u9ad8\u5ea6");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "w_wg");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", "2*w_wg");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("angle", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("rot", -90);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"w_wg", "l_wg"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new String[]{"w_wg", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"l_wg", "w_wg"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"-l_wg", "-2*w_wg"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "h_wg", 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("emw").feature("wee1").set("DisplacementFieldModel", "RefractiveIndex");
    model.component("comp1").physics("emw").create("port1", "Port", 2);
    model.component("comp1").physics("emw").feature("port1").selection().set(1);
    model.component("comp1").physics("emw").feature("port1").set("PortType", "Rectangular");
    model.component("comp1").physics("emw").create("port2", "Port", 2);
    model.component("comp1").physics("emw").feature("port2").selection().set(15);
    model.component("comp1").physics("emw").feature("port2").set("PortType", "Rectangular");

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

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (emw)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg1").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
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
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u7535\u573a, \u5bf9\u6570 (emw)");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(2, 3, 4, 5, 7, 8, 9, 10, 11, 13, 14, 16);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 13, 14, 15, 16);

    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "emw.normE");
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().set(2, 3, 4, 5, 7, 8, 9, 10, 11, 13, 14, 16);
    model.result("pg4").feature("surf1").set("colortable", "Dipole");
    model.result("pg4").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf1").create("tran1", "Transparency");
    model.result("pg4").feature("surf1").feature("tran1").set("transparency", 0.85);
    model.result("pg4").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg4").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf1").feature("mtrl1").set("family", "aluminumpolished");
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("expr", "emw.normE");
    model.result("pg4").feature("surf2").create("sel1", "Selection");
    model.result("pg4").feature("surf2").feature("sel1").selection().set(6, 12);
    model.result("pg4").feature("surf2").set("colortable", "Dipole");
    model.result("pg4").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf2").create("tran1", "Transparency");
    model.result("pg4").feature("surf2").feature("tran1").set("transparency", 0.3);
    model.result("pg4").create("surf3", "Surface");
    model.result("pg4").feature("surf3").set("expr", "emw.normE");
    model.result("pg4").feature("surf3").create("sel1", "Selection");
    model.result("pg4").feature("surf3").feature("sel1").selection().set(1, 15);
    model.result("pg4").feature("surf3").set("colortable", "Dipole");
    model.result("pg4").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf3").create("tran1", "Transparency");
    model.result("pg4").feature("surf3").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view3", "geom1");
    model.component("comp1").view("view3").camera()
         .set("position", new double[]{-0.30422060385994293, -0.3954825712286908, 0.30422060385994293});
    model.component("comp1").view("view3").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view3").camera().set("zoomanglefull", 32.49758958816528);

    model.result("pg4").set("view", "view3");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("linemarker", "cycle");
    model.result("pg2").feature("glob1").set("markerpos", "interp");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 45, 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().remove("mslc1");
    model.result("pg1").run();
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("expr", "emw.Ez");
    model.result("pg1").feature("slc1").set("descr", "\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg1").feature("slc1").set("quickplane", "xy");
    model.result("pg1").feature("slc1").set("quickzmethod", "coord");
    model.result("pg1").feature("slc1").set("colortable", "Wave");
    model.result("pg1").run();
    model.result("pg1").feature("slc1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("slc1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("slc1").feature("def1").set("expr", new String[]{"emw.Ex", "emw.Ey", "emw.Ez"});
    model.result("pg1").feature("slc1").feature("def1").set("descr", "\u7535\u573a");
    model.result("pg1").run();

    model.component("comp1").physics("emw").prop("PortSweepSettings").set("useSweep", true);

    model.param("default").setShowInParamSel(true);
    model.param().set("PortName", "1");

    model.study("std1").create("param1", "Parametric");
    model.study("std1").feature("param1").set("pname", "PortName");
    model.study("std1").feature("param1").set("plistarr", "1 2");

    model.component("comp1").physics("emw").prop("PortSweepSettings").set("ExportTouchstone", true);

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u7535\u573a (emw) 1");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 49, 0);
    model.result("pg5").setIndex("looplevel", 2, 1);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg5").feature("mslc1").set("smooth", "internal");
    model.result("pg5").feature("mslc1").set("data", "parent");
    model.result("pg5").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg5").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg6").feature("glob1")
         .set("expr", new String[]{"emw.S11dB", "emw.S12dB", "emw.S21dB", "emw.S22dB"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"S11", "S12", "S21", "S22"});
    model.result("pg6").label("S \u53c2\u6570 (emw) 1");
    model.result("pg6").feature("glob1").set("titletype", "none");
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg6").feature("glob1").set("xdataexpr", "freq");
    model.result("pg6").feature("glob1").set("xdataunit", "GHz");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("xdatasolnumtype", "all");
    model.result("pg6").feature("glob1").active(true);
    model.result().create("pg7", "SmithGroup");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("rgr1", "ReflectionGraph");
    model.result("pg7").feature("rgr1").set("unit", new String[]{"", ""});
    model.result("pg7").feature("rgr1").set("expr", new String[]{"emw.S11", "emw.S22"});
    model.result("pg7").feature("rgr1").set("descr", new String[]{"S11", "S22"});
    model.result("pg7").label("\u53f2\u5bc6\u65af\u56fe (emw) 1");
    model.result("pg7").feature("rgr1").set("titletype", "manual");
    model.result("pg7").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg7").feature("rgr1").set("linemarker", "point");
    model.result("pg7").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("rgr1").create("col1", "Color");
    model.result("pg7").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg7").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").label("\u7535\u573a, \u5bf9\u6570 (emw) 1");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(2, 3, 4, 5, 7, 8, 9, 10, 11, 13, 14, 16);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 13, 14, 15, 16);

    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "emw.normE");
    model.result("pg8").feature("surf1").create("sel1", "Selection");
    model.result("pg8").feature("surf1").feature("sel1").selection().set(2, 3, 4, 5, 7, 8, 9, 10, 11, 13, 14, 16);
    model.result("pg8").feature("surf1").set("colortable", "Dipole");
    model.result("pg8").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg8").feature("surf1").create("tran1", "Transparency");
    model.result("pg8").feature("surf1").feature("tran1").set("transparency", 0.85);
    model.result("pg8").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg8").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg8").feature("surf1").feature("mtrl1").set("family", "aluminumpolished");
    model.result("pg8").feature("surf1").set("expr", "1");
    model.result("pg8").create("surf2", "Surface");
    model.result("pg8").feature("surf2").set("expr", "emw.normE");
    model.result("pg8").feature("surf2").create("sel1", "Selection");
    model.result("pg8").feature("surf2").feature("sel1").selection().set(6, 12);
    model.result("pg8").feature("surf2").set("colortable", "Dipole");
    model.result("pg8").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg8").feature("surf2").create("tran1", "Transparency");
    model.result("pg8").feature("surf2").feature("tran1").set("transparency", 0.3);
    model.result("pg8").create("surf3", "Surface");
    model.result("pg8").feature("surf3").set("expr", "emw.normE");
    model.result("pg8").feature("surf3").create("sel1", "Selection");
    model.result("pg8").feature("surf3").feature("sel1").selection().set(1, 15);
    model.result("pg8").feature("surf3").set("colortable", "Dipole");
    model.result("pg8").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg8").feature("surf3").create("tran1", "Transparency");
    model.result("pg8").feature("surf3").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view4", "geom1");
    model.component("comp1").view("view4").camera()
         .set("position", new double[]{-0.30422060385994293, -0.3954825712286908, 0.30422060385994293});
    model.component("comp1").view("view4").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view4").camera().set("zoomanglefull", 32.49758958816528);

    model.result("pg8").set("view", "view4");
    model.result("pg5").run();
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("data", "dset2");
    model.result("pg2").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB", "emw.S12dB"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"S11", "S21", "S12"});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"emw.S11dB", "emw.S21dB", "emw.S12dB", "emw.S22dB"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"S11", "S21", "S12", "S22"});
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevelinput", "last", 1);
    model.result("pg3").run();
    model.result("pg3").feature("rgr1").set("expr", new String[]{"emw.S22"});
    model.result("pg3").feature("rgr1").set("descr", new String[]{"S22"});
    model.result("pg3").feature("rgr1").set("unit", new String[]{"1"});
    model.result("pg3").run();
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("rgr1").set("linemarker", "cycle");
    model.result("pg7").feature("rgr1").set("markerpos", "interp");

    model.title("H \u5f2f\u6ce2\u5bfc - \u4e09\u7ef4");

    model
         .description("\u672c\u4f8b\u5728\u4e09\u7ef4\u6a21\u5f0f\u4e0b\u6a21\u62df TE10\u00a0\u6ce2\u901a\u8fc7\u6ce2\u5bfc\u4e2d 90\u00a0\u5f2f\u66f2\u90e8\u5206\u7684\u4f20\u8f93\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("h_bend_waveguide_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
