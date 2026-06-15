/*
 * mechanical_multiport_system.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:07 by COMSOL 6.3.0.290. */
public class mechanical_multiport_system {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Elastic_Waves");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "10[kHz]", "\u6fc0\u53d1\u9891\u7387");
    model.param().set("cp", "5778[m/s]", "\u538b\u529b\u6ce2\u901f");
    model.param().set("cs", "3193.9[m/s]", "\u526a\u5207\u6ce2\u901f");
    model.param().set("kp", "2*pi*f0/cp", "\u538b\u529b\u6ce2\u6570");
    model.param().set("ks", "2*pi*f0/cs", "\u526a\u5207\u6ce2\u7684\u6ce2\u6570");
    model.param().set("k_lo", "12.429[1/m]", "10 kHz \u4e0b\u7684\u7eb5\u5411\u6a21\u5f0f\u6ce2\u6570");
    model.param().set("k_to", "27.342[1/m]", "10 kHz \u4e0b\u7684\u626d\u8f6c\u6a21\u5f0f\u6ce2\u6570");
    model.param().set("k_tr1", "208.25[1/m]", "10 kHz \u4e0b\u7684\u5e73\u79fb\u6a21\u5f0f 1 \u6ce2\u6570");
    model.param().set("k_tr2", "293.94[1/m]", "10 kHz \u4e0b\u7684\u5e73\u79fb\u6a21\u5f0f 2 \u6ce2\u6570");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "mechanical_multiport_system.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u8f93\u5165/\u8f93\u51fa 1");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u8f93\u5165/\u8f93\u51fa 2");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(6);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u8f93\u5165/\u8f93\u51fa 3");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(47);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u8f93\u5165/\u8f93\u51fa 4");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(48);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Aluminum 6063-T83");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.030e7[S/m]", "0", "0", "0", "3.030e7[S/m]", "0", "0", "0", "3.030e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23.4e-6[1/K]", "0", "0", "0", "23.4e-6[1/K]", "0", "0", "0", "23.4e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"201[W/(m*K)]", "0", "0", "0", "201[W/(m*K)]", "0", "0", "0", "201[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "69[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(23, 25);
    model.component("comp1").physics("solid").create("port1", "Port", 2);
    model.component("comp1").physics("solid").feature("port1").selection().named("sel1");
    model.component("comp1").physics("solid").feature("port1").set("PortExcitation", "off");
    model.component("comp1").physics("solid").create("port2", "Port", 2);
    model.component("comp1").physics("solid").feature("port2").selection().named("sel2");
    model.component("comp1").physics("solid").create("port3", "Port", 2);
    model.component("comp1").physics("solid").feature("port3").selection().named("sel3");
    model.component("comp1").physics("solid").create("port4", "Port", 2);
    model.component("comp1").physics("solid").feature("port4").selection().named("sel4");

    model.nodeGroup().create("grp1", "Physics", "solid");
    model.nodeGroup("grp1").placeAfter("fix1");
    model.nodeGroup("grp1").add("port1");
    model.nodeGroup("grp1").add("port2");
    model.nodeGroup("grp1").add("port3");
    model.nodeGroup("grp1").add("port4");
    model.nodeGroup("grp1").label("\u7eb5\u5411\u6a21\u5f0f");
    model.nodeGroup().duplicate("grp2", "grp1");
    model.nodeGroup("grp2").label("\u626d\u8f6c\u6a21\u5f0f");
    model.nodeGroup().duplicate("grp3", "grp2");
    model.nodeGroup("grp3").label("\u5e73\u79fb\u6a21\u5f0f 1");
    model.nodeGroup().duplicate("grp4", "grp3");
    model.nodeGroup("grp4").label("\u5e73\u79fb\u6a21\u5f0f 2");

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 1);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.05);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.3);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 5);
    model.component("comp1").mesh("mesh1").run("size");
    model.component("comp1").mesh("mesh1").create("id1", "IdenticalMesh");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group1").set(4, 9);
    model.component("comp1").mesh("mesh1").feature("id1").selection("group2").set(27, 31);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(4, 9, 27, 31);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u6a21\u5f0f");
    model.study("std1").setGenPlots(false);
    model.study("std1").create("bma", "BoundaryModeAnalysis");
    model.study("std1").feature("bma").set("modeFreq", "f0");
    model.study("std1").feature("bma").set("neigsactive", true);
    model.study("std1").feature("bma").set("neigs", 4);
    model.study("std1").feature("bma").set("shiftactive", true);
    model.study("std1").feature("bma").set("shift", "0.8*kp");
    model.study("std1").feature("bma").set("eigwhich", "lr");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("\u632f\u578b (solid)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").label("\u632f\u578b (solid)");
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("arrowcount", 20);
    model.result("pg1").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").feature("arws1").create("sel1", "Selection");
    model.result("pg1").feature("arws1").feature("sel1").selection().set(1);
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{2});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{3});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{4});
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").label("\u7814\u7a76 2 - \u9891\u57df\uff08\u7aef\u53e3\u626b\u63cf\uff09");
    model.study("std2").setGenPlots(false);
    model.study("std2").setGenConv(false);
    model.study("std2").create("bma", "BoundaryModeAnalysis");
    model.study("std2").feature("bma").set("modeFreq", "f0");
    model.study("std2").feature("bma").set("neigsactive", true);
    model.study("std2").feature("bma").set("shiftactive", true);
    model.study("std2").feature("bma").set("shift", "0.9*k_lo");
    model.study("std2").feature("bma").set("eigwhich", "lr");
    model.study("std2").feature().duplicate("bma1", "bma");
    model.study("std2").feature("bma1").set("PortName", "2");
    model.study("std2").feature().duplicate("bma2", "bma1");
    model.study("std2").feature("bma2").set("PortName", "3");
    model.study("std2").feature().duplicate("bma3", "bma2");
    model.study("std2").feature("bma3").set("PortName", "4");
    model.study("std2").feature().duplicate("bma4", "bma3");
    model.study("std2").feature("bma4").set("PortName", "5");
    model.study("std2").feature("bma4").set("shift", "0.9*k_to");
    model.study("std2").feature().duplicate("bma5", "bma4");
    model.study("std2").feature("bma5").set("PortName", "6");
    model.study("std2").feature().duplicate("bma6", "bma5");
    model.study("std2").feature("bma6").set("PortName", "7");
    model.study("std2").feature().duplicate("bma7", "bma6");
    model.study("std2").feature("bma7").set("PortName", "8");
    model.study("std2").feature().duplicate("bma8", "bma7");
    model.study("std2").feature("bma8").set("PortName", "9");
    model.study("std2").feature("bma8").set("shift", "0.9*k_tr1");
    model.study("std2").feature().duplicate("bma9", "bma8");
    model.study("std2").feature("bma9").set("PortName", "10");
    model.study("std2").feature().duplicate("bma10", "bma9");
    model.study("std2").feature("bma10").set("PortName", "11");
    model.study("std2").feature().duplicate("bma11", "bma10");
    model.study("std2").feature("bma11").set("PortName", "12");
    model.study("std2").feature().duplicate("bma12", "bma11");
    model.study("std2").feature("bma12").set("PortName", "13");
    model.study("std2").feature("bma12").set("shift", "0.9*k_tr2");
    model.study("std2").feature().duplicate("bma13", "bma12");
    model.study("std2").feature("bma13").set("PortName", "14");
    model.study("std2").feature().duplicate("bma14", "bma13");
    model.study("std2").feature("bma14").set("PortName", "15");
    model.study("std2").feature().duplicate("bma15", "bma14");
    model.study("std2").feature("bma15").set("PortName", "16");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").set("plist", "f0");

    model.component("comp1").physics("solid").prop("PortSweepSettings").set("PortSweep", "StandardSweep");

    model.param().set("PortName", "1");
    model.param().descr("PortName", "\u7aef\u53e3\u540d\u79f0\u53c2\u6570");

    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "f0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "Hz", 0);
    model.study("std2").feature("param").setIndex("pname", "f0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "Hz", 0);
    model.study("std2").feature("param").setIndex("pname", "PortName", 0);
    model.study("std2").feature("param").setIndex("plistarr", "1, 5, 9, 13", 0);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol19");
    model.sol("sol19").study("std2");
    model.sol("sol19").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol19");
    model.batch("p1").run("compute");

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset19");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").setIndex("looplevel", 4, 1);
    model.result("pg2").label("\u4f4d\u79fb (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", new String[]{"solid.disp"});
    model.result("pg2").feature("vol1").set("threshold", "manual");
    model.result("pg2").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("vol1").set("colortable", "SpectrumLight");
    model.result("pg2").feature("vol1").set("colortabletrans", "none");
    model.result("pg2").feature("vol1").set("colorscalemode", "linear");
    model.result("pg2").feature("vol1").set("resolution", "custom");
    model.result("pg2").feature("vol1").set("refine", 2);
    model.result("pg2").feature("vol1").create("def", "Deform");
    model.result("pg2").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").label("\u4f4d\u79fb (solid)");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 1, 1);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 3, 1);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 4, 1);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("abs(S)^2 - \u7aef\u53e3 1\uff08\u7eb5\u5411\uff09\u6fc0\u52b1");
    model.result("pg3").set("data", "dset19");
    model.result("pg3").setIndex("looplevelinput", "manual", 1);
    model.result("pg3").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u5165\u53e3/\u51fa\u53e3 #");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "|S|^2");
    model.result("pg3").set("ylog", true);
    model.result("pg3").set("manualgrid", true);
    model.result("pg3").set("legendpos", "center");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "abs(solid.Smatrix11)^2", 0);
    model.result("pg3").feature("glob1").setIndex("expr", "abs(solid.Smatrix51)^2", 1);
    model.result("pg3").feature("glob1").setIndex("expr", "abs(solid.Smatrix91)^2", 2);
    model.result("pg3").feature("glob1").setIndex("expr", "abs(solid.Smatrix13_1)^2", 3);
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "1");
    model.result("pg3").feature("glob1").set("linestyle", "none");
    model.result("pg3").feature("glob1").set("linemarker", "circle");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("glob2", "glob1");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").setIndex("expr", "abs(solid.Smatrix21)^2", 0);
    model.result("pg3").feature("glob2").setIndex("expr", "abs(solid.Smatrix61)^2", 1);
    model.result("pg3").feature("glob2").setIndex("expr", "abs(solid.Smatrix10_1)^2", 2);
    model.result("pg3").feature("glob2").setIndex("expr", "abs(solid.Smatrix14_1)^2", 3);
    model.result("pg3").feature("glob2").set("xdataexpr", "2");
    model.result("pg3").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").set("legend", false);
    model.result("pg3").feature().duplicate("glob3", "glob2");
    model.result("pg3").run();
    model.result("pg3").feature("glob3").setIndex("expr", "abs(solid.Smatrix31)^2", 0);
    model.result("pg3").feature("glob3").setIndex("expr", "abs(solid.Smatrix71)^2", 1);
    model.result("pg3").feature("glob3").setIndex("expr", "abs(solid.Smatrix11_1)^2", 2);
    model.result("pg3").feature("glob3").setIndex("expr", "abs(solid.Smatrix15_1)^2", 3);
    model.result("pg3").feature("glob3").set("xdataexpr", "3");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("glob4", "glob3");
    model.result("pg3").run();
    model.result("pg3").feature("glob4").setIndex("expr", "abs(solid.Smatrix41)^2", 0);
    model.result("pg3").feature("glob4").setIndex("expr", "abs(solid.Smatrix81)^2", 1);
    model.result("pg3").feature("glob4").setIndex("expr", "abs(solid.Smatrix12_1)^2", 2);
    model.result("pg3").feature("glob4").setIndex("expr", "abs(solid.Smatrix16_1)^2", 3);
    model.result("pg3").feature("glob4").set("xdataexpr", "4");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "\u7eb5\u5411", 0);
    model.result("pg3").feature("glob1").setIndex("legends", "\u626d\u8f6c", 1);
    model.result("pg3").feature("glob1").setIndex("legends", "\u6a2a\u5411 1", 2);
    model.result("pg3").feature("glob1").setIndex("legends", "\u6a2a\u5411 2", 3);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("abs(S)^2 - \u7aef\u53e3 5\uff08\u626d\u8f6c\uff09\u6fc0\u52b1");
    model.result("pg4").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg4").run();
    model.result("pg4").feature("glob1").setIndex("expr", "abs(solid.Smatrix15)^2", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "abs(solid.Smatrix55)^2", 1);
    model.result("pg4").feature("glob1").setIndex("expr", "abs(solid.Smatrix95)^2", 2);
    model.result("pg4").feature("glob1").setIndex("expr", "abs(solid.Smatrix13_5)^2", 3);
    model.result("pg4").run();
    model.result("pg4").feature("glob2").setIndex("expr", "abs(solid.Smatrix25)^2", 0);
    model.result("pg4").feature("glob2").setIndex("expr", "abs(solid.Smatrix65)^2", 1);
    model.result("pg4").feature("glob2").setIndex("expr", "abs(solid.Smatrix10_5)^2", 2);
    model.result("pg4").feature("glob2").setIndex("expr", "abs(solid.Smatrix14_5)^2", 3);
    model.result("pg4").run();
    model.result("pg4").feature("glob3").setIndex("expr", "abs(solid.Smatrix35)^2", 0);
    model.result("pg4").feature("glob3").setIndex("expr", "abs(solid.Smatrix75)^2", 1);
    model.result("pg4").feature("glob3").setIndex("expr", "abs(solid.Smatrix11_5)^2", 2);
    model.result("pg4").feature("glob3").setIndex("expr", "abs(solid.Smatrix15_5)^2", 3);
    model.result("pg4").run();
    model.result("pg4").feature("glob4").setIndex("expr", "abs(solid.Smatrix45)^2", 0);
    model.result("pg4").feature("glob4").setIndex("expr", "abs(solid.Smatrix85)^2", 1);
    model.result("pg4").feature("glob4").setIndex("expr", "abs(solid.Smatrix12_5)^2", 2);
    model.result("pg4").feature("glob4").setIndex("expr", "abs(solid.Smatrix16_5)^2", 3);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("abs(S)^2 - \u7aef\u53e3 9\uff08\u6a2a\u5411 1\uff09\u6fc0\u52b1");
    model.result("pg5").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("expr", "abs(solid.Smatrix19)^2", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "abs(solid.Smatrix59)^2", 1);
    model.result("pg5").feature("glob1").setIndex("expr", "abs(solid.Smatrix99)^2", 2);
    model.result("pg5").feature("glob1").setIndex("expr", "abs(solid.Smatrix13_9)^2", 3);
    model.result("pg5").run();
    model.result("pg5").feature("glob2").setIndex("expr", "abs(solid.Smatrix29)^2", 0);
    model.result("pg5").feature("glob2").setIndex("expr", "abs(solid.Smatrix69)^2", 1);
    model.result("pg5").feature("glob2").setIndex("expr", "abs(solid.Smatrix10_9)^2", 2);
    model.result("pg5").feature("glob2").setIndex("expr", "abs(solid.Smatrix14_9)^2", 3);
    model.result("pg5").run();
    model.result("pg5").feature("glob3").setIndex("expr", "abs(solid.Smatrix39)^2", 0);
    model.result("pg5").feature("glob3").setIndex("expr", "abs(solid.Smatrix79)^2", 1);
    model.result("pg5").feature("glob3").setIndex("expr", "abs(solid.Smatrix11_9)^2", 2);
    model.result("pg5").feature("glob3").setIndex("expr", "abs(solid.Smatrix15_9)^2", 3);
    model.result("pg5").run();
    model.result("pg5").feature("glob4").setIndex("expr", "abs(solid.Smatrix49)^2", 0);
    model.result("pg5").feature("glob4").setIndex("expr", "abs(solid.Smatrix89)^2", 1);
    model.result("pg5").feature("glob4").setIndex("expr", "abs(solid.Smatrix12_9)^2", 2);
    model.result("pg5").feature("glob4").setIndex("expr", "abs(solid.Smatrix16_9)^2", 3);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("abs(S)^2 - \u7aef\u53e3 13\uff08\u6a2a\u5411 2\uff09\u6fc0\u52b1");
    model.result("pg6").setIndex("looplevel", new int[]{4}, 1);
    model.result("pg6").run();
    model.result("pg6").feature("glob1").setIndex("expr", "abs(solid.Smatrix1_13)^2", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "abs(solid.Smatrix5_13)^2", 1);
    model.result("pg6").feature("glob1").setIndex("expr", "abs(solid.Smatrix9_13)^2", 2);
    model.result("pg6").feature("glob1").setIndex("expr", "abs(solid.Smatrix13_13)^2", 3);
    model.result("pg6").run();
    model.result("pg6").feature("glob2").setIndex("expr", "abs(solid.Smatrix2_13)^2", 0);
    model.result("pg6").feature("glob2").setIndex("expr", "abs(solid.Smatrix6_13)^2", 1);
    model.result("pg6").feature("glob2").setIndex("expr", "abs(solid.Smatrix10_13)^2", 2);
    model.result("pg6").feature("glob2").setIndex("expr", "abs(solid.Smatrix14_13)^2", 3);
    model.result("pg6").run();
    model.result("pg6").feature("glob3").setIndex("expr", "abs(solid.Smatrix3_13)^2", 0);
    model.result("pg6").feature("glob3").setIndex("expr", "abs(solid.Smatrix7_13)^2", 1);
    model.result("pg6").feature("glob3").setIndex("expr", "abs(solid.Smatrix11_13)^2", 2);
    model.result("pg6").feature("glob3").setIndex("expr", "abs(solid.Smatrix15_13)^2", 3);
    model.result("pg6").run();
    model.result("pg6").feature("glob4").setIndex("expr", "abs(solid.Smatrix4_13)^2", 0);
    model.result("pg6").feature("glob4").setIndex("expr", "abs(solid.Smatrix8_13)^2", 1);
    model.result("pg6").feature("glob4").setIndex("expr", "abs(solid.Smatrix12_13)^2", 2);
    model.result("pg6").feature("glob4").setIndex("expr", "abs(solid.Smatrix16_13)^2", 3);
    model.result("pg6").run();
    model.result("pg2").run();

    model
         .title("\u673a\u68b0\u591a\u7aef\u53e3\u7cfb\u7edf\uff1a\u5f39\u6027\u6ce2\u5728\u5c0f\u94dd\u677f\u4e2d\u7684\u4f20\u64ad");

    model
         .description("\u672c\u6559\u7a0b\u5206\u6790\u5177\u6709\u56db\u4e2a\u6ce2\u5bfc\u7ed3\u6784\u7684\u5c0f\u94dd\u677f\u7684\u632f\u52a8\u7279\u6027\u3002\u8fd9\u662f\u4e00\u4e2a\u4f4d\u4e8e\u8bbe\u5907\uff08\u5f39\u6027\u6ce2\u5728\u5176\u4e2d\u4f20\u64ad\uff09\u4e2d\u7684\u7ed3\u6784\u4ef6\u7684\u4f8b\u5b50\uff0c\u6b64\u7c7b\u8bbe\u5907\u5305\u62ec\u667a\u80fd\u626c\u58f0\u5668\u3001\u7535\u673a\u6216 MEMS \u5668\u4ef6\u7b49\u3002\u5176\u4e2d\u7684\u677f\u53ef\u4ee5\u770b\u4f5c\u662f\u4e00\u4e2a\u673a\u68b0\u591a\u7aef\u53e3\u7cfb\u7edf\u3002\u8be5\u6a21\u578b\u5728\u6ce2\u5bfc\u7ed3\u6784\u7684\u5165\u53e3/\u51fa\u53e3\u5904\u4f7f\u7528\u7aef\u53e3\u8fb9\u754c\u6761\u4ef6\uff1b\u8fd9\u4e9b\u6761\u4ef6\u4e00\u81f4\u5730\u6355\u83b7\u5e76\u5904\u7406\u4e0d\u540c\u7684\u4f20\u64ad\u5f39\u6027\u6a21\u5f0f\uff0c\u5982\u7eb5\u6ce2\u3001\u6a2a\u6ce2\u548c\u626d\u6ce2\u3002\u901a\u8fc7\u81ea\u52a8\u8ba1\u7b97\u7684\u7cfb\u7edf\u7684\u6563\u5c04\u77e9\u9635\u6765\u8868\u5f81\u5404\u79cd\u6a21\u5f0f\u7684\u900f\u5c04\u548c\u53cd\u5c04\uff0c\u4ece\u800c\u53ef\u4ee5\u975e\u5e38\u8be6\u7ec6\u5730\u63cf\u8ff0\u7ec4\u4ef6\u7684\u632f\u52a8\u7279\u6027\uff0c\u4f8b\u5982\uff0c\u4ee5\u4fbf\u968f\u540e\u7528\u4e8e\u7cfb\u7edf\u4eff\u771f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();

    model.label("mechanical_multiport_system.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
