/*
 * mri_coil.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:39 by COMSOL 6.3.0.290. */
public class mri_coil {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Passive_Devices");

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
    model.study("std1").feature("freq").set("plist", "63.87[MHz]");

    model.param().set("c_value", "10[pF]");
    model.param().descr("c_value", "\u6a2a\u6863\u4e0a\u4f7f\u7528\u7684\u7535\u5bb9");
    model.param().set("r_coil", "0.24[m]");
    model.param().descr("r_coil", "\u7ebf\u5708\u534a\u5f84");
    model.param().set("h_coil", "0.3[m]");
    model.param().descr("h_coil", "\u7ebf\u5708\u9ad8\u5ea6");
    model.param().set("l_element", "0.01[m]");
    model.param().descr("l_element", "\u7535\u5bb9\u5143\u4ef6\u957f\u5ea6");

    model.component("comp1").geom("geom1").insertFile("mri_coil_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "mri_coil.mphbin");
    model.component("comp1").geom("geom1").feature("fin").set("repairtoltype", "absolute");
    model.component("comp1").geom("geom1").feature("fin").set("absrepairtol", 2.0E-9);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u7ebf\u5708");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1")
         .set(9, 10, 11, 12, 14, 16, 19, 21, 31, 33, 36, 38, 66, 67, 76, 77, 85, 87, 90, 92, 95, 97, 100, 102);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u5706");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(61);
    model.component("comp1").selection("sel2").set("groupcontang", true);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u5438\u6536\u8fb9\u754c");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(4);
    model.component("comp1").selection("sel3").set("groupcontang", true);

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().named("sel2");
    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().named("sel2");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Bleft", "abs(emw.Bx+j*emw.By)");
    model.component("comp1").variable("var1").descr("Bleft", "\u78c1\u901a\u5de6\u65cb\u5206\u91cf");
    model.component("comp1").variable("var1").set("Bright", "abs(emw.Bx-j*emw.By)");
    model.component("comp1").variable("var1").descr("Bright", "\u78c1\u901a\u53f3\u65cb\u5206\u91cf");
    model.component("comp1").variable("var1").set("BaxialratiodB", "20*log10((Bright+Bleft)/(Bright-Bleft))");
    model.component("comp1").variable("var1").descr("BaxialratiodB", "\u78c1\u901a\u8f74\u6bd4");
    model.component("comp1").variable("var1").set("intBaxialratiodB", "intop1(abs(BaxialratiodB))");
    model.component("comp1").variable("var1")
         .descr("intBaxialratiodB", "\u4eba\u4f53\u5934\u90e8\u6a21\u578b\u5468\u56f4\u78c1\u901a\u5706\u5ea6\u7684\u79ef\u5206");
    model.component("comp1").variable("var1").set("stdev", "sqrt(aveop1(emw.normE^2)-aveop1(emw.normE)^2)");
    model.component("comp1").variable("var1").descr("stdev", "\u7535\u573a\u6a21\u6807\u51c6\u5dee");

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide1")
         .set(1, 2, 3, 4, 5, 8, 23, 26, 29, 63, 64, 65, 68, 69, 70, 71);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").physics("emw").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("emw").feature("pec2").selection().named("sel1");
    model.component("comp1").physics("emw").create("pec3", "PerfectElectricConductor", 2);
    model.component("comp1").physics("emw").feature("pec3").selection().set(5, 6, 65, 78);
    model.component("comp1").physics("emw").create("lport1", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport1").selection().set(37);
    model.component("comp1").physics("emw").feature("lport1").set("V0", 200);
    model.component("comp1").physics("emw").create("lport2", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport2").selection().set(101);
    model.component("comp1").physics("emw").feature("lport2").set("PortExcitation", "on");
    model.component("comp1").physics("emw").feature("lport2").set("V0", 200);
    model.component("comp1").physics("emw").feature("lport2").set("Thetap", "pi/2");
    model.component("comp1").physics("emw").create("lelement1", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement1").selection().set(91);
    model.component("comp1").physics("emw").feature("lelement1").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement1").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement2", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement2").selection().set(96);
    model.component("comp1").physics("emw").feature("lelement2").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement2").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement3", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement3").selection().set(86);
    model.component("comp1").physics("emw").feature("lelement3").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement3").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement4", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement4").selection().set(32);
    model.component("comp1").physics("emw").feature("lelement4").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement4").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement5", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement5").selection().set(15);
    model.component("comp1").physics("emw").feature("lelement5").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement5").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement6", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement6").selection().set(20);
    model.component("comp1").physics("emw").feature("lelement6").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement6").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement7", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement7").selection().set(35);
    model.component("comp1").physics("emw").feature("lelement7").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement7").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement8", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement8").selection().set(89);
    model.component("comp1").physics("emw").feature("lelement8").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement8").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement9", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement9").selection().set(99);
    model.component("comp1").physics("emw").feature("lelement9").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement9").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement10", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement10").selection().set(94);
    model.component("comp1").physics("emw").feature("lelement10").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement10").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement11", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement11").selection().set(84);
    model.component("comp1").physics("emw").feature("lelement11").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement11").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement12", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement12").selection().set(30);
    model.component("comp1").physics("emw").feature("lelement12").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement12").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement13", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement13").selection().set(13);
    model.component("comp1").physics("emw").feature("lelement13").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement13").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement14", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement14").selection().set(18);
    model.component("comp1").physics("emw").feature("lelement14").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement14").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement15", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement15").selection().set(39);
    model.component("comp1").physics("emw").feature("lelement15").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement15").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement16", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement16").selection().set(93);
    model.component("comp1").physics("emw").feature("lelement16").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement16").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement17", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement17").selection().set(103);
    model.component("comp1").physics("emw").feature("lelement17").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement17").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement18", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement18").selection().set(98);
    model.component("comp1").physics("emw").feature("lelement18").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement18").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement19", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement19").selection().set(88);
    model.component("comp1").physics("emw").feature("lelement19").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement19").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement20", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement20").selection().set(34);
    model.component("comp1").physics("emw").feature("lelement20").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement20").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement21", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement21").selection().set(17);
    model.component("comp1").physics("emw").feature("lelement21").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement21").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("lelement22", "LumpedElement", 2);
    model.component("comp1").physics("emw").feature("lelement22").selection().set(22);
    model.component("comp1").physics("emw").feature("lelement22").set("LumpedElementType", "Capacitor");
    model.component("comp1").physics("emw").feature("lelement22").set("Celement", "c_value");
    model.component("comp1").physics("emw").create("sctr1", "Scattering", 2);
    model.component("comp1").physics("emw").feature("sctr1").selection().named("sel3");

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "c_value", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "F", 0);
    model.study("std1").feature("param").setIndex("pname", "c_value", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "F", 0);
    model.study("std1").feature("param").setIndex("punit", "pF", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(20,0.5,30)", 0);
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
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u7535\u573a, \u5bf9\u6570 (emw)");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection()
         .set(5, 6, 9, 10, 11, 12, 14, 16, 19, 21, 31, 33, 36, 38, 65, 66, 67, 76, 77, 78, 85, 87, 90, 92, 95, 97, 100, 102);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 63, 64, 70, 71);

    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "emw.normE");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection()
         .set(5, 6, 9, 10, 11, 12, 14, 16, 19, 21, 31, 33, 36, 38, 65, 66, 67, 76, 77, 78, 85, 87, 90, 92, 95, 97, 100, 102);
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg2").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf1").feature("mtrl1").set("family", "chrome");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("expr", "emw.normE");
    model.result("pg2").feature("surf2").create("sel1", "Selection");
    model.result("pg2").feature("surf2").feature("sel1").selection()
         .set(7, 8, 13, 15, 17, 18, 20, 22, 23, 24, 25, 26, 27, 28, 29, 30, 32, 34, 35, 37, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 68, 69, 72, 73, 74, 75, 79, 80, 81, 82, 83, 84, 86, 88, 89, 91, 93, 94, 96, 98, 99, 101, 103);
    model.result("pg2").feature("surf2").set("colortable", "Dipole");
    model.result("pg2").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf2").create("tran1", "Transparency");
    model.result("pg2").feature("surf2").feature("tran1").set("transparency", 0.3);

