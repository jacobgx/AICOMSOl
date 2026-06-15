/*
 * magnetohydrodynamics_pump.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class magnetohydrodynamics_pump {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Electromagnetics_and_Fluids");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics("mf").create("alf1", "AmperesLawFluid");
    model.component("comp1").physics("mf").feature("alf1").selection().all();
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.component("comp1").multiphysics().create("mhd1", "Magnetohydrodynamics", 2);
    model.component("comp1").multiphysics("mhd1").set("EMForce_physics", "mf");
    model.component("comp1").multiphysics("mhd1").set("FluidFlow_physics", "spf");
    model.component("comp1").multiphysics("mhd1").selection().all();

    model.study().create("std1");
    model.study("std1").create("fstat", "FrequencyStationary");
    model.study("std1").feature("fstat").set("freq", "1000");
    model.study("std1").feature("fstat").set("solnum", "auto");
    model.study("std1").feature("fstat").set("notsolnum", "auto");
    model.study("std1").feature("fstat").set("outputmap", new String[]{});
    model.study("std1").feature("fstat").set("ngenAUX", "1");
    model.study("std1").feature("fstat").set("goalngenAUX", "1");
    model.study("std1").feature("fstat").set("ngenAUX", "1");
    model.study("std1").feature("fstat").set("goalngenAUX", "1");
    model.study("std1").feature("fstat").setSolveFor("/physics/mf", true);
    model.study("std1").feature("fstat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("fstat").setSolveFor("/multiphysics/mhd1", true);

    model.param().set("N", "10");
    model.param().descr("N", "\u7ebf\u5708\u531d\u6570");
    model.param().set("I0", "1[A]");
    model.param().descr("I0", "\u7ebf\u5708\u7535\u6d41\u5927\u5c0f");
    model.param().set("I1", "I0*exp(-i*120[deg])");
    model.param().descr("I1", "\u76f8\u4f4d 1");
    model.param().set("I2", "I0");
    model.param().descr("I2", "\u76f8\u4f4d 2");
    model.param().set("I3", "I0*exp(i*120[deg])");
    model.param().descr("I3", "\u76f8\u4f4d 3");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").repairTolType("relative");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{175, 200});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{0, -100});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 50, 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{100, 200});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{0, -100});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new int[]{20, 200});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new int[]{25, -100});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new int[]{20, 40});
    model.component("comp1").geom("geom1").feature("r4").set("base", "center");
    model.component("comp1").geom("geom1").feature("r4").set("pos", new int[]{55, 0});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("r4");
    model.component("comp1").geom("geom1").feature("copy1").set("disply", "2*100/3");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("copy2", "Copy");
    model.component("comp1").geom("geom1").feature("copy2").selection("input").set("r4");
    model.component("comp1").geom("geom1").feature("copy2").set("disply", "-2*100/3");
    model.component("comp1").geom("geom1").run("copy2");

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").coordSystem("ie1").selection().set(8);
    model.component("comp1").coordSystem("ie1").set("ScalingType", "Cylindrical");

    model.component("comp1").physics("spf").selection().set(2);

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
    model.component("comp1").material("mat1").selection().set(4, 5, 6);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u94c1");
    model.component("comp1").material("mat2").selection().set(1, 3);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1e3"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("Lithium, 200 \u00b0C");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"4[MS/m]", "0", "0", "0", "4[MS/m]", "0", "0", "0", "4[MS/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"47[W/(m*K)]", "0", "0", "0", "47[W/(m*K)]", "0", "0", "0", "47[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("dynamicviscosity", "6.12E-04[Pa*s]");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "510[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").selection().set(2);

    model.component("comp1").physics("mf").feature("alf1").selection().set(2);
    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als1").selection().set(1, 3);
    model.component("comp1").physics("mf").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("mf").feature("pc1").selection().set(2, 3, 5, 6, 8, 21, 26, 27, 29, 30);
    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").label("\u591a\u531d\u7ebf\u5708 1");
    model.component("comp1").physics("mf").feature("coil1").selection().set(6);
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", "I1");
    model.component("comp1").physics("mf").feature("coil1").set("N", "N");
    model.component("comp1").physics("mf").feature().duplicate("coil2", "coil1");
    model.component("comp1").physics("mf").feature("coil2").label("\u591a\u531d\u7ebf\u5708 2");
    model.component("comp1").physics("mf").feature("coil2").selection().set(5);
    model.component("comp1").physics("mf").feature("coil2").set("ICoil", "I2");
    model.component("comp1").physics("mf").feature().duplicate("coil3", "coil2");
    model.component("comp1").physics("mf").feature("coil3").label("\u591a\u531d\u7ebf\u5708 3");
    model.component("comp1").physics("mf").feature("coil3").selection().set(4);
    model.component("comp1").physics("mf").feature("coil3").set("ICoil", "I3");
    model.component("comp1").physics("spf").feature("fp1").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("minput_temperature", "T");
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(4);
    model.component("comp1").physics("spf").create("pfc1", "PeriodicFlowCondition", 1);
    model.component("comp1").physics("spf").feature("pfc1").selection().set(5, 6);

    model.component("comp1").multiphysics("mhd1").selection().set(2);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run("size");
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run("size1");
    model.component("comp1").mesh("mesh1").feature().remove("size2");
    model.component("comp1").mesh("mesh1").feature().remove("size3");
    model.component("comp1").mesh("mesh1").feature("dis1").selection().set(5, 6);
    model.component("comp1").mesh("mesh1").feature("dis1").set("elemcount", 40);
    model.component("comp1").mesh("mesh1").feature("dis1").set("elemratio", 25);
    model.component("comp1").mesh("mesh1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run("dis1");
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map2").set("adjustedgdistr", true);
    model.component("comp1").mesh("mesh1").feature("map2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map2").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature().remove("cr1");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1, 3, 4, 5, 6, 7);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").feature("map1").selection().remaining();
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").feature().remove("bl1");

    model.study("std1").feature("fstat").set("freq", 50);
    model.study("std1").feature("fstat").set("useparam", true);
    model.study("std1").feature("fstat").setIndex("pname", "N", 0);
    model.study("std1").feature("fstat").setIndex("plistarr", "", 0);
    model.study("std1").feature("fstat").setIndex("punit", "", 0);
    model.study("std1").feature("fstat").setIndex("pname", "N", 0);
    model.study("std1").feature("fstat").setIndex("plistarr", "", 0);
    model.study("std1").feature("fstat").setIndex("punit", "", 0);
    model.study("std1").feature("fstat").setIndex("pname", "I0", 0);
    model.study("std1").feature("fstat").setIndex("plistarr", "0.1 1 5 10 15 20 25", 0);
    model.study("std1").feature("fstat").set("pcontinuationmode", "no");
    model.study("std1").feature("fstat").set("preusesol", "yes");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").create("se1", "Segregated");
    model.sol("sol1").feature("s1").feature("se1").feature("ssDef").set("segvar", new String[]{"comp1_mf_PsiOrA"});
    model.sol("sol1").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("segvar", new String[]{"comp1_p", "comp1_u"});
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("subdamp", "0.5");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.03);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("solutionparams", "parent");
    model.result("pg1").feature("con1").set("expr", "mf.Psi");
    model.result("pg1").feature("con1").set("titletype", "none");
    model.result("pg1").feature("con1").set("number", 10);
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("color", "custom");
    model.result("pg1").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg1").feature("con1").set("resolution", "fine");
    model.result("pg1").feature("con1").set("inheritcolor", false);
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").feature("con1").feature().create("filt1", "Filter");
    model.result("pg1").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("solutionparams", "parent");
    model.result("pg2").feature("con1").set("expr", "mf.Psi");
    model.result("pg2").feature("con1").set("titletype", "none");
    model.result("pg2").feature("con1").set("number", 10);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("color", "custom");
    model.result("pg2").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg2").feature("con1").set("resolution", "fine");
    model.result("pg2").feature("con1").set("inheritcolor", false);
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg2").feature("con1").set("inheritplot", "vol1");
    model.result("pg2").feature("con1").feature().create("filt1", "Filter");
    model.result("pg2").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg2").feature("con1").feature("filt1").set("shownodespec", "on");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u901f\u5ea6 (spf)");
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.U");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u538b\u529b (spf)");
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("expr", "p");
    model.result("pg4").feature("con1").set("number", 40);
    model.result("pg4").feature("con1").set("levelrounding", false);
    model.result("pg4").feature("con1").set("smooth", "internal");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u8868\u9762");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("expr", "spf.U");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().dataset("dset1").set("frametype", "material");
    model.result("pg1").run();
    model.result("pg1").set("frametype", "material");
    model.result("pg1").run();
    model.result("pg1").feature().remove("str1");
    model.result("pg1").run();
    model.result("pg1").feature().remove("con1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result("pg3").run();
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").label("\u901f\u5ea6 (spf) 1");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u901f\u5ea6\u548c\u78c1\u901a\u5bc6\u5ea6");
    model.result("pg6").set("edges", false);
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").create("con1", "Contour");
    model.result("pg6").feature("con1").set("expr", "r*Aphi");
    model.result("pg6").feature("con1").set("levelrounding", false);
    model.result("pg6").feature("con1").set("coloring", "uniform");
    model.result("pg6").feature("con1").set("color", "black");
    model.result("pg6").feature("con1").set("colorlegend", false);
    model.result("pg6").feature("con1").set("titletype", "none");
    model.result("pg6").run();
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("titletype", "none");
    model.result("pg6").feature("arws1").set("expr", new String[]{"u", "w"});
    model.result("pg6").feature("arws1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg6").feature("arws1").set("arrowxmethod", "coord");
    model.result("pg6").feature("arws1").set("xcoord", "range(25.1,18/10,44)");
    model.result("pg6").feature("arws1").set("color", "black");
    model.result("pg6").run();
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("titletype", "custom");
    model.result("pg6").feature("surf1").set("typeintitle", false);
    model.result("pg6").feature("surf1").set("expr", "spf.U");
    model.result("pg6").feature("surf1").set("descr", "\u901f\u5ea6\u5927\u5c0f");
    model.result("pg6").feature("surf1").set("colortable", "Traffic");
    model.result("pg6").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg6").run();
    model.result("pg6").create("surf2", "Surface");
    model.result("pg6").feature("surf2").set("titletype", "custom");
    model.result("pg6").feature("surf2").set("typeintitle", false);
    model.result("pg6").feature("surf2").set("colortable", "RainbowLight");
    model.result("pg6").feature("surf2").create("sel1", "Selection");
    model.result("pg6").feature("surf2").feature("sel1").selection().set(1, 3, 7);
    model.result("pg6").run();
    model.result("pg6").create("surf3", "Surface");
    model.result("pg6").feature("surf3").set("expr", "mf.Jphi");
    model.result("pg6").feature("surf3").set("descr", "\u7535\u6d41\u5bc6\u5ea6\uff0cphi \u5206\u91cf");
    model.result("pg6").feature("surf3").set("titletype", "custom");
    model.result("pg6").feature("surf3").set("typeintitle", false);
    model.result("pg6").feature("surf3").set("colortable", "Inferno");
    model.result("pg6").feature("surf3").create("sel1", "Selection");
    model.result("pg6").feature("surf3").feature("sel1").selection().set(4, 5, 6);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").run();
    model.result("pg7").label("\u6d1b\u4f26\u5179\u529b\u548c\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg7").set("showlegendsmaxmin", true);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "mf.Jphi");
    model.result("pg7").feature("surf1").set("descr", "\u7535\u6d41\u5bc6\u5ea6\uff0cphi \u5206\u91cf");
    model.result("pg7").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg7").feature("surf1").set("colortable", "WaveLight");
    model.result("pg7").run();
    model.result("pg7").create("con1", "Contour");
    model.result("pg7").feature("con1").set("expr", "r*Aphi");
    model.result("pg7").feature("con1").set("levelrounding", false);
    model.result("pg7").feature("con1").set("coloring", "uniform");
    model.result("pg7").feature("con1").set("color", "black");
    model.result("pg7").feature("con1").set("colorlegend", false);
    model.result("pg7").feature("con1").set("titletype", "none");
    model.result("pg7").run();
    model.result("pg7").create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").set("expr", new String[]{"mf.FLtzavr", "mf.FLtzavz"});
    model.result("pg7").feature("arws1")
         .set("descr", "\u6d1b\u4f26\u5179\u529b\u8d21\u732e\uff0c\u65f6\u95f4\u5e73\u5747");
    model.result("pg7").feature("arws1").set("arrowxmethod", "coord");
    model.result("pg7").feature("arws1").set("xcoord", "range(25.1,18/10,44)");
    model.result("pg7").feature("arws1").set("ynumber", 30);
    model.result("pg7").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg7").feature("arws1").set("color", "black");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").label("\u901f\u5ea6\u548c\u78c1\u901a\u5bc6\u5ea6\uff0c\u65cb\u8f6c\u51e0\u4f55");
    model.result("pg8").set("edges", false);
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").create("vol1", "Volume");
    model.result("pg8").feature("vol1").set("expr", "mf.normB/((dom!=2)*(dom!=8)*(dom!=7))");
    model.result("pg8").feature("vol1").set("titletype", "custom");
    model.result("pg8").feature("vol1").set("typeintitle", false);
    model.result("pg8").feature("vol1").set("descractive", true);
    model.result("pg8").feature("vol1").set("descr", "\u78c1\u901a\u5bc6\u5ea6");
    model.result("pg8").feature("vol1").set("colortable", "AuroraBorealis");
    model.result("pg8").run();
    model.result("pg8").create("vol2", "Volume");
    model.result("pg8").feature("vol2").set("expr", "abs(spf.U)");
    model.result("pg8").feature("vol2").set("descractive", true);
    model.result("pg8").feature("vol2").set("descr", "\u6d41\u4f53\u901f\u5ea6");
    model.result("pg8").feature("vol2").set("titletype", "custom");
    model.result("pg8").feature("vol2").set("typeintitle", false);
    model.result("pg8").feature("vol2").set("colortable", "JupiterAuroraBorealis");
    model.result("pg8").feature("vol2").set("colortabletrans", "reverse");
    model.result("pg8").feature("vol2").create("def1", "Deform");
    model.result("pg8").feature("vol2").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg8").run();
    model.result("pg8").feature("vol2").feature("def1").set("expr", new String[]{"0", "0", "abs(w)"});
    model.result("pg8").feature("vol2").feature("def1").set("scaleactive", true);
    model.result("pg8").feature("vol2").feature("def1").set("scale", 3);
    model.result("pg8").run();
    model.result("pg8").create("con1", "Contour");
    model.result("pg8").feature("con1").set("expr", "Aphi*r");
    model.result("pg8").feature("con1").set("levelrounding", false);
    model.result("pg8").feature("con1").set("coloring", "uniform");
    model.result("pg8").feature("con1").set("color", "black");
    model.result("pg8").feature("con1").set("colorlegend", false);
    model.result("pg8").feature("con1").set("titletype", "none");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg8").run();
    model.result("pg8").run();

    model.title("\u78c1\u6d41\u4f53\u52a8\u529b\u5b66\u6cf5");

    model
         .description("\u8fd9\u4e2a\u4ecb\u7ecd\u6027\u6a21\u578b\u6f14\u793a\u78c1\u6d41\u4f53\u52a8\u529b\u5b66\u6cf5\u7684\u4f5c\u7528\u3002\u672c\u4f8b\u4e2d\u7684\u4e09\u76f8\u7ebf\u5708\u4ea7\u751f\u8fd0\u52a8\u78c1\u573a\uff0c\u6db2\u4f53\u4e2d\u7684\u6da1\u6d41\u4ea7\u751f\u53cd\u5411\u611f\u5e94\u78c1\u573a\uff0c\u5bf9\u67f1\u4e2d\u7684\u6db2\u4f53\u4ea7\u751f\u6392\u65a5\u529b\uff0c\u4ece\u800c\u4ea7\u751f\u8fd0\u52a8\u3002\u8fd9\u79cd\u7c7b\u578b\u7684\u611f\u5e94\u6cf5\u53ef\u7528\u4e8e\u9ad8\u6e29\u51b7\u5374\u7cfb\u7edf\u7b49\u8bbe\u5907\uff0c\u5176\u4e2d\u6ca1\u6709\u8fd0\u52a8\u90e8\u4ef6\uff0c\u5e76\u4e14\u6cf5\u9001\u7684\u6db2\u4f53\u4fdd\u5b58\u5728\u5bc6\u5c01\u7cfb\u7edf\u4e2d\uff0c\u8fd9\u662f\u660e\u663e\u7684\u4f18\u52bf\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("magnetohydrodynamics_pump.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
