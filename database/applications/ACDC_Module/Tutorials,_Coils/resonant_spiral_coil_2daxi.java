/*
 * resonant_spiral_coil_2daxi.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class resonant_spiral_coil_2daxi {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Tutorials,_Coils");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mef", "ElectricInductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/mef", true);

    model.param().set("frq", "6e6[Hz]");
    model.param().set("delta", "sqrt(2/(mu0_const*2*pi*frq*5.98e7[S/m]))");
    model.param().set("gap", "1e-7[m]");

    model.component("comp1").geom("geom1").scaleUnitValue(true);
    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{6, 0.5});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "gap+eps", 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{7, 0.5});
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "gap+eps", 0);
    model.component("comp1").geom("geom1").feature("r2").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r2").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r2").set("layertop", true);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("base", "center");
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{8, 0.5});
    model.component("comp1").geom("geom1").feature("r3").setIndex("layer", "gap+eps", 0);
    model.component("comp1").geom("geom1").feature("r3").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r3").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r3").set("layertop", true);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("base", "center");
    model.component("comp1").geom("geom1").feature("r4").set("pos", new double[]{9, 0.5});
    model.component("comp1").geom("geom1").feature("r4").setIndex("layer", "gap+eps", 0);
    model.component("comp1").geom("geom1").feature("r4").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r4").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r4").set("layertop", true);
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("base", "center");
    model.component("comp1").geom("geom1").feature("r5").set("pos", new double[]{10, 0.5});
    model.component("comp1").geom("geom1").feature("r5").setIndex("layer", "gap+eps", 0);
    model.component("comp1").geom("geom1").feature("r5").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r5").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r5").set("layertop", true);
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").set("size", new int[]{25, 1});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new int[]{0, -1});
    model.component("comp1").geom("geom1").run("r6");
    model.component("comp1").geom("geom1").create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("r7").set("size", new int[]{25, 50});
    model.component("comp1").geom("geom1").feature("r7").set("pos", new int[]{0, -25});
    model.component("comp1").geom("geom1").run("r7");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u94dc");
    model.component("comp1").selection("sel1").set(8, 17, 26, 35, 44);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u5c42\u89d2\u8fb9\u754c");
    model.component("comp1").selection("sel2")
         .set(4, 6, 10, 12, 13, 15, 19, 21, 22, 24, 28, 30, 31, 33, 37, 39, 40, 42, 46, 48);
    model.component("comp1").selection("sel2").geom("geom1", 2, 1, new String[]{"exterior"});
    model.component("comp1").selection("sel2")
         .set(4, 6, 10, 12, 13, 15, 19, 21, 22, 24, 28, 30, 31, 33, 37, 39, 40, 42, 46, 48);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u5c42\u4fa7\u8fb9\u754c");
    model.component("comp1").selection("sel3")
         .set(5, 7, 9, 11, 14, 16, 18, 20, 23, 25, 27, 29, 32, 34, 36, 38, 41, 43, 45, 47);
    model.component("comp1").selection("sel3").geom("geom1", 2, 1, new String[]{"exterior"});
    model.component("comp1").selection("sel3")
         .set(5, 7, 9, 11, 14, 16, 18, 20, 23, 25, 27, 29, 32, 34, 36, 38, 41, 43, 45, 47);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u6f06\u819c");
    model.component("comp1").selection("sel4")
         .set(4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 24, 25, 27, 28, 29, 30, 31, 32, 33, 34, 36, 37, 38, 39, 40, 41, 42, 43, 45, 46, 47, 48);
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").set("entitydim", 1);
    model.component("comp1").selection("dif1").set("add", new String[]{"sel3"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel2"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("FR4 (Circuit Board)");
    model.component("comp1").material("mat1").set("family", "pcb");
    model.component("comp1").material("mat1").set("color", "custom");
    model.component("comp1").material("mat1").set("customcolor", "0 0.5019607843137255 0.25098039215686274");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0.004[S/m]", "0", "0", "0", "0.004[S/m]", "0", "0", "0", "0.004[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"18e-6[1/K]", "0", "0", "0", "18e-6[1/K]", "0", "0", "0", "18e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "1369[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "1900[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "22[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.15");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat2").label("Copper");
    model.component("comp1").material("mat2").set("family", "copper");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("emissivity", "0.5");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "8940[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "126e9[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.34");
    model.component("comp1").material("mat2").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("rho0", "1.667e-8[ohm*m]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("alpha", "3.862e-3[1/K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("Tref", "293.15[K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat1").selection().named("sel4");
    model.component("comp1").material("mat1").selection()
         .set(2, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 24, 25, 27, 28, 29, 30, 31, 32, 33, 34, 36, 37, 38, 39, 40, 41, 42, 43, 45, 46, 47, 48);
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]"});
    model.component("comp1").material("mat2").selection().named("sel1");

    model.component("comp1").physics("mef").prop("ShapeProperty").set("order_magneticvectorpotential", 1);
    model.component("comp1").physics("mef").prop("ShapeProperty").set("order_electricpotential", 1);
    model.component("comp1").physics("mef").feature("mi1").create("ein1", "ElectricInsulation", 1);
    model.component("comp1").physics("mef").feature("mi1").feature("ein1").selection().all();
    model.component("comp1").physics("mef").create("alcs1", "ElectromagneticModelSolid", 2);
    model.component("comp1").physics("mef").feature("alcs1").selection()
         .set(2, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 24, 25, 27, 28, 29, 30, 31, 32, 33, 34, 36, 37, 38, 39, 40, 41, 42, 43, 45, 46, 47, 48);
    model.component("comp1").physics("mef").create("al1", "AmperesLaw", 2);
    model.component("comp1").physics("mef").feature("al1").selection().named("sel1");
    model.component("comp1").physics("mef").create("rlccg1", "RLCCoilGroup", 2);
    model.component("comp1").physics("mef").feature("rlccg1").selection().named("sel1");
    model.component("comp1").physics("mef").feature("rlccg1").set("CoilExcitation", "Voltage");
    model.component("comp1").physics("mef").feature("rlccg1").set("VCoil", "5[mV]");
    model.component("comp1").physics("mef").feature("rlccg1").set("DomainSpec", "manual");
    model.component("comp1").physics("mef").feature("rlccg1").set("DomainsList", "44 35 26 17 8");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 1);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.3);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.5);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.6);
    model.component("comp1").mesh("mesh1").run("size");
    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 1);
    model.component("comp1").mesh("mesh1").run("size1");
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run("edg1");
    model.component("comp1").mesh("mesh1").create("edg2", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg2").selection().named("dif1");
    model.component("comp1").mesh("mesh1").feature("edg2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").selection().named("dif1");
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").set("numelem", 1000);
    model.component("comp1").mesh("mesh1").run("edg2");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("sel4");
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").run("ftri1");

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq").set("plist", "range(5900000,28000,7300000)");

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("expr", "mef.RCoil_1");
    model.component("comp1").probe().create("var2", "GlobalVariable");
    model.component("comp1").probe("var2").set("expr", "mef.XCoil_1");
    model.component("comp1").probe("var2").set("unit", "\u03a9");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset3", "Solution");
    model.result().dataset("dset3").selection().geom("geom1", 1);
    model.result().dataset("dset3").selection().geom("geom1", 1);
    model.result().dataset("dset3").selection().set(24, 31, 38, 45, 52, 59, 66, 73, 80, 87, 94, 101);
    model.result().dataset().create("dset4", "Solution");
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().set(8, 17, 26, 35, 44);
    model.result().dataset().create("dset5", "Solution");
    model.result().dataset("dset5").selection().geom("geom1", 2);
    model.result().dataset("dset5").selection().geom("geom1", 2);
    model.result().dataset("dset5").selection().set(2);
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset3");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "dset4");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 270);
    model.result().dataset().create("rev3", "Revolve2D");
    model.result().dataset("rev3").set("data", "dset5");
    model.result().dataset("rev3").set("startangle", -90);
    model.result().dataset("rev3").set("revangle", 270);
    model.result().dataset().create("rev4", "Revolve2D");
    model.result().dataset("rev4").set("startangle", -90);
    model.result().dataset("rev4").set("revangle", 270);
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("J\u3001B \u548c V");
    model.result("pg2").setIndex("looplevel", 24, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "log(mef.normJ)");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().set(8, 17, 26, 35, 44);
    model.result("pg2").run();
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("colortable", "Dipole");
    model.result("pg2").run();
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "r*mef.Aphi");
    model.result("pg2").feature("con1").set("contourtype", "tubes");
    model.result("pg2").feature("con1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("con1").set("tuberadiusscale", 0.025);
    model.result("pg2").feature("con1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("con1").feature("col1").set("expr", "mef.normB");
    model.result("pg2").feature("con1").feature("col1").set("colortable", "RainbowLight");
    model.result("pg2").run();
    model.result("pg2").feature("con1").create("sel1", "Selection");
    model.result("pg2").feature("con1").feature("sel1").selection().set(1, 2, 3);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("J\u3001B \u548c V\uff0c\u4e09\u7ef4");
    model.result("pg3").set("data", "rev2");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "log(mef.normJ)");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").run();
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").set("data", "rev3");
    model.result("pg3").feature("surf2").set("colortable", "Dipole");
    model.result("pg3").feature("surf2").create("tran1", "Transparency");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("arwv1", "ArrowVolume");
    model.result("pg3").feature("arwv1").set("revcoordsys", "cylindrical");
    model.result("pg3").feature("arwv1").set("data", "rev4");
    model.result("pg3").feature("arwv1").set("xnumber", 50);
    model.result("pg3").feature("arwv1").set("arrowymethod", "coord");
    model.result("pg3").feature("arwv1").set("ycoord", "1e-4");
    model.result("pg3").feature("arwv1").set("znumber", 50);
    model.result("pg3").feature("arwv1").set("arrowtype", "cone");
    model.result("pg3").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("arwv1").set("logrange", 1000);
    model.result("pg3").feature("arwv1").set("arrowbase", "center");
    model.result("pg3").feature("arwv1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("arwv1").feature("col1").set("expr", "mef.normB");
    model.result("pg3").feature("arwv1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("arwv1").feature("col1").set("colorlegend", false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("|nJ|");
    model.result("pg4").setIndex("looplevel", 24, 0);
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", "abs(V_lm[A/m])/(2*pi*r)");
    model.result("pg4").feature("line1").set("unit", "A/mm^2");
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg4").feature("line1").set("tuberadiusscale", 0.05);
    model.result("pg4").feature("line1").set("colortable", "RainbowLightClassic");
    model.result("pg4").feature("line1").create("sel1", "Selection");
    model.result("pg4").feature("line1").feature("sel1").selection().set(24, 38, 45, 59, 66, 80, 87, 101);
    model.result("pg4").run();
    model.result("pg4").feature("line1").create("filt1", "Filter");
    model.result("pg4").run();
    model.result("pg4").feature("line1").feature("filt1").set("expr", "(s>0.01)&&(s<0.99)");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("|nJ|\uff0c\u4e09\u7ef4");
    model.result("pg5").setIndex("looplevel", 24, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "abs(V_lm[A/m])/(2*pi*r)");
    model.result("pg5").feature("surf1").set("unit", "A/mm^2");
    model.result("pg5").feature("surf1").set("colortable", "RainbowLightClassic");
    model.result("pg5").feature("surf1").create("filt1", "Filter");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").feature("filt1").set("expr", "(s>0.01)&&(s<0.99)");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u7ebf\u5708\u7535\u6d41");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "freq (Hz)");
    model.result("pg6").set("xlabelactive", false);
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "abs(mef.ICoil_1)", 0);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u9988\u7ebf\u963b\u6297");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "freq (Hz)");
    model.result("pg7").set("xlabelactive", false);
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "mef.RCoil_1", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "mef.XCoil_1", 1);
    model.result("pg7").feature("glob1").set("linewidth", 3);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result().move("pg1", 6);
    model.result("pg1").set("showlegends", false);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("linemarker", "cycle");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();

    model.title("\u8c10\u632f\u87ba\u65cb\u7ebf\u5708 - \u4e8c\u7ef4\u8f74\u5bf9\u79f0");

    model
         .description("\u5f53\u7ebf\u5708\u8fbe\u5230\u8c10\u632f\u9891\u7387\u65f6\uff0c\u531d\u95f4\u7684\u7535\u5bb9\u8026\u5408\u4f1a\u5bfc\u81f4\u7ebf\u5708\u7684\u963b\u6297\u4ece\u611f\u6027\u53d8\u4e3a\u5bb9\u6027\u3002\u672c App \u5206\u6790\u4e00\u4e2a\u5305\u88f9\u5728\u8584\u73af\u6c27\u6e05\u6f06\u7edd\u7f18\u5c42\u4e2d\u7684 5 \u531d\u94dc\u87ba\u65cb\u7ebf\u5708\u5728\u8c10\u632f\u72b6\u6001\u4e0b\u7684\u8868\u73b0\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("resonant_spiral_coil_2daxi.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