    model.component("comp1").view().create("view3", "geom1");
    model.component("comp1").view("view3").camera()
         .set("position", new double[]{-2.0341070838596513, -2.7121427784795347, 2.0341070838596513});
    model.component("comp1").view("view3").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view3").camera().set("zoomanglefull", 12.003676891326904);

    model.result("pg2").set("view", "view3");
    model.result("pg1").run();
    model.result().dataset("dset1").selection().geom("geom1", 3);
    model.result().dataset("dset1").selection().geom("geom1", 3);
    model.result().dataset("dset1").selection().set(3, 4, 5, 6);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 18, 0);
    model.result("pg3").create("slc1", "Slice");
    model.result("pg3").feature("slc1").set("quickplane", "xy");
    model.result("pg3").feature("slc1").set("quickznumber", 1);
    model.result("pg3").feature("slc1").set("expr", "emw.normB");
    model.result("pg3").feature("slc1").set("descr", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.result("pg3").run();
    model.result("pg3").create("arwv1", "ArrowVolume");
    model.result("pg3").feature("arwv1").set("expr", new String[]{"emw.Bx", "emw.By", "emw.Bz"});
    model.result("pg3").feature("arwv1").set("descr", "\u78c1\u901a\u5bc6\u5ea6");
    model.result("pg3").run();
    model.result("pg3").create("arwv2", "ArrowVolume");
    model.result("pg3").feature("arwv2").setIndex("expr", "imag(emw.Bx)", 0);
    model.result("pg3").feature("arwv2").setIndex("expr", "imag(emw.By)", 1);
    model.result("pg3").feature("arwv2").setIndex("expr", "imag(emw.Bz)", 2);
    model.result("pg3").feature("arwv2").set("color", "blue");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"intBaxialratiodB"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u4eba\u4f53\u5934\u90e8\u6a21\u578b\u5468\u56f4\u78c1\u901a\u5706\u5ea6\u7684\u79ef\u5206"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"m"});
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("expr", new String[]{"stdev"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"\u7535\u573a\u6a21\u6807\u51c6\u5dee"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"V/m"});
    model.result("pg5").run();
    model.result("pg5").run();

    model.param().set("c_value", "28[pF]");

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(5, 6);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"40"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0.9"});

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").set("plotgroup", "Default");
    model.study("std2").feature("freq").set("solnum", "auto");
    model.study("std2").feature("freq").set("notsolnum", "auto");
    model.study("std2").feature("freq").set("outputmap", new String[]{});
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").setSolveFor("/physics/emw", true);
    model.study("std2").feature("freq").set("plist", "63.87[MHz]");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u7535\u573a (emw) 1");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("mslc1", "Multislice");
    model.result("pg6").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg6").feature("mslc1").set("smooth", "internal");
    model.result("pg6").feature("mslc1").set("data", "parent");
    model.result("pg6").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg6").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").label("\u7535\u573a, \u5bf9\u6570 (emw) 1");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection()
         .set(5, 6, 9, 10, 11, 12, 14, 16, 19, 21, 31, 33, 36, 38, 65, 66, 67, 76, 77, 78, 85, 87, 90, 92, 95, 97, 100, 102);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 63, 64, 70, 71);

    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "emw.normE");
    model.result("pg7").feature("surf1").create("sel1", "Selection");
    model.result("pg7").feature("surf1").feature("sel1").selection()
         .set(5, 6, 9, 10, 11, 12, 14, 16, 19, 21, 31, 33, 36, 38, 65, 66, 67, 76, 77, 78, 85, 87, 90, 92, 95, 97, 100, 102);
    model.result("pg7").feature("surf1").set("colortable", "Dipole");
    model.result("pg7").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg7").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg7").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg7").feature("surf1").feature("mtrl1").set("family", "chrome");
    model.result("pg7").feature("surf1").set("expr", "1");
    model.result("pg7").create("surf2", "Surface");
    model.result("pg7").feature("surf2").set("expr", "emw.normE");
    model.result("pg7").feature("surf2").create("sel1", "Selection");
    model.result("pg7").feature("surf2").feature("sel1").selection()
         .set(7, 8, 13, 15, 17, 18, 20, 22, 23, 24, 25, 26, 27, 28, 29, 30, 32, 34, 35, 37, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 68, 69, 72, 73, 74, 75, 79, 80, 81, 82, 83, 84, 86, 88, 89, 91, 93, 94, 96, 98, 99, 101, 103);
    model.result("pg7").feature("surf2").set("colortable", "Dipole");
    model.result("pg7").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg7").feature("surf2").create("tran1", "Transparency");
    model.result("pg7").feature("surf2").feature("tran1").set("transparency", 0.3);

    model.component("comp1").view().create("view4", "geom1");
    model.component("comp1").view("view4").camera().set("position", new double[]{0, 0, 1.1860782167185908});
    model.component("comp1").view("view4").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view4").camera().set("zoomanglefull", 32.83953666687012);

    model.result("pg7").set("view", "view4");
    model.result("pg6").run();
    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().set(3, 4, 5, 6);
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").create("slc1", "Slice");
    model.result("pg8").run();
    model.result("pg8").set("data", "dset2");
    model.result("pg8").run();
    model.result("pg8").feature("slc1").set("expr", "emw.normB");
    model.result("pg8").feature("slc1").set("descr", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.result("pg8").feature("slc1").set("quickplane", "xy");
    model.result("pg8").feature("slc1").set("quickznumber", 1);
    model.result("pg8").run();
    model.result("pg8").create("arwv1", "ArrowVolume");
    model.result("pg8").feature("arwv1").set("expr", new String[]{"emw.Bx", "emw.By", "emw.Bz"});
    model.result("pg8").feature("arwv1").set("descr", "\u78c1\u901a\u5bc6\u5ea6");
    model.result("pg8").run();
    model.result("pg8").create("arwv2", "ArrowVolume");
    model.result("pg8").feature("arwv2").setIndex("expr", "imag(emw.Bx)", 0);
    model.result("pg8").feature("arwv2").setIndex("expr", "imag(emw.By)", 1);
    model.result("pg8").feature("arwv2").setIndex("expr", "imag(emw.Bz)", 2);
    model.result("pg8").feature("arwv2").set("color", "blue");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").set("expr", new String[]{"intBaxialratiodB"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u4eba\u4f53\u5934\u90e8\u6a21\u578b\u5468\u56f4\u78c1\u901a\u5706\u5ea6\u7684\u79ef\u5206"});
    model.result().numerical("gev1").set("unit", new String[]{"m"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset2");
    model.result().numerical("gev2").set("expr", new String[]{"stdev"});
    model.result().numerical("gev2").set("descr", new String[]{"\u7535\u573a\u6a21\u6807\u51c6\u5dee"});
    model.result().numerical("gev2").set("unit", new String[]{"V/m"});
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();

    model.title("MRI \u7b3c\u5f0f\u7ebf\u5708");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u4e00\u4e2a\u7b3c\u5f0f\u7ebf\u5708\u7684\u8bbe\u8ba1\u548c\u4f18\u5316\uff0c\u7528\u6765\u4e3a\u78c1\u5171\u632f\u6210\u50cf (MRI) \u7cfb\u7edf\u63d0\u4f9b\u5747\u5300\u7684\u78c1\u573a\u5206\u5e03\uff0c\u63d0\u9ad8\u626b\u63cf\u56fe\u50cf\u7684\u5206\u8fa8\u7387\u3002\u901a\u8fc7\u7814\u7a76\u6b63\u4ea4\u6fc0\u52b1\u548c\u8fdb\u884c\u53c2\u6570\u5316\u626b\u63cf\u786e\u5b9a\u7ebf\u5708\u96c6\u603b\u5143\u4ef6\u7684\u6700\u4f18\u503c\uff0c\u5f97\u5230\u4e86\u5747\u5300\u78c1\u573a\u3002\u5bf9\u96c6\u603b\u5143\u4ef6\u4e0a\u7684\u7535\u5bb9\u8fdb\u884c\u626b\u63cf\u7ed9\u51fa\u4e86\u5728\u6240\u9700\u62c9\u83ab\u5c14\u9891\u7387\u4e0b\u7a7a\u6c14\u9020\u5f71\u7684\u6700\u4f18\u503c\u3002\u6b64\u6a21\u578b\u8fd8\u7814\u7a76\u4e86\u52a0\u8f7d\u4eba\u8111\u9020\u5f71\u65f6\u7ebf\u5708\u7684\u6027\u80fd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("mri_coil.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
