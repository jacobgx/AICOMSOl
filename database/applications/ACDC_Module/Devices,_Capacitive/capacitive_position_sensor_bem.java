/*
 * capacitive_position_sensor_bem.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:14 by COMSOL 6.3.0.290. */
public class capacitive_position_sensor_bem {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Capacitive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("esbe", "ElectrostaticsBoundaryElements", "geom1");

    model.study().create("std1");
    model.study("std1").create("stssw", "StationarySourceSweep");
    model.study("std1").feature("stssw").set("solnum", "auto");
    model.study("std1").feature("stssw").set("notsolnum", "auto");
    model.study("std1").feature("stssw").set("outputmap", new String[]{});
    model.study("std1").feature("stssw").set("ngenAUX", "1");
    model.study("std1").feature("stssw").set("goalngenAUX", "1");
    model.study("std1").feature("stssw").set("ngenAUX", "1");
    model.study("std1").feature("stssw").set("goalngenAUX", "1");
    model.study("std1").feature("stssw").setSolveFor("/physics/esbe", true);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"11[cm]", "11[cm]", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "1[cm]", 2);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "5[mm]");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"1[cm]", "11[cm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"1[cm]", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("fullsize", new int[]{5, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"2[cm]", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").run("wp1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").run();

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
    model.component("comp1").material("mat2").label("Nylon");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.39215686274509803, 0.9803921568627451});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.39215686274509803, 0.39215686274509803, 0.7843137254901961});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("lighting", "phong");
    model.component("comp1").material("mat2").set("shininess", 500);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "1700[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"4", "0", "0", "0", "4", "0", "0", "0", "4"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"280e-6[1/K]", "0", "0", "0", "280e-6[1/K]", "0", "0", "0", "280e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "1150[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.26[W/(m*K)]", "0", "0", "0", "0.26[W/(m*K)]", "0", "0", "0", "0.26[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "2[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.4");
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat1").selection().set();
    model.component("comp1").material("mat1").selection().allVoids();

    model.component("comp1").physics("esbe").create("ccn2", "ChargeConservation", 3);
    model.component("comp1").physics("esbe").feature("ccn2").selection().set(1);
    model.component("comp1").physics("esbe").create("gnd1", "Ground", 2);
    model.component("comp1").physics("esbe").feature("gnd1").selection().set(3);
    model.component("comp1").physics("esbe").create("term1", "Terminal", 2);
    model.component("comp1").physics("esbe").feature("term1").selection().set(6);
    model.component("comp1").physics("esbe").create("term2", "Terminal", 2);
    model.component("comp1").physics("esbe").feature("term2").selection().set(7);
    model.component("comp1").physics("esbe").create("term3", "Terminal", 2);
    model.component("comp1").physics("esbe").feature("term3").selection().set(8);
    model.component("comp1").physics("esbe").create("term4", "Terminal", 2);
    model.component("comp1").physics("esbe").feature("term4").selection().set(9);
    model.component("comp1").physics("esbe").create("term5", "Terminal", 2);
    model.component("comp1").physics("esbe").feature("term5").selection().set(10);

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").physics("esbe").prop("ShapeProperty").set("shapeorder", "p11");

    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("dDef").active(true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("grid1", "Grid3D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("data", "dset1");
    model.result().dataset("grid1").set("par1", "x");
    model.result().dataset("grid1").set("par2", "y");
    model.result().dataset("grid1").set("par3", "z");
    model.result().dataset("grid1").set("parmin1", -0.11);
    model.result().dataset("grid1").set("parmax1", 0.22);
    model.result().dataset("grid1").set("parmin2", -0.11000000000000004);
    model.result().dataset("grid1").set("parmax2", 0.22000000000000008);
    model.result().dataset("grid1").set("parmin3", -0.01);
    model.result().dataset("grid1").set("parmax3", 0.02);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "grid1");
    model.result("pg1").setIndex("looplevel", 5, 0);
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"esbe.V"});
    model.result("pg1").feature("mslc1").set("solutionparams", "parent");
    model.result("pg1").feature("mslc1").set("colortable", "Dipole");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").label("\u7535\u52bf (esbe)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").create("strmsl1", "StreamlineMultislice");
    model.result("pg1").feature("strmsl1").set("expr", new String[]{"esbe.Ex", "esbe.Ey", "esbe.Ez"});
    model.result("pg1").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg1").feature("strmsl1").set("titletype", "none");
    model.result("pg1").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg1").feature("strmsl1").set("udist", "0.02");
    model.result("pg1").feature("strmsl1").set("smooth", "internal");
    model.result("pg1").feature("strmsl1").set("maxlen", "0.4");
    model.result("pg1").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg1").feature("strmsl1").set("inheritcolor", false);
    model.result("pg1").feature("strmsl1").create("col1", "Color");
    model.result("pg1").feature("strmsl1").feature("col1").set("expr", new String[]{"esbe.V"});
    model.result("pg1").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"esbe.V"});
    model.result("pg1").feature("surf1").set("data", "dset1");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("titletype", "none");
    model.result("pg1").feature("surf1").set("colortable", "Dipole");
    model.result("pg1").feature("surf1").set("smooth", "material");
    model.result("pg1").feature("surf1").set("inheritplot", "mslc1");
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", new String[]{"1"});
    model.result("pg1").feature("line1").set("data", "dset1");
    model.result("pg1").feature("line1").set("titletype", "none");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "black");
    model.result("pg1").feature("line1").set("solutionparams", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "grid1");
    model.result("pg2").setIndex("looplevel", 5, 0);
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", new String[]{"esbe.normE"});
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mslc1").set("colorcalibration", "-0.8");
    model.result("pg2").feature("mslc1").set("smooth", "internal");
    model.result("pg2").label("\u7535\u573a\u6a21 (esbe)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"esbe.Ex", "esbe.Ey", "esbe.Ez"});
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", "0.02");
    model.result("pg2").feature("strmsl1").set("smooth", "internal");
    model.result("pg2").feature("strmsl1").set("maxlen", "0.4");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", new String[]{"esbe.normE"});
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorcalibration", "-0.8");
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", new String[]{"1"});
    model.result("pg2").feature("line1").set("data", "dset1");
    model.result("pg2").feature("line1").set("titletype", "none");
    model.result("pg2").feature("line1").set("coloring", "uniform");
    model.result("pg2").feature("line1").set("color", "black");
    model.result("pg2").feature("line1").set("solutionparams", "parent");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset1");
    model.result().evaluationGroup("eg1").label(" (esbe)");
    model.result().evaluationGroup("eg1").label("\u9006\u9ea6\u514b\u65af\u97e6\u7535\u5bb9 (dset1, esbe)");
    model.result().evaluationGroup("eg1").create("gmev1", "EvalGlobalMatrix");
    model.result().evaluationGroup("eg1").feature("gmev1")
         .label("\u9006\u9ea6\u514b\u65af\u97e6\u7535\u5bb9 (dset1, esbe)");
    model.result().evaluationGroup("eg1").feature("gmev1").set("expr", "root.comp1.esbe.Cinv");
    model.result().evaluationGroup("eg1").feature("gmev1").set("outerdataseries", "none");
    model.result().evaluationGroup("eg1").feature("gmev1").set("dataseries", "sum");

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").label("\u96c6\u603b\u53c2\u6570 (dset1, esbe)");
    model.nodeGroup("grp1").set("type", "evaluationgroup");
    model.nodeGroup("grp1").add("evaluationgroup", "eg1");

    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").set("data", "dset1");
    model.result().evaluationGroup("eg2").label(" (esbe)");
    model.result().evaluationGroup("eg2").label("\u9ea6\u514b\u65af\u97e6\u7535\u5bb9 (dset1, esbe)");
    model.result().evaluationGroup("eg2").create("gmev1", "EvalGlobalMatrix");
    model.result().evaluationGroup("eg2").feature("gmev1")
         .label("\u9ea6\u514b\u65af\u97e6\u7535\u5bb9 (dset1, esbe)");
    model.result().evaluationGroup("eg2").feature("gmev1").set("expr", "root.comp1.esbe.Cinv");
    model.result().evaluationGroup("eg2").feature("gmev1").set("outerdataseries", "none");
    model.result().evaluationGroup("eg2").feature("gmev1").set("dataseries", "sum");
    model.result().evaluationGroup("eg2").feature("gmev1").set("trans", "inverse");

    model.nodeGroup("grp1").add("evaluationgroup", "eg2");

    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup().create("eg3", "EvaluationGroup");
    model.result().evaluationGroup("eg3").set("data", "dset1");
    model.result().evaluationGroup("eg3").label(" (esbe)");
    model.result().evaluationGroup("eg3").label("\u4e92\u7535\u5bb9 (dset1, esbe)");
    model.result().evaluationGroup("eg3").create("gmev1", "EvalGlobalMatrix");
    model.result().evaluationGroup("eg3").feature("gmev1").label("\u4e92\u7535\u5bb9 (dset1, esbe)");
    model.result().evaluationGroup("eg3").feature("gmev1").set("expr", "root.comp1.esbe.Cinv");
    model.result().evaluationGroup("eg3").feature("gmev1").set("outerdataseries", "none");
    model.result().evaluationGroup("eg3").feature("gmev1").set("dataseries", "sum");
    model.result().evaluationGroup("eg3").feature("gmev1").set("trans", "invmaxwellmutual");

    model.nodeGroup("grp1").add("evaluationgroup", "eg3");

    model.result().evaluationGroup("eg3").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("strmsl1").active(false);
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("xnumber", "0");
    model.result("pg1").feature("mslc1").set("znumber", "0");
    model.result("pg1").run();
    model.result().evaluationGroup("eg2").feature("gmev1").set("unit", "1/pF");
    model.result().evaluationGroup("eg3").feature("gmev1").set("unit", "1/pF");
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup("eg3").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "none");
    model.result("pg3").create("tbls1", "TableSurface");
    model.result("pg3").feature("tbls1").set("source", "evaluationgroup");
    model.result("pg3").feature("tbls1").set("evaluationgroup", "eg3");
    model.result("pg3").run();
    model.result("pg3").feature("tbls1").set("dataformat", "cells");
    model.result("pg3").feature("tbls1").set("function", "discrete");
    model.result("pg3").run();
    model.result("pg3").label("\u4e92\u7535\u5bb9\u77e9\u9635");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u4e92\u7535\u5bb9\u77e9\u9635 [pF]");
    model.result("pg3").run();

    model.param().set("L", "2[cm]");
    model.param().descr("L", "\u4f4d\u79fb");

    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("type", "surface");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"25[cm]", "5[cm]", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "8[cm]", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"-35[cm]", "3[cm]", "0"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", "2[cm]", 2);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("blk2")
         .set("pos", new String[]{"-35[cm]+10[cm]", "3[cm]", "2[cm]"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"-35[cm]", "3[cm]", "2[cm]"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").common().create("pres1", "PrescribedDeformationDeformedGeometry");
    model.component("comp1").common("pres1").selection().all();

    model.component("comp1").geom("geom1").run();

    model.component("comp1").common("pres1").selection().geom("geom1", 2);
    model.component("comp1").common("pres1").selection().set(1, 2, 3, 4, 5, 6);
    model.component("comp1").common("pres1").set("prescribedDeformation", new String[]{"L", "0", "0"});

    model.component("comp1").physics("esbe").create("fp1", "FloatingPotential", 2);
    model.component("comp1").physics("esbe").feature("fp1").selection().set(1, 2, 3, 4, 5, 6);
    model.component("comp1").physics("esbe").selection().set(0, 1);

    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "L", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "L", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(2,2,28)", 0);
    model.study("std1").feature("param").setIndex("punit", "cm", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset().create("grid2", "Grid3D");
    model.result().dataset("grid2").set("source", "data");
    model.result().dataset("grid2").set("data", "dset2");
    model.result().dataset("grid2").set("par1", "x");
    model.result().dataset("grid2").set("par2", "y");
    model.result().dataset("grid2").set("par3", "z");
    model.result().dataset("grid2").set("parmin1", -0.8100000000000002);
    model.result().dataset("grid2").set("parmax1", 0.5700000000000001);
    model.result().dataset("grid2").set("parmin2", -0.11000000000000001);
    model.result().dataset("grid2").set("parmax2", 0.22000000000000003);
    model.result().dataset("grid2").set("parmin3", -0.1);
    model.result().dataset("grid2").set("parmax3", 0.2);
    model.result().create("pg4", "PlotGroup3D");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").set("data", "grid2");
    model.result("pg4").setIndex("looplevel", 5, 0);
    model.result("pg4").setIndex("looplevel", 14, 1);
    model.result("pg4").create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("expr", new String[]{"esbe.V"});
    model.result("pg4").feature("mslc1").set("solutionparams", "parent");
    model.result("pg4").feature("mslc1").set("colortable", "Dipole");
    model.result("pg4").feature("mslc1").set("smooth", "internal");
    model.result("pg4").label("\u7535\u52bf (esbe) 1");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").create("strmsl1", "StreamlineMultislice");
    model.result("pg4").feature("strmsl1").set("expr", new String[]{"esbe.Ex", "esbe.Ey", "esbe.Ez"});
    model.result("pg4").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg4").feature("strmsl1").set("titletype", "none");
    model.result("pg4").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg4").feature("strmsl1").set("udist", "0.02");
    model.result("pg4").feature("strmsl1").set("smooth", "internal");
    model.result("pg4").feature("strmsl1").set("maxlen", "0.4");
    model.result("pg4").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg4").feature("strmsl1").set("inheritcolor", false);
    model.result("pg4").feature("strmsl1").create("col1", "Color");
    model.result("pg4").feature("strmsl1").feature("col1").set("expr", new String[]{"esbe.V"});
    model.result("pg4").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"esbe.V"});
    model.result("pg4").feature("surf1").set("data", "dset2");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("titletype", "none");
    model.result("pg4").feature("surf1").set("colortable", "Dipole");
    model.result("pg4").feature("surf1").set("smooth", "material");
    model.result("pg4").feature("surf1").set("inheritplot", "mslc1");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", new String[]{"1"});
    model.result("pg4").feature("line1").set("data", "dset2");
    model.result("pg4").feature("line1").set("titletype", "none");
    model.result("pg4").feature("line1").set("coloring", "uniform");
    model.result("pg4").feature("line1").set("color", "black");
    model.result("pg4").feature("line1").set("solutionparams", "parent");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "grid2");
    model.result("pg5").setIndex("looplevel", 5, 0);
    model.result("pg5").setIndex("looplevel", 14, 1);
    model.result("pg5").create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("expr", new String[]{"esbe.normE"});
    model.result("pg5").feature("mslc1").set("solutionparams", "parent");
    model.result("pg5").feature("mslc1").set("colortable", "Prism");
    model.result("pg5").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("mslc1").set("colorcalibration", "-0.8");
    model.result("pg5").feature("mslc1").set("smooth", "internal");
    model.result("pg5").label("\u7535\u573a\u6a21 (esbe) 1");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").create("strmsl1", "StreamlineMultislice");
    model.result("pg5").feature("strmsl1").set("expr", new String[]{"esbe.Ex", "esbe.Ey", "esbe.Ez"});
    model.result("pg5").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg5").feature("strmsl1").set("titletype", "none");
    model.result("pg5").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg5").feature("strmsl1").set("udist", "0.02");
    model.result("pg5").feature("strmsl1").set("smooth", "internal");
    model.result("pg5").feature("strmsl1").set("maxlen", "0.4");
    model.result("pg5").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg5").feature("strmsl1").set("inheritcolor", false);
    model.result("pg5").feature("strmsl1").create("col1", "Color");
    model.result("pg5").feature("strmsl1").feature("col1").set("expr", new String[]{"esbe.normE"});
    model.result("pg5").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg5").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg5").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("strmsl1").feature("col1").set("colorcalibration", "-0.8");
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", new String[]{"1"});
    model.result("pg5").feature("line1").set("data", "dset2");
    model.result("pg5").feature("line1").set("titletype", "none");
    model.result("pg5").feature("line1").set("coloring", "uniform");
    model.result("pg5").feature("line1").set("color", "black");
    model.result("pg5").feature("line1").set("solutionparams", "parent");
    model.result().evaluationGroup().create("eg4", "EvaluationGroup");
    model.result().evaluationGroup("eg4").set("data", "dset2");
    model.result().evaluationGroup("eg4").label(" (esbe)");
    model.result().evaluationGroup("eg4").label("\u9006\u9ea6\u514b\u65af\u97e6\u7535\u5bb9 (dset2, esbe)");
    model.result().evaluationGroup("eg4").create("gmev1", "EvalGlobalMatrix");
    model.result().evaluationGroup("eg4").feature("gmev1")
         .label("\u9006\u9ea6\u514b\u65af\u97e6\u7535\u5bb9 (dset2, esbe)");
    model.result().evaluationGroup("eg4").feature("gmev1").set("expr", "root.comp1.esbe.Cinv");
    model.result().evaluationGroup("eg4").feature("gmev1").set("outerdataseries", "none");
    model.result().evaluationGroup("eg4").feature("gmev1").set("dataseries", "sum");

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").label("\u96c6\u603b\u53c2\u6570 (dset2, esbe)");
    model.nodeGroup("grp2").set("type", "evaluationgroup");
    model.nodeGroup("grp2").add("evaluationgroup", "eg4");

    model.result().evaluationGroup("eg4").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg4").run();
    model.result().evaluationGroup().create("eg5", "EvaluationGroup");
    model.result().evaluationGroup("eg5").set("data", "dset2");
    model.result().evaluationGroup("eg5").label(" (esbe)");
    model.result().evaluationGroup("eg5").label("\u9ea6\u514b\u65af\u97e6\u7535\u5bb9 (dset2, esbe)");
    model.result().evaluationGroup("eg5").create("gmev1", "EvalGlobalMatrix");
    model.result().evaluationGroup("eg5").feature("gmev1")
         .label("\u9ea6\u514b\u65af\u97e6\u7535\u5bb9 (dset2, esbe)");
    model.result().evaluationGroup("eg5").feature("gmev1").set("expr", "root.comp1.esbe.Cinv");
    model.result().evaluationGroup("eg5").feature("gmev1").set("outerdataseries", "none");
    model.result().evaluationGroup("eg5").feature("gmev1").set("dataseries", "sum");
    model.result().evaluationGroup("eg5").feature("gmev1").set("trans", "inverse");

    model.nodeGroup("grp2").add("evaluationgroup", "eg5");

    model.result().evaluationGroup("eg5").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg5").run();
    model.result().evaluationGroup().create("eg6", "EvaluationGroup");
    model.result().evaluationGroup("eg6").set("data", "dset2");
    model.result().evaluationGroup("eg6").label(" (esbe)");
    model.result().evaluationGroup("eg6").label("\u4e92\u7535\u5bb9 (dset2, esbe)");
    model.result().evaluationGroup("eg6").create("gmev1", "EvalGlobalMatrix");
    model.result().evaluationGroup("eg6").feature("gmev1").label("\u4e92\u7535\u5bb9 (dset2, esbe)");
    model.result().evaluationGroup("eg6").feature("gmev1").set("expr", "root.comp1.esbe.Cinv");
    model.result().evaluationGroup("eg6").feature("gmev1").set("outerdataseries", "none");
    model.result().evaluationGroup("eg6").feature("gmev1").set("dataseries", "sum");
    model.result().evaluationGroup("eg6").feature("gmev1").set("trans", "invmaxwellmutual");

    model.nodeGroup("grp2").add("evaluationgroup", "eg6");

    model.result().evaluationGroup("eg6").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("eg6").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 5, 0);
    model.result("pg6").setIndex("looplevel", 14, 1);
    model.result("pg6").label("\u53d8\u5f62\u51e0\u4f55");
    model.result("pg6").create("mesh1", "Mesh");
    model.result("pg6").feature("mesh1").set("meshdomain", "surface");
    model.result("pg6").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg6").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg6").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg6").feature("mesh1").feature("sel1").selection().set(1, 2, 3, 4, 5, 6);
    model.result("pg6").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg6").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg6").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").active(false);
    model.result("pg4").run();
    model.result().dataset("grid2").set("parmin1", -0.2);
    model.result().dataset("grid2").set("parmax1", 0.2);
    model.result().dataset("grid2").set("res1", 100);
    model.result("pg4").run();
    model.result("pg4").feature("strmsl1").active(false);
    model.result("pg4").run();
    model.result("pg4").feature("mslc1").set("xnumber", "0");
    model.result("pg4").feature("mslc1").set("znumber", "0");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 1, 1);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("mslc1").set("expr", "log10(esbe.V)");
    model.result("pg4").feature("mslc1").set("rangecoloractive", true);
    model.result("pg4").feature("mslc1").set("rangecolormin", -4);
    model.result("pg4").feature("mslc1").set("rangecolormax", 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 5, 1);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 9, 1);
    model.result("pg4").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u7535\u5bb9\u6bd4\u8f83");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevelinput", "first", 0);
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "\u7535\u5bb9\u7684\u76f8\u5bf9\u53d8\u5316");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1")
         .setIndex("expr", "with(1,esbe.Cinv11)/withsol('sol2',with(1,esbe.Cinv11),setval(L,0.02))", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "\u7ec8\u7aef 1", 0);
    model.result("pg7").feature("glob1")
         .setIndex("expr", "with(5,esbe.Cinv55)/withsol('sol2',with(5,esbe.Cinv55),setval(L,0.02))", 1);
    model.result("pg7").feature("glob1").setIndex("descr", "\u7ec8\u7aef 5", 1);
    model.result("pg7").feature("glob1")
         .setIndex("expr", "with(5,esbe.Cinv55)/with(1,esbe.Cinv11)/withsol('sol2',with(5,esbe.Cinv55)/with(1,esbe.Cinv11),setval(L,0.02))", 2);
    model.result("pg7").feature("glob1")
         .setIndex("descr", "\u7ec8\u7aef 1 \u4e0e 5 \u7684\u7535\u5bb9\u6bd4\u7387", 2);
    model.result("pg7").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg7").feature("glob1").set("legendmethod", "manual");
    model.result("pg7").feature("glob1").setIndex("legends", "\u7ec8\u7aef 1 \u7684\u53d8\u5316", 0);
    model.result("pg7").feature("glob1").setIndex("legends", "\u7ec8\u7aef 5 \u7684\u53d8\u5316", 1);
    model.result("pg7").feature("glob1")
         .setIndex("legends", "\u7ec8\u7aef 1 \u4e0e 5 \u7684\u76f8\u5bf9\u53d8\u5316", 2);
    model.result("pg7").run();
    model.result("pg4").run();
    model.result("pg4").run();

    model.title("\u7535\u5bb9\u5f0f\u4f4d\u7f6e\u4f20\u611f\u5668\u7684\u8fb9\u754c\u5143\u5efa\u6a21");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u89e3\u91ca\u5982\u4f55\u901a\u8fc7\u7a33\u6001\u6e90\u626b\u63cf \u7814\u7a76\u6765\u63d0\u53d6\u96c6\u603b\u77e9\u9635\u3002\u5176\u4e2d\u4f7f\u7528\u4e94\u7ec8\u7aef\u7cfb\u7edf\u7684\u7535\u5bb9\u77e9\u9635\u6765\u63a8\u65ad\u91d1\u5c5e\u7269\u4f53\u7684\u4f4d\u7f6e\uff0c\u7c7b\u4f3c\u4e8e\u73b0\u5b9e\u4e16\u754c\u4e2d\u7684\u7535\u5bb9\u5f0f\u4f4d\u7f6e\u4f20\u611f\u5668\u3002\n\n\u672c\u4f8b\u8bf4\u660e\u4e86\u9759\u7535\uff0c\u8fb9\u754c\u5143 \u63a5\u53e3\u652f\u6301\u7684\u8fb9\u754c\u5143\u6cd5 (BEM) \u7684\u4f7f\u7528\u3002");

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

    model.label("capacitive_position_sensor_bem.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
