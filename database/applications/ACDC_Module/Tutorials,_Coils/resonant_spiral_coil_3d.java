/*
 * resonant_spiral_coil_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class resonant_spiral_coil_3d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Tutorials,_Coils");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mef", "ElectricInductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/mef", true);

    model.param().set("frq", "6e6[Hz]");
    model.param().set("delta", "sqrt(2/(mu0_const*2*pi*frq*5.98e7[S/m]))");

    model.component("comp1").sorder("linear");

    model.component("comp1").curvedInterior(false);

    model.component("comp1").geom("geom1").insertFile("resonant_spiral_coil_3d_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u94dc\u8868\u9762");
    model.component("comp1").selection("sel1").set(4, 5, 6, 7, 8);
    model.component("comp1").selection("sel1").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel1").set(4, 5, 6, 7, 8);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u94dc\u57df");
    model.component("comp1").selection("sel2").set(4, 5, 6, 7, 8);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u63a5\u89e6\u8fb9\u754c");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(32);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u94dc\u548c\u63a5\u89e6\u8fb9\u754c");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel3", "sel1"});
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u94dc\u8fb9");
    model.component("comp1").selection("sel4").set(4, 5, 6, 7, 8);
    model.component("comp1").selection("sel4").geom("geom1", 3, 1, new String[]{"exterior"});
    model.component("comp1").selection("sel4").set(4, 5, 6, 7, 8);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u9988\u7ebf\u548c\u63a5\u5730");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(20, 43);
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u6f06\u819c\u8fb9\u754c");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"uni1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel5"});

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat1").label("Copper");
    model.component("comp1").material("mat1").set("family", "copper");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat1").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat1").selection().named("sel2");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("FR4 (Circuit Board)");
    model.component("comp1").material("mat2").set("family", "pcb");
    model.component("comp1").material("mat2").set("color", "custom");
    model.component("comp1").material("mat2").set("customcolor", "0 0.5019607843137255 0.25098039215686274");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0.004[S/m]", "0", "0", "0", "0.004[S/m]", "0", "0", "0", "0.004[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"18e-6[1/K]", "0", "0", "0", "18e-6[1/K]", "0", "0", "0", "18e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "1369[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "1900[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "22[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.15");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").label("FR4 (Circuit Board) 1");
    model.component("comp1").material("mat3").set("family", "pcb");
    model.component("comp1").material("mat3").set("color", "custom");
    model.component("comp1").material("mat3").set("customcolor", "0 0.5019607843137255 0.25098039215686274");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0.004[S/m]", "0", "0", "0", "0.004[S/m]", "0", "0", "0", "0.004[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"18e-6[1/K]", "0", "0", "0", "18e-6[1/K]", "0", "0", "0", "18e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "1369[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "1900[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "22[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.15");
    model.component("comp1").material("mat3").selection().geom("geom1", 2);
    model.component("comp1").material("mat3").selection().named("dif1");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]"});

    model.component("comp1").physics("mef").prop("ShapeProperty").set("order_magneticvectorpotential", 1);
    model.component("comp1").physics("mef").prop("ShapeProperty").set("order_electricpotential", 1);
    model.component("comp1").physics("mef").feature("mi1").create("ein1", "ElectricInsulation", 2);
    model.component("comp1").physics("mef").feature("mi1").feature("ein1").selection().all();
    model.component("comp1").physics("mef").feature("mi1").create("term1", "Terminal", 2);
    model.component("comp1").physics("mef").feature("mi1").feature("term1").selection().set(20);
    model.component("comp1").physics("mef").feature("mi1").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("mef").feature("mi1").feature("term1").set("V0", "5[mV]");
    model.component("comp1").physics("mef").feature("mi1").create("gnd2", "Ground", 2);
    model.component("comp1").physics("mef").feature("mi1").feature("gnd2").selection().set(43);
    model.component("comp1").physics("mef").create("alcs1", "ElectromagneticModelSolid", 3);
    model.component("comp1").physics("mef").feature("alcs1").selection().set(2, 4, 5, 6, 7, 8);
    model.component("comp1").physics("mef").create("mc1", "MagneticContinuity", 2);
    model.component("comp1").physics("mef").feature("mc1").selection().named("dif1");
    model.component("comp1").physics("mef").feature("mc1").create("ci1", "ContactImpedance", 2);
    model.component("comp1").physics("mef").feature("mc1").feature("ci1").selection().named("dif1");
    model.component("comp1").physics("mef").feature("mc1").feature("ci1").set("ds", "0.0001[mm]");
    model.component("comp1").physics("mef").feature("mc1").create("ci2", "ContactImpedance", 2);
    model.component("comp1").physics("mef").feature("mc1").feature("ci2").selection().named("sel3");
    model.component("comp1").physics("mef").feature("mc1").feature("ci2").set("ds", "0.0002[mm]");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature().move("size1", 1);
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("sel4");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 0.2);
    model.component("comp1").mesh("mesh1").run("size1");
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("size2").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", 0.5);
    model.component("comp1").mesh("mesh1").run("size2");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("uni1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "delta/2");
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").run("ftet1");

    model.result().dataset().create("mesh1", "Mesh");
    model.result().dataset("mesh1").set("mesh", "mesh1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7f51\u683c\u56fe 1");
    model.result("pg1").set("data", "mesh1");
    model.result("pg1").set("inherithide", true);
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").create("mesh1", "Mesh");
    model.result("pg1").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg1").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg1").feature("mesh1").set("meshdomain", "volume");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").label("\u7f51\u683c\u56fe");
    model.result("pg1").run();
    model.result("pg1").feature("mesh1").set("colortable", "TrafficLightClassic");
    model.result("pg1").feature("mesh1").set("colortabletrans", "reverse");
    model.result("pg1").feature("mesh1").set("filteractive", true);
    model.result("pg1").feature("mesh1").set("logfilterexpr", "y>1[mm]");
    model.result("pg1").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg1").feature("mesh1").feature("sel1").selection().named("sel2");
    model.result("pg1").run();
    model.result("pg1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq").set("plist", "range(5600000,28000,7000000)");
    model.study("std1").feature("freq").set("preusesol", "auto");
    model.study("std1").feature("freq").set("usestol", true);
    model.study("std1").feature("freq").set("stol", "1e-9");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("i1").set("linsolver", "bicgstab");
    model.sol("sol1").feature("s1").feature("i1").set("prefuntype", "right");
    model.sol("sol1").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol1").feature("s1").feature("i1").create("dp1", "DirectPreconditioner");
    model.sol("sol1").feature("s1").feature("i1").feature("dp1").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i1").feature("dp1").set("hybridization", "multi");
    model.sol("sol1").feature("s1").feature("i1").feature("dp1").set("hybridvar", new String[]{"comp1_V"});
    model.sol("sol1").feature("s1").feature("i1").create("so1", "SOR");
    model.sol("sol1").feature("s1").feature("i1").feature("so1").set("iter", 3);
    model.sol("sol1").feature("s1").feature("i1").feature("so1").set("hybridvar", new String[]{"comp1_A"});

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("expr", "real(1/mef.Y11)");
    model.component("comp1").probe().create("var2", "GlobalVariable");
    model.component("comp1").probe("var2").set("expr", "imag(1/mef.Y11)");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("B\u3001J \u548c V");
    model.result("pg3").setIndex("looplevel", 22, 0);
    model.result("pg3").set("edges", false);
    model.result("pg3").create("slc1", "Slice");
    model.result("pg3").feature("slc1").set("expr", "log(mef.normJ)");
    model.result("pg3").feature("slc1").set("quickplane", "zx");
    model.result("pg3").feature("slc1").set("quickymethod", "coord");
    model.result("pg3").feature("slc1").set("quicky", -2);
    model.result("pg3").feature("slc1").set("colortable", "Prism");
    model.result("pg3").feature("slc1").create("sel1", "Selection");
    model.result("pg3").feature("slc1").feature("sel1").selection().set(4);
    model.result("pg3").run();
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").setIndex("expr", "side(4,mef.Jx)", 0);
    model.result("pg3").feature("arws1").setIndex("expr", "side(4,mef.Jy)", 1);
    model.result("pg3").feature("arws1").setIndex("expr", "side(4,mef.Jz)", 2);
    model.result("pg3").feature("arws1").set("placement", "gausspoints");
    model.result("pg3").feature("arws1").set("arrowtype", "cone");
    model.result("pg3").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("arws1").set("logrange", 25);
    model.result("pg3").feature("arws1").set("arrowbase", "center");
    model.result("pg3").feature("arws1").create("sel1", "Selection");
    model.result("pg3").feature("arws1").feature("sel1").selection().named("sel1");
    model.result("pg3").run();
    model.result("pg3").feature("arws1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("arws1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("arws1").feature("col1").set("expr", "mef.normJ");
    model.result("pg3").feature("arws1").feature("col1").set("unit", "A/mm^2");
    model.result("pg3").feature("arws1").feature("col1").set("colortable", "GrayScale");
    model.result("pg3").run();
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", "1");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "gray");
    model.result("pg3").feature("line1").create("sel1", "Selection");
    model.result("pg3").feature("line1").feature("sel1").selection().named("sel4");
    model.result("pg3").run();
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("colortable", "Dipole");
    model.result("pg3").feature("vol1").create("sel1", "Selection");
    model.result("pg3").feature("vol1").feature("sel1").selection().set(2);
    model.result("pg3").run();
    model.result("pg3").feature("vol1").create("tran1", "Transparency");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").feature("tran1").set("uniformblending", 0.5);
    model.result("pg3").run();
    model.result("pg3").create("arwv1", "ArrowVolume");
    model.result("pg3").feature("arwv1").set("xnumber", 40);
    model.result("pg3").feature("arwv1").set("ynumber", 1);
    model.result("pg3").feature("arwv1").set("znumber", 40);
    model.result("pg3").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("arwv1").set("arrowbase", "center");
    model.result("pg3").feature("arwv1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("arwv1").feature("col1").set("expr", "mef.normB");
    model.result("pg3").feature("arwv1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("arwv1").feature("col1").set("colorlegend", false);
    model.result("pg3").run();
    model.result("pg3").run();

    model.component("comp1").view("view1").set("scenelight", false);

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u6cd5\u5411\uff08\u4f4d\u79fb\uff09\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg4").setIndex("looplevel", 22, 0);
    model.result("pg4").set("edges", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "abs(mef.mc1.ci2.nJ)");
    model.result("pg4").feature("surf1").set("unit", "A/mm^2");
    model.result("pg4").feature("surf1").set("colortable", "RainbowLightClassic");
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().named("sel3");
    model.result("pg4").run();
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", "1");
    model.result("pg4").feature("line1").set("coloring", "uniform");
    model.result("pg4").feature("line1").set("color", "gray");
    model.result("pg4").feature("line1").create("sel1", "Selection");
    model.result("pg4").feature("line1").feature("sel1").selection().named("sel4");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("|J|");
    model.result("pg5").setIndex("looplevel", 22, 0);
    model.result("pg5").set("edges", false);
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", "1");
    model.result("pg5").feature("line1").set("coloring", "uniform");
    model.result("pg5").feature("line1").set("color", "gray");
    model.result("pg5").feature("line1").create("sel1", "Selection");
    model.result("pg5").feature("line1").feature("sel1").selection().named("sel4");
    model.result("pg5").run();
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("expr", "mef.normJ");
    model.result("pg5").feature("vol1").set("unit", "A/mm^2");
    model.result("pg5").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("vol1").set("colortabletrans", "reverse");
    model.result("pg5").feature("vol1").create("sel1", "Selection");
    model.result("pg5").feature("vol1").feature("sel1").selection().named("sel2");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u9988\u7ebf\u963b\u6297");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "real(1/mef.Y11)", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "R", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "imag(1/mef.Y11)", 1);
    model.result("pg6").feature("glob1").setIndex("descr", "X", 1);
    model.result("pg6").feature("glob1").set("linewidth", 3);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg3").run();
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").run();
    model.result("pg3").feature("line1").set("titletype", "none");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").set("unit", "mV");
    model.result("pg3").feature("vol1").set("colortable", "RainbowLight");
    model.result("pg3").run();
    model.result("pg3").feature("slc1").label("\u7535\u6d41\u5bc6\u5ea6\u6a21");
    model.result("pg3").feature("slc1").set("expr", "mef.normJ");
    model.result("pg3").feature("slc1").set("colorscalemode", "logarithmic");
    model.result("pg4").run();

    model.component("comp1").view("view1").set("scenelight", true);

    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").label("\u6cd5\u5411\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg4").feature("surf1").set("titletype", "label");
    model.result("pg4").feature("surf1").set("unit", "A/m^2");
    model.result("pg4").feature("surf1").set("colortable", "ThermalDark");
    model.result("pg4").run();
    model.result("pg4").feature("line1").set("titletype", "none");
    model.result("pg5").run();
    model.result("pg5").feature("line1").set("titletype", "none");
    model.result("pg5").run();
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg4").run();
    model.result().duplicate("pg7", "pg4");
    model.result("pg7").run();
    model.result("pg7").create("surf2", "Surface");
    model.result("pg7").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("surf2").set("expr", "1");
    model.result("pg7").feature("surf2").create("tran1", "Transparency");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").feature("tran1").set("transparency", 0.8);
    model.result("pg7").run();
    model.result("pg7").feature("surf2").label("\u94dc\u8868\u9762");
    model.result("pg7").feature("surf2").set("titletype", "none");
    model.result("pg7").run();
    model.result("pg7").create("strmsl1", "StreamlineMultislice");
    model.result("pg7").feature("strmsl1").set("titletype", "none");
    model.result("pg7").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg7").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg7").feature("strmsl1").set("ycoord", 0);
    model.result("pg7").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg7").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg7").feature("strmsl1").set("udist", 0.02);
    model.result("pg7").feature("strmsl1").create("col1", "Color");
    model.result("pg7").run();
    model.result("pg7").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg7").feature("strmsl1").feature("col1").set("expr", "mef.normB");
    model.result("pg7").feature("strmsl1").feature("col1").set("unit", "uT");
    model.result("pg7").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg7").run();
    model.result("pg7").create("mslc1", "Multislice");
    model.result("pg7").feature("mslc1").set("expr", "mef.normB");
    model.result("pg7").feature("mslc1").set("unit", "uT");
    model.result("pg7").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg7").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg7").feature("mslc1").set("ycoord", 0);
    model.result("pg7").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg7").feature("mslc1").set("colortabletrans", "reverse");
    model.result("pg7").feature("mslc1").set("colortable", "Prism");
    model.result("pg7").feature("mslc1").set("colorscalemode", "logarithmic");
    model.result("pg7").feature("mslc1").create("tran1", "Transparency");
    model.result("pg7").run();
    model.result("pg7").feature("mslc1").feature("tran1").set("transparency", 0.4);

    model.title("\u8c10\u632f\u87ba\u65cb\u7ebf\u5708 - \u4e09\u7ef4");

    model
         .description("\u5f53\u7ebf\u5708\u8fbe\u5230\u8c10\u632f\u9891\u7387\u65f6\uff0c\u531d\u95f4\u7684\u7535\u5bb9\u8026\u5408\u4f1a\u5bfc\u81f4\u7ebf\u5708\u7684\u963b\u6297\u4ece\u611f\u6027\u53d8\u4e3a\u5bb9\u6027\u3002\u672c App \u5206\u6790\u4e00\u4e2a\u5305\u88f9\u5728\u8584\u73af\u6c27\u6e05\u6f06\u7edd\u7f18\u5c42\u4e2d\u7684 5 \u531d\u94dc\u87ba\u65cb\u7ebf\u5708\u5728\u8c10\u632f\u72b6\u6001\u4e0b\u7684\u8868\u73b0\uff0c\u901a\u8fc7\u4f7f\u7528\u7279\u6b8a\u7684\u8fb9\u754c\u6761\u4ef6\u6a21\u62df\u8584\u6e05\u6f06\u5c42\u4e2d\u7684\u7535\u5bb9\u8026\u5408\uff0c\u4ece\u800c\u907f\u514d\u8ba1\u7b97\u6210\u672c\u8f83\u9ad8\u7684\u4f53\u7f51\u683c\u5212\u5206\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("resonant_spiral_coil_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
