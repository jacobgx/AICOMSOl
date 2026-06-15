/*
 * induced_voltage_moving_magnet.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:17 by COMSOL 6.3.0.290. */
public class induced_voltage_moving_magnet {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Motors_and_Generators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mf", true);

    model.param().set("f0", "4[Hz]");
    model.param().descr("f0", "\u632f\u8361\u78c1\u94c1\u7684\u9891\u7387");
    model.param().set("T0", "1/f0");
    model.param().descr("T0", "\u632f\u8361\u78c1\u94c1\u7684\u5468\u671f");
    model.param().set("t", "0[s]");
    model.param().descr("t", "\u65f6\u95f4");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{1, 2});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{0, -1});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{1, 8});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{1.1, -4});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new int[]{1, 12});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new int[]{0, -6});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new int[]{3, 12});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new int[]{1, -6});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("pos", new int[]{0, 6});
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").set("size", new int[]{4, 1});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new int[]{1, 6});
    model.component("comp1").geom("geom1").run("r6");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("r5", "r6");
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("r7").set("size", new int[]{1, 14});
    model.component("comp1").geom("geom1").feature("r7").set("pos", new int[]{4, -7});
    model.component("comp1").geom("geom1").run("r7");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r1", "r3");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input")
         .set("mir1", "r2", "r4", "r5", "r6", "r7");
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(30, 31, 33, 36);
    model.component("comp1").selection("sel1").label("\u78c1\u573a\u8fb9\u754c");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(35, 36, 37);
    model.component("comp1").selection("sel2").label("\u8fde\u7eed\u6027\u8fb9\u754c");

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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").label("BMN-35");
    model.component("comp1").material("mat2").set("family", "chrome");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"9.0[W/(m*K)]", "0", "0", "0", "9.0[W/(m*K)]", "0", "0", "0", "9.0[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7.55[g/cm^3]");
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "440[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/1.50[uohm*m]", "0", "0", "0", "1/1.50[uohm*m]", "0", "0", "0", "1/1.50[uohm*m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity").func("int1")
         .set("funcname", "Br");
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity").func("int1")
         .set("table", new String[][]{{"293.15", "1.220"}, {"353.15", "1.13"}});
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity").func("int1")
         .set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity").func("int1")
         .set("fununit", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1.05", "0", "0", "0", "1.05", "0", "0", "0", "1.05"});
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity").set("normBr", "Br(T)");
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity").addInput("temperature");
    model.component("comp1").material("mat1").selection().set(6);
    model.component("comp1").material("mat2").selection().set(11);

    model.component("comp1").physics("mf").create("mag1", "Magnet", 2);
    model.component("comp1").physics("mf").feature("mag1").selection().set(11);
    model.component("comp1").physics("mf").feature("mag1").feature("north1").selection().set(33);
    model.component("comp1").physics("mf").feature("mag1").feature("south1").selection().set(31);
    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").selection().set(6);
    model.component("comp1").physics("mf").feature("coil1").setIndex("materialType", "solid", 0);
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("N", 800);
    model.component("comp1").physics("mf").feature("coil1").set("AreaFrom", "UserDefined");
    model.component("comp1").physics("mf").feature("coil1").set("HarmonicLoss", false);
    model.component("comp1").physics("mf").feature("coil1").set("coilWindArea", "pi*(0.5[mm])^2");
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", 0);
    model.component("comp1").physics("mf").create("cont1", "Continuity", 1);
    model.component("comp1").physics("mf").feature("mi1").set("constraintOptions", "weakConstraints");
    model.component("comp1").physics("mf").feature("cont1").set("pairs", new String[]{"ap1"});
    model.component("comp1").physics("mf").feature("cont1").set("constraintOptions", "weakConstraints");

    model.component("comp1").common().create("pres1", "PrescribedDeformation");
    model.component("comp1").common("pres1").selection().all();
    model.component("comp1").common("pres1").selection().set(11);
    model.component("comp1").common("pres1")
         .set("prescribedDeformation", new String[]{"0", "0", "30[mm]*sin(2*pi*f0*t)"});
    model.component("comp1").common().create("pres2", "PrescribedDeformation");
    model.component("comp1").common("pres2").selection().set(12);
    model.component("comp1").common("pres2")
         .set("prescribedDeformation", new String[]{"0", "0", "30[mm]*sin(2*pi*f0*t)*(6[cm]-Z)/5[cm]"});
    model.component("comp1").common().create("pres3", "PrescribedDeformation");
    model.component("comp1").common("pres3").selection().set(10);
    model.component("comp1").common("pres3")
         .set("prescribedDeformation", new String[]{"0", "0", "30[mm]*sin(2*pi*f0*t)*(6[cm]+Z)/5[cm]"});

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");
    model.component("comp1").coordSystem("ie1").set("ScalingType", "Cylindrical");
    model.component("comp1").coordSystem("ie1").selection().set(1, 2, 3, 5, 7, 8, 9);

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(28, 29, 31, 32, 33, 34, 35, 36, 37);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "4[mm]");
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(9);
    model.component("comp1").mesh("mesh1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", "1[mm]");
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(28, 31, 32, 33, 34, 35, 37);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(10, 12);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(4, 6, 11);
    model.component("comp1").mesh("mesh1").create("cpe1", "CopyEdge");
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").set(12, 34);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").set(6, 13);
    model.component("comp1").mesh("mesh1").create("cpe2", "CopyEdge");
    model.component("comp1").mesh("mesh1").feature("cpe2").selection("source").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe2").selection("destination").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe2").selection("source").set(10, 29);
    model.component("comp1").mesh("mesh1").feature("cpe2").selection("destination").set(2, 8);
    model.component("comp1").mesh("mesh1").create("edg2", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg2").selection().set(1, 4, 18, 19, 21, 22, 23, 24, 25, 27);
    model.component("comp1").mesh("mesh1").feature("edg2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").create("cpe3", "CopyEdge");
    model.component("comp1").mesh("mesh1").feature("cpe3").selection("source").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe3").selection("destination").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe3").selection("source").set(1, 4);
    model.component("comp1").mesh("mesh1").feature("cpe3").selection("destination").set(7, 11);
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,T0/100,T0)");
    model.study("std1").showAutoSequences("all");
    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "1e-4");

    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");

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
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37);
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
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 101, 0);
    model.result("pg3").label("\u52a8\u7f51\u683c");
    model.result("pg3").create("mesh1", "Mesh");
    model.result("pg3").feature("mesh1").set("meshdomain", "surface");
    model.result("pg3").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg3").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg3").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg3").feature("mesh1").feature("sel1").selection().set(10, 11, 12);
    model.result("pg3").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg3").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg3").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 81, 0);
    model.result("pg1").run();
    model.result("pg1").feature("str1").active(false);
    model.result("pg1").feature("con1").active(false);
    model.result("pg1").run();
    model.result("pg1").create("mesh1", "Mesh");
    model.result("pg1").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg1").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg1").feature("mesh1").set("elemcolor", "none");
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"mf.VCoil_1"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u7ebf\u5708\u7535\u538b"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"V"});
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").label("\u7ebf\u5708\u7684\u611f\u5e94\u7535\u538b");

    model.title("\u79fb\u52a8\u78c1\u94c1\u5728\u7ebf\u5708\u4e2d\u4ea7\u751f\u611f\u5e94\u7535\u538b");

    model
         .description("\u5f53\u78c1\u94c1\u6cbf\u8f74\u5411\u7a7f\u8fc7\u7ebf\u5708\u4e2d\u5fc3\u65f6\uff0c\u4f1a\u5728\u7ebf\u5708\u7ec8\u7aef\u4e0a\u4ea7\u751f\u611f\u5e94\u7535\u538b\u3002\u8fd9\u79cd\u73b0\u8c61\u7684\u4e00\u4e2a\u5b9e\u9645\u5e94\u7528\u662f\u624b\u6447\u7535\u7b52\uff0c\u901a\u8fc7\u7528\u529b\u6765\u56de\u6447\u52a8\u624b\u7535\u7b52\uff0c\u53ef\u4ee5\u4f7f\u78c1\u94c1\u79fb\u52a8\u5e76\u901a\u8fc7\u591a\u531d\u7ebf\u5708\uff0c\u4ece\u800c\u4e3a\u7535\u6c60\u5145\u7535\u3002\u672c\u4f8b\u6a21\u62df\u78c1\u94c1\u901a\u8fc7\u7ebf\u5708\u7684\u8fd0\u52a8\u60c5\u51b5\uff0c\u5e76\u8ba1\u7b97\u7531\u6b64\u4ea7\u751f\u7684\u611f\u5e94\u7535\u538b\u3002\u7531\u4e8e\u78c1\u94c1\u7684\u4f4d\u79fb\u975e\u5e38\u660e\u663e\uff0c\u56e0\u6b64\u672c\u4f8b\u4f7f\u7528\u52a8\u7f51\u683c\u548c\u6ed1\u79fb\u7f51\u683c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("induced_voltage_moving_magnet.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
