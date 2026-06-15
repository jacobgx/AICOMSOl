/*
 * chip_cooling.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:34 by COMSOL 6.3.0.290. */
public class chip_cooling {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Tutorials,_Forced_and_Natural_Convection");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("n_fins", "4", "\u5f15\u811a\u6570\uff0cx \u65b9\u5411");
    model.param().set("L_chip", "40[mm]", "\u82af\u7247\u5927\u5c0f");
    model.param().set("H_chip", "4[mm]", "\u82af\u7247\u9ad8\u5ea6");
    model.param().set("P0", "10[W]", "\u7535\u5b50\u82af\u7247\u8017\u6563\u7684\u603b\u529f\u7387");
    model.param().set("h0", "10[W/m^2/K]", "\u4f20\u70ed\u7cfb\u6570");
    model.param().set("W_channel", "8[cm]", "\u901a\u9053\u5bbd\u5ea6");
    model.param().set("D_channel", "30[cm]", "\u901a\u9053\u6df1\u5ea6");
    model.param().set("H_channel", "6[cm]", "\u901a\u9053\u9ad8\u5ea6");
    model.param().set("U0", "0.1[m/s]", "\u5e73\u5747\u5165\u53e3\u6d41\u901f");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"L_chip", "L_chip", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "H_chip", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "-H_chip"});
    model.component("comp1").geom("geom1").run("blk1");
    model.geom()
         .load(new String[]{"part1"}, "Heat_Transfer_Module\\Heat_Sinks\\heat_sink_straight_fins.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "n_fins_x", "n_fins");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "X_fins_bottom", "3[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "X_fins_top", "2[mm]");
    model.component("comp1").geom("geom1").feature("pi1").set("workplanepart", "wp11");
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new String[]{"-5[mm]", "-5[mm]", "0"});
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_csel9.dom", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_difsel1", true);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").label("Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat2").label("Silica glass");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.99);
    model.component("comp1").material("mat2").set("roughness", 0.02);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "703[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"3.75", "0", "0", "0", "3.75", "0", "0", "0", "3.75"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2203[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "73.1[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.17");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.45", "0", "0", "0", "1.45", "0", "0", "0", "1.45"});

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").material("mat2").selection().set(3);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(3);
    model.component("comp1").selection("sel1").label("\u82af\u7247");
    model.component("comp1").selection("sel1").set(3);

    model.component("comp1").material("mat2").selection().named("sel1");

    model.component("comp1").common().create("ampr1", "AmbientProperties");

    model.component("comp1").physics("ht").create("hs1", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs1").selection().named("sel1");
    model.component("comp1").physics("ht").feature("hs1").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("hs1").set("P0", "P0");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("geom1_pi1_difsel1");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "h0");
    model.component("comp1").physics("ht").feature("hf1").set("Text_src", "root.comp1.ampr1.T_amb");

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
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").create("sel1", "Selection");
    model.result("pg1").feature("vol1").feature("sel1").selection().named("sel1");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("vol2", "vol1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol2").feature("sel1").selection().named("geom1_pi1_csel9_dom");
    model.result("pg1").run();
    model.result("pg1").feature("vol2").set("titletype", "none");
    model.result("pg1").feature("vol2").set("inheritplot", "vol1");
    model.result("pg1").feature("vol2").create("filt1", "Filter");
    model.result("pg1").run();
    model.result("pg1").feature("vol2").feature("filt1").set("expr", "x>0.02");
    model.result("pg1").run();

    model.component("comp1").physics("ht").create("tc1", "ThermalContact", 2);
    model.component("comp1").physics("ht").feature("tc1").selection().set(15);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("\u82af\u7247/\u6563\u70ed\u5668\u754c\u9762");
    model.component("comp1").selection("sel2").set(15);

    model.component("comp1").physics("ht").feature("tc1").selection().named("sel2");
    model.component("comp1").physics("ht").feature("tc1").set("ContactModel", "EquThinLayer");
    model.component("comp1").physics("ht").feature("tc1").set("Specify", "LayerConductivityandThickness");
    model.component("comp1").physics("ht").feature("tc1").set("ds", "50[um]");

    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat3").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat3").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").label("Air");
    model.component("comp1").material("mat3").set("family", "air");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat3").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat3").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat3").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat3").materialType("nonSolid");
    model.component("comp1").material("mat3").selection().geom("geom1", 2);
    model.component("comp1").material("mat3").selection().named("sel2");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("Thermal grease");
    model.component("comp1").material("mat4").set("family", "concrete");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "2600[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "1200[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"3[W/(m*K)]", "0", "0", "0", "3[W/(m*K)]", "0", "0", "0", "3[W/(m*K)]"});
    model.component("comp1").material("mat4").selection().geom("geom1", 2);
    model.component("comp1").material("mat4").selection().named("sel2");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"W_channel", "D_channel", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "H_channel", 2);
    model.component("comp1").geom("geom1").feature("blk2")
         .set("pos", new String[]{"-(W_channel-40[mm])/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", "-80[mm]", 1);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().duplicate("mat5", "mat3");
    model.component("comp1").material("mat5").selection().geom("geom1", 3);
    model.component("comp1").material("mat5").selection().set(1);

    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(3);
    model.component("comp1").selection("sel3").label("\u7a7a\u6c14");
    model.component("comp1").selection("sel3").set(1);

    model.component("comp1").material("mat5").selection().named("sel3");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.component("comp1").physics("spf").selection().named("sel3");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2);

    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").label("\u5165\u53e3");
    model.component("comp1").selection("sel4").set(2);

    model.component("comp1").physics("spf").feature("inl1").selection().named("sel4");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "U0");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(5);

    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").label("\u51fa\u53e3");
    model.component("comp1").selection("sel5").set(5);

    model.component("comp1").physics("spf").feature("out1").selection().named("sel5");
    model.component("comp1").physics("ht").feature("fluid1").selection().named("sel3");
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 2);
    model.component("comp1").physics("ht").feature("ifl1").selection().named("sel4");
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl1").selection().named("sel5");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);

    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);

    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");

    model.component("comp1").mesh("mesh1").autoMeshSize(9);
    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u58c1\u6e29");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg2").feature("surf1").feature("sel1").selection()
         .set(1, 3, 4, 6, 7, 9, 11, 12, 13, 14, 15, 16, 22, 24, 25, 26, 27, 28, 29, 31, 32, 33, 34, 35, 37, 39, 40, 41, 42, 43, 44);
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("expr", "nitf1.T");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature("vol1").feature().create("sel1", "Selection");
    model.result("pg2").feature("vol1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg2").feature("vol1").feature("sel1").selection().set(2, 3, 4, 5, 6, 7);
    model.result("pg2").feature("vol1").set("inheritplot", "surf1");
    model.result("pg2").feature().create("arwv1", "ArrowVolume");
    model.result("pg2").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg2").feature("arwv1").set("solutionparams", "parent");
    model.result("pg2").feature("arwv1").set("expr", new String[]{"nitf1.ux", "nitf1.uy", "nitf1.uz"});
    model.result("pg2").feature("arwv1").set("xnumber", 30);
    model.result("pg2").feature("arwv1").set("ynumber", 30);
    model.result("pg2").feature("arwv1").set("znumber", 30);
    model.result("pg2").feature("arwv1").set("arrowtype", "cone");
    model.result("pg2").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg2").feature("arwv1").set("data", "parent");
    model.result("pg2").feature("arwv1").feature().create("col1", "Color");
    model.result("pg2").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg2").feature("arwv1").feature("col1").set("expr", "spf.U");
    model.result("pg2").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg2").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg2").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg2").run();

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 1, 2, 4, 5);

    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("arwv1").feature("filt1").set("expr", "spf.U>1.2*nitf1.Uave");
    model.result("pg2").run();
    model.result("pg2").run();

    return model;
  }

  public static Model run2(Model model) {
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u901a\u9053\u58c1\u548c\u6563\u70ed\u5668\u7684\u6e29\u5ea6");
    model.result("pg3").set("showlegendsunit", false);
    model.result("pg3").run();
    model.result("pg3").feature().remove("arwv1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();

    model.component("comp1").physics().create("rad", "SurfaceToSurfaceRadiation", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/rad", true);

    model.component("comp1").physics("rad").selection().named("geom1_pi1_difsel1");
    model.component("comp1").physics("rad").selection()
         .set(1, 3, 4, 6, 7, 9, 11, 12, 13, 14, 15, 16, 22, 24, 25, 26, 27, 28, 29, 31, 32, 33, 34, 35, 37, 39, 40, 41, 42, 43, 44);
    model.component("comp1").physics("rad").create("opac1", "Opacity", 3);
    model.component("comp1").physics("rad").feature("opac1").selection().allVoids();

    model.component("comp1").view("view1").set("hidestatus", "ignore");

    model.component("comp1").multiphysics().create("htrad1", "HeatTransferWithSurfaceToSurfaceRadiation", 2);

    model.study("std1").feature("stat").setSolveFor("/multiphysics/htrad1", true);

    model.component("comp1").multiphysics("htrad1").selection().all();
    model.component("comp1").multiphysics("htrad1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("htrad1").set("Rad_physics", "rad");

    model.component("comp1").view("view1").set("hidestatus", "hide");

    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").label("\u6563\u70ed\u5668\u58c1");
    model.component("comp1").material("mat6").selection().geom("geom1", 2);
    model.component("comp1").material("mat6").selection().named("geom1_pi1_difsel1");
    model.component("comp1").material("mat6").propertyGroup("def").set("emissivity", new String[]{"0.9"});
    model.component("comp1").material().create("mat7", "Common");
    model.component("comp1").material("mat7").label("\u901a\u9053\u58c1");
    model.component("comp1").material("mat7").selection().geom("geom1", 2);

    model.component("comp1").view("view1").set("hidestatus", "ignore");

    model.component("comp1").material("mat7").selection().set(1, 3, 4, 44);

    model.component("comp1").view("view1").set("hidestatus", "hide");

    model.component("comp1").material("mat7").propertyGroup("def").set("emissivity", new String[]{"0.85"});

    model.study("std1").label("\u7814\u7a76 1\uff1a\u65e0\u8f90\u5c04");
    model.study("std1").feature("stat").setSolveFor("/physics/rad", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/htrad1", false);
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/rad", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/htrad1", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u6709\u8f90\u5c04");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("solutionparams", "parent");
    model.result("pg4").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("vol1").set("smooth", "internal");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u901f\u5ea6 (spf)");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("slc1", "Slice");
    model.result("pg5").feature("slc1").label("\u5207\u9762");
    model.result("pg5").feature("slc1").set("showsolutionparams", "on");
    model.result("pg5").feature("slc1").set("expr", "spf.U");
    model.result("pg5").feature("slc1").set("smooth", "internal");
    model.result("pg5").feature("slc1").set("showsolutionparams", "on");
    model.result("pg5").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset2");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(1, 3, 4, 6, 7, 9, 11, 12, 13, 14, 15, 16, 22, 24, 25, 26, 27, 28, 29, 31, 32, 33, 34, 35, 37, 39, 40, 41, 42, 43, 44);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u538b\u529b (spf)");
    model.result("pg6").set("data", "surf1");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("expr", "p");
    model.result("pg6").feature("surf1").set("colortable", "Dipole");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u8868\u9762\u8f90\u5c04\u5ea6 (rad)");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").feature().create("slit1", "SurfaceSlit");
    model.result("pg7").feature("slit1").set("showsolutionparams", "on");
    model.result("pg7").feature("slit1").set("upexpr", "rad.Ju");
    model.result("pg7").feature("slit1").set("downexpr", "rad.Jd");
    model.result("pg7").feature("slit1").set("smooth", "internal");
    model.result("pg7").feature("slit1").set("showsolutionparams", "on");
    model.result("pg7").feature("slit1").set("data", "parent");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1) 1");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").feature().create("surf1", "Surface");
    model.result("pg8").feature("surf1").label("\u58c1\u6e29");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("solutionparams", "parent");
    model.result("pg8").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg8").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg8").feature("surf1").set("smooth", "internal");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("data", "parent");
    model.result("pg8").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg8").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg8").feature("surf1").feature("sel1").selection()
         .set(1, 3, 4, 6, 7, 9, 11, 12, 13, 14, 15, 16, 22, 24, 25, 26, 27, 28, 29, 31, 32, 33, 34, 35, 37, 39, 40, 41, 42, 43, 44);
    model.result("pg8").feature().create("vol1", "Volume");
    model.result("pg8").feature("vol1").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg8").feature("vol1").set("showsolutionparams", "on");
    model.result("pg8").feature("vol1").set("solutionparams", "parent");
    model.result("pg8").feature("vol1").set("expr", "nitf1.T");
    model.result("pg8").feature("vol1").set("smooth", "internal");
    model.result("pg8").feature("vol1").set("showsolutionparams", "on");
    model.result("pg8").feature("vol1").set("data", "parent");
    model.result("pg8").feature("vol1").feature().create("sel1", "Selection");
    model.result("pg8").feature("vol1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg8").feature("vol1").feature("sel1").selection().set(2, 3, 4, 5, 6, 7);
    model.result("pg8").feature("vol1").set("inheritplot", "surf1");
    model.result("pg8").feature().create("arwv1", "ArrowVolume");
    model.result("pg8").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg8").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg8").feature("arwv1").set("solutionparams", "parent");
    model.result("pg8").feature("arwv1").set("expr", new String[]{"nitf1.ux", "nitf1.uy", "nitf1.uz"});
    model.result("pg8").feature("arwv1").set("xnumber", 30);
    model.result("pg8").feature("arwv1").set("ynumber", 30);
    model.result("pg8").feature("arwv1").set("znumber", 30);
    model.result("pg8").feature("arwv1").set("arrowtype", "cone");
    model.result("pg8").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg8").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg8").feature("arwv1").set("data", "parent");
    model.result("pg8").feature("arwv1").feature().create("col1", "Color");
    model.result("pg8").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg8").feature("arwv1").feature("col1").set("expr", "spf.U");
    model.result("pg8").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg8").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("vol1").create("tran1", "Transparency");
    model.result("pg4").run();
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("arwv1").feature("filt1").set("expr", "spf.U>1.2*nitf1.Uave");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9")
         .label("\u901a\u9053\u58c1\u548c\u6563\u70ed\u5668\u7684\u6e29\u5ea6\uff08\u6709\u8f90\u5c04\uff09");
    model.result("pg9").set("showlegendsunit", false);
    model.result("pg9").run();
    model.result("pg9").feature().remove("arwv1");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").label("\u63a5\u89e6\u6e29\u5ea6 (ht)");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").feature().create("slit1", "SurfaceSlit");
    model.result("pg10").feature("slit1").set("solutionparams", "parent");
    model.result("pg10").feature("slit1").set("updescractive", true);
    model.result("pg10").feature("slit1").set("upexpr", "ht.Tu");
    model.result("pg10").feature("slit1").set("updescr", "\u4e0a\u4fa7\u6e29\u5ea6");
    model.result("pg10").feature("slit1").set("downdescractive", true);
    model.result("pg10").feature("slit1").set("downexpr", "ht.Td");
    model.result("pg10").feature("slit1").set("downdescr", "\u4e0b\u4fa7\u6e29\u5ea6");
    model.result("pg10").feature("slit1").set("colortable", "HeatCameraLight");
    model.result("pg10").feature("slit1").set("smooth", "internal");
    model.result("pg10").feature("slit1").set("showsolutionparams", "on");
    model.result("pg10").feature("slit1").set("data", "parent");
    model.result("pg10").feature("slit1").feature().create("sel1", "Selection");
    model.result("pg10").feature("slit1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg10").feature("slit1").feature("sel1").selection().set(20);
    model.result("pg10").label("\u63a5\u89e6\u6e29\u5ea6 (ht)");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg8").run();

    model.title("\u7535\u5b50\u82af\u7247\u51b7\u5374");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u4f7f\u7528\u201c\u96f6\u4ef6\u5e93\u201d\u4e2d\u7684\u6563\u70ed\u5668\u51e0\u4f55\uff0c\u4ecb\u7ecd\u5728\u7814\u7a76\u7535\u5b50\u82af\u7247\u51b7\u5374\u65f6\u7684\u4e0d\u540c\u4f20\u70ed\u5efa\u6a21\u65b9\u6cd5\u3002\n\n\u5728\u7b2c\u4e00\u90e8\u5206\uff0c\u53ea\u5bf9\u56fa\u4f53\u90e8\u5206\u5efa\u6a21\uff0c\u5e76\u4f7f\u7528\u5bf9\u6d41\u70ed\u901a\u91cf \u8fb9\u754c\u6761\u4ef6\u6a21\u62df\u5bf9\u6d41\u6c14\u6d41\u3002\n\n\u5728\u7b2c\u4e8c\u90e8\u5206\uff0c\u6a21\u578b\u6269\u5c55\u4e3a\u5305\u542b\u6d41\u9053\u7684\u6d41\u4f53\u57df\uff0c\u5047\u5b9a\u6d41\u9053\u4e2d\u5b58\u5728\u975e\u7b49\u6e29\u6d41\u52a8\uff0c\u8ba1\u7b97\u4e86\u6d41\u4f53\u7684\u8026\u5408\u6e29\u5ea6\u548c\u901f\u5ea6\u3002\n\n\u5728\u6700\u540e\u4e00\u90e8\u5206\uff0c\u5206\u6790\u8868\u9762\u5bf9\u8868\u9762\u8f90\u5c04\uff0c\u89c2\u5bdf\u5176\u5bf9\u7ed3\u679c\u7684\u5f71\u54cd\u7a0b\u5ea6\u3002");

    model.label("chip_cooling.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
