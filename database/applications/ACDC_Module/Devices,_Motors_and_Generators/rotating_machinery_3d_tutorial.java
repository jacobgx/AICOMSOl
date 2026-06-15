/*
 * rotating_machinery_3d_tutorial.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:21 by COMSOL 6.3.0.290. */
public class rotating_machinery_3d_tutorial {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Motors_and_Generators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("rmm", "RotatingMachineryMagnetic", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/rmm", true);

    model.component("comp1").geom("geom1").insertFile("rotating_machinery_3d_tutorial_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("src");
    model.component("comp1").selection("sel1").set(11, 12, 14, 15);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("dst");
    model.component("comp1").selection("sel2").set(17, 18, 28, 33);

    model.param().set("omega", "3000[rpm]");
    model.param().descr("omega", "\u8f6c\u901f");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");
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
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat3").label("N35 (Sintered NdFeB)");
    model.component("comp1").material("mat3").set("family", "chrome");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1.05", "0", "0", "0", "1.05", "0", "0", "0", "1.05"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").set("normBr", "1.21[T]");
    model.component("comp1").material("mat2").selection().set(4, 5);

    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(3);
    model.component("comp1").selection("sel3").label("\u65cb\u8f6c\u76d8");
    model.component("comp1").selection("sel3").set(4, 5);

    model.component("comp1").material("mat2").selection().named("sel3");
    model.component("comp1").material("mat3").selection().set(2);

    model.component("comp1").physics("rmm").create("mfc1", "MagneticFluxConservation", 3);
    model.component("comp1").physics("rmm").feature("mfc1")
         .label("\u7a7a\u6c14\uff0c\u975e\u5bfc\u7535\u57df\u516c\u5f0f");
    model.component("comp1").physics("rmm").feature("mfc1").selection().set(1, 3);
    model.component("comp1").physics("rmm").create("ncmag1", "NonconductingMagnet", 3);
    model.component("comp1").physics("rmm").feature("ncmag1").selection().set(2);
    model.component("comp1").physics("rmm").feature("ncmag1").feature("north1").selection().set(10);
    model.component("comp1").physics("rmm").feature("ncmag1").feature("south1").selection().set(5);
    model.component("comp1").physics("rmm").create("gfa1", "GaugeFixingA", 3);
    model.component("comp1").physics("rmm").feature("gfa1").selection().named("sel3");
    model.component("comp1").physics("rmm").feature("gfa1").set("SetPointConstraint", true);

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").selection().all();
    model.component("comp1").common("rot1").selection().set(3, 4, 5);
    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("angularVelocity", "omega");

    model.component("comp1").physics("rmm").create("cont1", "Continuity", 2);
    model.component("comp1").physics("rmm").feature("cont1").set("pairs", new String[]{"ap1"});
    model.component("comp1").physics("rmm").create("zsp1", "ZeroMagneticScalarPotential", 0);
    model.component("comp1").physics("rmm").feature("zsp1").selection().set(1);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "2e-3");
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("ftri2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmax", "7e-4");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection()
         .set(5, 6, 7, 8, 9, 10, 21, 22, 23, 24, 25, 26, 27, 29, 30, 31, 32);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "7e-4");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(1, 2, 4, 5);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("sel3");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(21, 22, 23, 24, 25, 26, 27, 29, 30, 31, 32);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", 1.0E-4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", 7.0E-5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.3);
    model.component("comp1").mesh("mesh1").create("ftet2", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u56fa\u4f53\u94dc\u76d8");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,1/100,1/2)/omega");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (rmm)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solutionparams", "parent");
    model.result("pg1").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("mslc1").set("xcoord", "rmm.CPx");
    model.result("pg1").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("mslc1").set("ycoord", "rmm.CPy");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", "rmm.CPz");
    model.result("pg1").feature("mslc1").set("colortable", "Prism");
    model.result("pg1").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg1").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("strmsl1").set("xcoord", "rmm.CPx");
    model.result("pg1").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("strmsl1").set("ycoord", "rmm.CPy");
    model.result("pg1").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("strmsl1").set("zcoord", "rmm.CPz");
    model.result("pg1").feature("strmsl1").set("titletype", "none");
    model.result("pg1").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg1").feature("strmsl1").set("udist", 0.02);
    model.result("pg1").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg1").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("inheritcolor", false);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("data", "parent");
    model.result("pg1").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg1").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg1").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg1").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").label("\u7535\u6d41\u548c\u56fa\u4f53\u57df\u8fb9\u754c\u8868\u5f81");

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().set(5, 6, 7, 8, 9, 10, 23, 29, 30, 31, 32);
    model.result("pg2").run();
    model.result("pg2").create("arwv1", "ArrowVolume");
    model.result("pg2").feature("arwv1").set("expr", new String[]{"rmm.Jx", "rmm.Jy", "rmm.Jz"});
    model.result("pg2").feature("arwv1")
         .set("descr", "\u7535\u6d41\u5bc6\u5ea6 \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg2").feature("arwv1").set("xnumber", 10);
    model.result("pg2").feature("arwv1").set("ynumber", 10);
    model.result("pg2").feature("arwv1").set("znumber", 10);
    model.result("pg2").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("arwv1").set("logrange", 10);
    model.result("pg2").feature("arwv1").create("sel1", "Selection");
    model.result("pg2").feature("arwv1").feature("sel1").selection().named("sel3");
    model.result("pg2").run();
    model.result("pg2").feature("arwv1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("arwv1").feature("col1").set("expr", "rmm.normJ");
    model.result("pg2").run();
    model.result().numerical().create("int1", "IntVolume");
    model.result().numerical("int1").selection().named("sel3");
    model.result().numerical("int1").setIndex("expr", "rmm.Qh", 0);
    model.result().numerical("int1").setIndex("descr", "\u7535\u78c1\u4f53\u79ef\u635f\u8017\u5bc6\u5ea6", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u4f53\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "none");
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("source", "table");
    model.result("pg3").feature("tblp1").set("table", "tbl1");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").run();

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("time").setSolveFor("/physics/ec", true);

    model.component("comp1").physics("ec").selection().named("sel3");
    model.component("comp1").physics("ec").prop("ShapeProperty").set("order_electricpotential", 1);
    model.component("comp1").physics("ec").feature("cucn1").setIndex("materialType", "solid", 0);
    model.component("comp1").physics("ec").create("ecd1", "ExternalCurrentDensity", 3);
    model.component("comp1").physics("ec").feature("ecd1").selection().named("sel3");
    model.component("comp1").physics("ec").feature("ecd1").set("Je", new String[]{"rmm.Jix", "rmm.Jiy", "rmm.Jiz"});
    model.component("comp1").physics("ec").create("ein2", "ElectricInsulation", 2);
    model.component("comp1").physics("ec").feature("ein2").selection().set(26);
    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 0);
    model.component("comp1").physics("ec").feature("pot1").selection().set(27, 29);
    model.component("comp1").physics("rmm").create("ecd1", "ExternalCurrentDensity", 3);
    model.component("comp1").physics("rmm").feature("ecd1").selection().named("sel3");
    model.component("comp1").physics("rmm").feature("ecd1")
         .set("Je", new String[]{"ec.Jx-rmm.Jix", "ec.Jy-rmm.Jiy", "ec.Jz-rmm.Jiz"});

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/rmm", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std2").label("\u5c42\u53e0\u94dc\u76d8");
    model.study("std2").feature().copy("time", "std1/time");
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"rmm/ecd1"});
    model.study("std1").feature("stat").setSolveFor("/physics/ec", false);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"rmm/ecd1", "ec"});
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"rmm/ecd1"});
    model.study("std1").feature("time").setSolveFor("/physics/ec", false);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"rmm/ecd1", "ec"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u78c1\u901a\u5bc6\u5ea6 (rmm) 1");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 51, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("solutionparams", "parent");
    model.result("pg4").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("mslc1").set("xcoord", "rmm.CPx");
    model.result("pg4").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("mslc1").set("ycoord", "rmm.CPy");
    model.result("pg4").feature("mslc1").set("multiplanezmethod", "coord");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").feature("mslc1").set("zcoord", "rmm.CPz");
    model.result("pg4").feature("mslc1").set("colortable", "Prism");
    model.result("pg4").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result("pg4").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg4").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("strmsl1").set("xcoord", "rmm.CPx");
    model.result("pg4").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("strmsl1").set("ycoord", "rmm.CPy");
    model.result("pg4").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("strmsl1").set("zcoord", "rmm.CPz");
    model.result("pg4").feature("strmsl1").set("titletype", "none");
    model.result("pg4").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg4").feature("strmsl1").set("udist", 0.02);
    model.result("pg4").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg4").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("inheritcolor", false);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("data", "parent");
    model.result("pg4").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg4").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg4").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg4").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg4").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u7535\u52bf (ec)");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 51, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("solutionparams", "parent");
    model.result("pg5").feature("vol1").set("expr", "V");
    model.result("pg5").feature("vol1").set("colortable", "Dipole");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u7535\u573a (ec)");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevel", 51, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("mslc1", "Multislice");
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("solutionparams", "parent");
    model.result("pg6").feature("mslc1").set("expr", "ec.normE");
    model.result("pg6").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg6").feature("mslc1").set("xcoord", "ec.CPx");
    model.result("pg6").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg6").feature("mslc1").set("ycoord", "ec.CPy");
    model.result("pg6").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg6").feature("mslc1").set("zcoord", "ec.CPz");
    model.result("pg6").feature("mslc1").set("colortable", "Prism");
    model.result("pg6").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("data", "parent");
    model.result("pg6").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg6").feature("strmsl1").set("expr", new String[]{"ec.Ex", "ec.Ey", "ec.Ez"});
    model.result("pg6").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg6").feature("strmsl1").set("xcoord", "ec.CPx");
    model.result("pg6").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg6").feature("strmsl1").set("ycoord", "ec.CPy");
    model.result("pg6").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg6").feature("strmsl1").set("zcoord", "ec.CPz");
    model.result("pg6").feature("strmsl1").set("titletype", "none");
    model.result("pg6").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg6").feature("strmsl1").set("udist", 0.02);
    model.result("pg6").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg6").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("inheritcolor", false);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg6").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("strmsl1").set("data", "parent");
    model.result("pg6").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg6").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg6").feature("strmsl1").feature("col1").set("expr", "ec.normE");
    model.result("pg6").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg6").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg6").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg6").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u5782\u76f4\u4e8e\u7edd\u7f18\u5e73\u9762\u7684\u7535\u6d41");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 51, 0);
    model.result("pg7").create("vol1", "Volume");
    model.result("pg7").feature("vol1").set("expr", "rmm.Jz");
    model.result("pg7").feature("vol1").set("unit", "A/mm^2");
    model.result("pg7").feature("vol1").set("colortable", "WaveLight");
    model.result("pg7").feature("vol1").set("colorscalemode", "linearsymmetric");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").set("data", "dset1");
    model.result("pg7").run();
    model.result("pg7").set("data", "dset3");
    model.result().numerical().duplicate("int2", "int1");
    model.result().numerical("int2").set("data", "dset3");
    model.result().numerical("int2").set("table", "tbl1");
    model.result().numerical("int2").appendResult();
    model.result("pg3").run();
    model.result("pg3").feature("tblp1").set("legend", true);
    model.result("pg3").feature("tblp1").set("legendmethod", "manual");
    model.result("pg3").feature("tblp1").setIndex("legends", "\u56fa\u4f53\u94dc\u76d8", 0);
    model.result("pg3").feature("tblp1").setIndex("legends", "\u5c42\u53e0\u94dc\u76d8", 1);
    model.result("pg3").run();
    model.result("pg3").label("\u94dc\u76d8\u4e2d\u7684\u635f\u8017");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3")
         .set("title", "\u6709/\u65e0\u7edd\u7f18\u5c42\u7684\u94dc\u76d8\u4e2d\u7684\u635f\u8017 (W)");
    model.result("pg3").set("legendpos", "lowerright");
    model.result("pg3").run();

    model.component("comp1").physics("rmm").create("pcond1", "PassiveConductor", 3);
    model.component("comp1").physics("rmm").feature("pcond1").selection().named("sel3");

    model.study("std1").feature("stat").set("disabledphysics", new String[]{"rmm/ecd1", "ec", "rmm/pcond1"});
    model.study("std1").feature("time").set("disabledphysics", new String[]{"rmm/ecd1", "ec", "rmm/pcond1"});
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"rmm/pcond1"});
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"rmm/pcond1"});
    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/rmm", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ec", false);
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat").set("disabledphysics", new String[]{"rmm/ecd1"});
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").set("tlist", "range(0,1/100,1/2)/omega");
    model.study("std3").feature("time").setSolveFor("/physics/ec", false);
    model.study("std3").feature("time").set("useadvanceddisable", true);
    model.study("std3").feature("time").set("disabledphysics", new String[]{"rmm/ecd1"});
    model.study("std3").label("\u5e26\u65e0\u6e90\u5bfc\u4f53\u7684\u5c42\u53e0\u94dc\u76d8");
    model.study("std3").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u78c1\u901a\u5bc6\u5ea6 (rmm) 2");
    model.result("pg8").set("data", "dset5");
    model.result("pg8").setIndex("looplevel", 51, 0);
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").set("showlegendsmaxmin", true);
    model.result("pg8").feature().create("mslc1", "Multislice");
    model.result("pg8").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg8").feature("mslc1").set("solutionparams", "parent");
    model.result("pg8").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg8").feature("mslc1").set("xcoord", "rmm.CPx");
    model.result("pg8").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg8").feature("mslc1").set("ycoord", "rmm.CPy");
    model.result("pg8").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg8").feature("mslc1").set("zcoord", "rmm.CPz");
    model.result("pg8").feature("mslc1").set("colortable", "Prism");
    model.result("pg8").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg8").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg8").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg8").feature("mslc1").set("data", "parent");
    model.result("pg8").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg8").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg8").feature("strmsl1").set("xcoord", "rmm.CPx");
    model.result("pg8").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg8").feature("strmsl1").set("ycoord", "rmm.CPy");
    model.result("pg8").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg8").feature("strmsl1").set("zcoord", "rmm.CPz");
    model.result("pg8").feature("strmsl1").set("titletype", "none");
    model.result("pg8").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg8").feature("strmsl1").set("udist", 0.02);
    model.result("pg8").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg8").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("inheritcolor", false);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("data", "parent");
    model.result("pg8").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg8").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg8").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg8").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg8").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg8").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg8").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg8").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg8").run();
    model.result().numerical().duplicate("int3", "int2");
    model.result().numerical("int3").set("data", "dset5");
    model.result().numerical("int3").set("table", "tbl1");
    model.result().numerical("int3").appendResult();
    model.result("pg3").run();
    model.result("pg3").feature("tblp1")
         .setIndex("legends", "\u5e26\u65e0\u6e90\u5bfc\u4f53\u7684\u5c42\u53e0\u94dc\u76d8", 2);
    model.result("pg3").run();
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u65cb\u8f6c\u673a\u68b0\u4e09\u7ef4\u5efa\u6a21\u6559\u7a0b");

    model
         .description("\u4e00\u4e2a\u5706\u67f1\u5f62\u5747\u5300\u94dc\u8f6c\u5b50\u5728\u4e00\u4e2a\u7531\u6c38\u78c1\u4f53\u4ea7\u751f\u7684\u9759\u78c1\u573a\u4e2d\u65cb\u8f6c\u8fd0\u52a8\u65f6\uff0c\u4f1a\u5728\u8f6c\u5b50\u4e2d\u4ea7\u751f\u611f\u5e94\u6da1\u6d41\u3002\u78c1\u573a\u6e17\u900f\u6df1\u5ea6\u4ee5\u53ca\u6da1\u6d41\u5206\u5e03\u53d7\u5230\u901f\u5ea6\u96c6\u80a4\u6548\u5e94\u7ea6\u675f\u3002\u672c\u4f8b\u7b80\u8981\u4ecb\u7ecd\u5982\u4f55\u4f7f\u7528\u201c\u65cb\u8f6c\u673a\u68b0\u201d\u63a5\u53e3\u8fdb\u884c\u4e09\u7ef4\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("rotating_machinery_3d_tutorial.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
