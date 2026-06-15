/*
 * polarized_circular_ports.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:43 by COMSOL 6.3.0.290. */
public class polarized_circular_ports {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Tutorials");

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

    model.param().set("frq", "c_const/0.03[m]");
    model.param().descr("frq", "\u5de5\u4f5c\u9891\u7387");

    model.study("std1").feature("freq").set("plist", "range(0.9*frq,(1.5*frq-(0.9*frq))/10,1.5*frq)");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 0.01);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 0.1);
    model.component("comp1").geom("geom1").run("cyl1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", 0.1);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("coord1", new double[]{0, 0.01});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord2", new double[]{0, -0.01});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").selection("input").set("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").set("rot", "45 135");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").set("fin", 7, 8, 13, 14);
    model.component("comp1").geom("geom1").feature("ige1").set("ignorevtx", false);
    model.component("comp1").geom("geom1").run("ige1");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").physics("emw").create("port1", "Port", 2);
    model.component("comp1").physics("emw").feature("port1").selection().set(3);
    model.component("comp1").physics("emw").feature("port1").set("PortType", "Circular");
    model.component("comp1").physics("emw").feature("port1").create("cportv1", "CircularPortReferenceAxis", 0);
    model.component("comp1").physics("emw").feature("port1").feature("cportv1").selection().set(5, 8);
    model.component("comp1").physics("emw").create("port2", "Port", 2);
    model.component("comp1").physics("emw").feature("port2").selection().set(3);
    model.component("comp1").physics("emw").feature("port2").set("PortType", "Circular");
    model.component("comp1").physics("emw").feature("port2").create("cportv1", "CircularPortReferenceAxis", 0);
    model.component("comp1").physics("emw").feature("port2").feature("cportv1").selection().set(1, 12);
    model.component("comp1").physics("emw").create("port3", "Port", 2);
    model.component("comp1").physics("emw").feature("port3").selection().set(4);
    model.component("comp1").physics("emw").feature("port3").set("PortType", "Circular");
    model.component("comp1").physics("emw").feature("port3").create("cportv1", "CircularPortReferenceAxis", 0);
    model.component("comp1").physics("emw").feature("port3").feature("cportv1").selection().set(4, 10);
    model.component("comp1").physics("emw").create("port4", "Port", 2);
    model.component("comp1").physics("emw").feature("port4").selection().set(4);
    model.component("comp1").physics("emw").feature("port4").set("PortType", "Circular");
    model.component("comp1").physics("emw").feature("port4").create("cportv1", "CircularPortReferenceAxis", 0);
    model.component("comp1").physics("emw").feature("port4").feature("cportv1").selection().set(3, 11);

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
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"emw.S11dB", "emw.S21dB", "emw.S31dB", "emw.S41dB"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"S11", "S21", "S31", "S41"});
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
    model.component("comp1").measure().selection().set(1, 2, 5, 6);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 5, 6);

    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "emw.normE");
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().set(1, 2, 5, 6);
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
    model.result("pg4").feature("surf2").feature("sel1").selection().set(3, 4);
    model.result("pg4").feature("surf2").set("colortable", "Dipole");
    model.result("pg4").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf2").create("tran1", "Transparency");
    model.result("pg4").feature("surf2").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view3", "geom1");
    model.component("comp1").view("view3").camera()
         .set("position", new double[]{-0.1220464447270269, -0.16272859728854636, 0.1437855803448221});
    model.component("comp1").view("view3").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view3").camera().set("zoomanglefull", 43.8342809677124);

    model.result("pg4").set("view", "view3");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u7aef\u53e3 1");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "emw.normtEmode_1");
    model.result("pg5").feature("surf1").set("descr", "\u7aef\u53e3\u5207\u5411\u7535\u6a21\u573a\u6a21");
    model.result("pg5").run();
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1")
         .set("expr", new String[]{"emw.tEmodex_1", "emw.tEmodey_1", "emw.tEmodez_1"});
    model.result("pg5").feature("arws1").set("descr", "\u7aef\u53e3\u5207\u5411\u7535\u6a21\u573a");
    model.result("pg5").feature("arws1").set("arrowcount", 1000);
    model.result("pg5").feature("arws1").set("color", "black");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u7aef\u53e3 2");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "emw.normtEmode_2");
    model.result("pg6").run();
    model.result("pg6").feature("arws1").setIndex("expr", "emw.tEmodex_2", 0);
    model.result("pg6").feature("arws1").setIndex("expr", "emw.tEmodey_2", 1);
    model.result("pg6").feature("arws1").setIndex("expr", "emw.tEmodez_2", 2);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u7aef\u53e3 3");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("expr", "emw.normtEmode_3");
    model.result("pg7").run();
    model.result("pg7").feature("arws1").setIndex("expr", "emw.tEmodex_3", 0);
    model.result("pg7").feature("arws1").setIndex("expr", "emw.tEmodey_3", 1);
    model.result("pg7").feature("arws1").setIndex("expr", "emw.tEmodez_3", 2);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u7aef\u53e3 4");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("expr", "emw.normtEmode_4");
    model.result("pg8").run();
    model.result("pg8").feature("arws1").setIndex("expr", "emw.tEmodex_4", 0);
    model.result("pg8").feature("arws1").setIndex("expr", "emw.tEmodey_4", 1);
    model.result("pg8").feature("arws1").setIndex("expr", "emw.tEmodez_4", 2);
    model.result("pg8").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev1").set("expr", new String[]{"emw.S31dB"});
    model.result().numerical("gev1").set("descr", new String[]{"S31"});
    model.result().numerical("gev1").set("unit", new String[]{"1"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev2").set("expr", new String[]{"emw.S41dB"});
    model.result().numerical("gev2").set("descr", new String[]{"S41"});
    model.result().numerical("gev2").set("unit", new String[]{"1"});
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();

    model.title("\u5706\u5f62\u7aef\u53e3\u504f\u632f");

    model
         .description("\u8fd9\u4e2a\u5706\u5f62\u6ce2\u5bfc\u793a\u4f8b\u6f14\u793a\u5982\u4f55\u6fc0\u52b1\u548c\u7ec8\u6b62\u5177\u6709\u9000\u5316\u7aef\u53e3\u6a21\u5f0f\u7684\u7aef\u53e3\u3002\u7740\u91cd\u4ecb\u7ecd\u4e86\u5982\u4f55\u5bf9\u4e09\u7ef4\u5706\u5f62\u6ce2\u5bfc\u7684 TE11 \u6a21\u5f0f\u8fdb\u884c\u5efa\u6a21\u548c\u6fc0\u52b1\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("polarized_circular_ports.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
