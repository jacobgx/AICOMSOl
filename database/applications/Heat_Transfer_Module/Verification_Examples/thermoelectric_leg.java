/*
 * thermoelectric_leg.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:38 by COMSOL 6.3.0.290. */
public class thermoelectric_leg {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");

    model.component("comp1").multiphysics().create("tee1", "ThermoelectricEffect", 3);
    model.component("comp1").multiphysics("tee1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("tee1").set("EMCurrentDensity_physics", "ec");
    model.component("comp1").multiphysics("tee1").selection().all();
    model.component("comp1").multiphysics().create("emh1", "ElectromagneticHeating", 3);
    model.component("comp1").multiphysics("emh1").set("EMHeat_physics", "ec");
    model.component("comp1").multiphysics("emh1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("emh1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/tee1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/emh1", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{1, 1, 6});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 0.1, 0);
    model.component("comp1").geom("geom1").feature("blk1").set("layertop", true);
    model.component("comp1").geom("geom1").runPre("fin");

    model.param().set("T0", "0[degC]");
    model.param().descr("T0", "\u53c2\u8003\u6e29\u5ea6");
    model.param().set("J0", "0.7[A]/(1[mm])^2");
    model.param().descr("J0", "\u5411\u5185\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u78b2\u5316\u94cb - Bi2Te3");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.6[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7740[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("heatcapacity", new String[]{"154.4[J/(kg*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.1e5[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("seebeckcoefficient", new String[]{"2e-4[V/K]"});
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
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat2").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat2").selection().set(1, 3);
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("seebeckcoefficient", new String[]{"6.5e-6[V/K]"});

    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").physics("ht").feature("temp1").selection().set(3);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T0");
    model.component("comp1").physics("ec").create("gnd1", "Ground", 2);
    model.component("comp1").physics("ec").feature("gnd1").selection().set(3);
    model.component("comp1").physics("ec").create("ncd1", "NormalCurrentDensity", 2);
    model.component("comp1").physics("ec").feature("ncd1").selection().set(10);
    model.component("comp1").physics("ec").feature("ncd1").set("nJ", "J0");

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").set(10);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u52bf (ec)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("expr", "V");
    model.result("pg2").feature("vol1").set("colortable", "Dipole");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u573a (ec)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("solutionparams", "parent");
    model.result("pg3").feature("mslc1").set("expr", "ec.normE");
    model.result("pg3").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("mslc1").set("xcoord", "ec.CPx");
    model.result("pg3").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("mslc1").set("ycoord", "ec.CPy");
    model.result("pg3").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("mslc1").set("zcoord", "ec.CPz");
    model.result("pg3").feature("mslc1").set("colortable", "Prism");
    model.result("pg3").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg3").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg3").feature("strmsl1").set("expr", new String[]{"ec.Ex", "ec.Ey", "ec.Ez"});
    model.result("pg3").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("strmsl1").set("xcoord", "ec.CPx");
    model.result("pg3").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("strmsl1").set("ycoord", "ec.CPy");
    model.result("pg3").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("strmsl1").set("zcoord", "ec.CPz");
    model.result("pg3").feature("strmsl1").set("titletype", "none");
    model.result("pg3").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg3").feature("strmsl1").set("udist", 0.02);
    model.result("pg3").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg3").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("inheritcolor", false);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("data", "parent");
    model.result("pg3").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg3").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg3").feature("strmsl1").feature("col1").set("expr", "ec.normE");
    model.result("pg3").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg3").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("unit", "\u00b0C");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg4").feature().create("iso1", "Isosurface");
    model.result("pg4").feature("iso1").set("solutionparams", "parent");
    model.result("pg4").feature("iso1").set("number", 10);
    model.result("pg4").feature("iso1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("iso1").set("smooth", "internal");
    model.result("pg4").feature("iso1").set("showsolutionparams", "on");
    model.result("pg4").feature("iso1").set("data", "parent");
    model.result("pg4").label("\u7b49\u6e29\u7ebf (ht)");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("iso1").set("unit", "\u00b0C");
    model.result("pg4").run();
    model.result("pg4").create("arwv1", "ArrowVolume");
    model.result("pg4").feature("arwv1").set("xnumber", 2);
    model.result("pg4").feature("arwv1").set("ynumber", 2);
    model.result("pg4").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg4").run();
    model.result("pg2").run();

    model.title("\u70ed\u7535\u81c2");

    model
         .description("\u672c\u4f8b\u6c42\u89e3\u70ed\u7535\u81c2\u4e2d\u7684\u4f20\u70ed\u548c\u7535\u573a\uff0c\u5176\u4e2d\u7684\u88c5\u7f6e\u4f7f\u7528\u73c0\u5c14\u5e16\u6548\u5e94\u6765\u5b9e\u73b0\u51b7\u5374\u3002");

    model.label("thermoelectric_leg.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
