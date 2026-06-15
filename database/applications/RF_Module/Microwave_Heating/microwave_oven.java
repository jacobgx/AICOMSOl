/*
 * microwave_oven.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:38 by COMSOL 6.3.0.290. */
public class microwave_oven {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Microwave_Heating");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.component("comp1").multiphysics().create("emh1", "ElectromagneticHeating", 3);
    model.component("comp1").multiphysics("emh1").set("EMHeat_physics", "emw");
    model.component("comp1").multiphysics("emh1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("emh1").selection().all();

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("ftplistmethod", "manual");
    model.study("std1").feature("freq").set("punit", "Hz");
    model.study("std1").feature("freq").set("plist", "1[MHz]");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/emw", true);
    model.study("std1").feature("freq").setSolveFor("/physics/ht", true);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/emh1", true);
    model.study("std1").feature("freq").setSolveFor("/physics/ht", false);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/emh1", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/emw", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/emh1", true);
    model.study("std1").feature("time").setSolveFor("/physics/emw", false);
    model.study("std1").feature("freq").set("plist", "2.45[GHz]");
    model.study("std1").setStoreSolution(true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("wo", "267[mm]", "\u5fae\u6ce2\u7089\u5bbd\u5ea6");
    model.param().set("do", "270[mm]", "\u5fae\u6ce2\u7089\u6df1\u5ea6");
    model.param().set("ho", "188[mm]", "\u5fae\u6ce2\u7089\u9ad8\u5ea6");
    model.param().set("wg", "50[mm]", "\u6ce2\u5bfc\u5bbd\u5ea6");
    model.param().set("dg", "78[mm]", "\u6ce2\u5bfc\u6df1\u5ea6");
    model.param().set("hg", "18[mm]", "\u6ce2\u5bfc\u9ad8\u5ea6");
    model.param().set("rp", "113.5[mm]", "\u73bb\u7483\u76d8\u534a\u5f84");
    model.param().set("hp", "6[mm]", "\u73bb\u7483\u76d8\u9ad8\u5ea6");
    model.param().set("bp", "15[mm]", "\u73bb\u7483\u76d8\u4e0e\u5e95\u90e8\u7684\u8ddd\u79bb");
    model.param().set("rpot", "31.5[mm]", "\u9a6c\u94c3\u85af\u534a\u5f84");
    model.param().set("T0", "8[degC]", "\u9a6c\u94c3\u85af\u521d\u59cb\u6e29\u5ea6");
    model.param().set("full_geometry", "1", "\u5bf9\u79f0\u6807\u5fd7");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"wo", "do", "ho"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "-do/2", "0"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"wg", "dg", "hg"});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"-wg", "-dg/2", "ho-hg"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "rp");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "hp");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"wo/2", "0", "bp"});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "rpot");
    model.component("comp1").geom("geom1").feature("sph1").set("pos", new String[]{"wo/2", "0", "rpot+bp+hp"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("blk1", "blk2", "cyl1", "sph1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new double[]{0.4, 0.4, 0.4});
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new double[]{-0.1, 0, 0});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("blk3", "uni1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("if1", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("if1")
         .label("\u5982\u679c\u662f\u5b8c\u6574\u51e0\u4f55\u7ed3\u6784");
    model.component("comp1").geom("geom1").feature("if1").set("condition", "full_geometry");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("int1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u571f\u8c46");
    model.component("comp1").selection("sel1").set(7, 8);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u73bb\u7483\u677f");
    model.component("comp1").selection("sel2").set(5, 6);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u7a7a\u6c14");
    model.component("comp1").selection("sel3").set(1, 2, 3, 4);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u7aef\u53e3\u8fb9\u754c");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(1, 5);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u91d1\u5c5e\u8fb9\u754c");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(2, 3, 4, 7, 8, 9, 10, 11, 12, 13, 15, 17, 19, 20, 39, 40);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u5bf9\u79f0");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(6, 16, 23, 30);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u534a\u6a21\u578b");
    model.component("comp1").selection("sel7").set(2, 4, 6, 8);

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
    model.component("comp1").material("mat1").selection().named("sel3");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u571f\u8c46");
    model.component("comp1").material("mat2").selection().named("sel1");
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"65-20*j"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalconductivity", new String[]{"0.55"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1050"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"3.64e3"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u73bb\u7483");
    model.component("comp1").material("mat3").selection().named("sel2");
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"2.55"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat4").label("Copper");
    model.component("comp1").material("mat4").set("family", "copper");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat4").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat4").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat4").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat4").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat4").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat4").selection().geom("geom1", 2);
    model.component("comp1").material("mat4").selection().named("sel5");

    model.component("comp1").physics("emw").create("port1", "Port", 2);
    model.component("comp1").physics("emw").feature("port1").label("\u7aef\u53e3 1\uff0c\u5168\u6a21\u578b");
    model.component("comp1").physics("emw").feature("port1").selection().named("sel4");
    model.component("comp1").physics("emw").feature("port1").set("PortType", "Rectangular");
    model.component("comp1").physics("emw").feature("port1").set("Pin", "1[kW]");
    model.component("comp1").physics("emw").create("imp1", "Impedance", 2);
    model.component("comp1").physics("emw").feature("imp1").selection().named("sel5");
    model.component("comp1").physics("emw").selection().named("sel7");
    model.component("comp1").physics("emw").create("port2", "Port", 2);
    model.component("comp1").physics("emw").feature("port2").label("\u7aef\u53e3 2\uff0c\u534a\u6a21\u578b");
    model.component("comp1").physics("emw").feature("port2").selection().named("sel4");
    model.component("comp1").physics("emw").feature("port2").set("PortType", "Rectangular");
    model.component("comp1").physics("emw").feature("port2").set("PortExcitation", "on");
    model.component("comp1").physics("emw").feature("port2").set("Pin", "1[kW]/2");
    model.component("comp1").physics("emw").create("symp1", "SymmetryPlane", 2);
    model.component("comp1").physics("emw").feature("symp1").selection().named("sel6");
    model.component("comp1").physics("emw").selection().all();
    model.component("comp1").physics("ht").selection().named("sel1");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").set("useadvanceddisable", true);
    model.study("std1").feature("freq").set("disabledphysics", new String[]{"emw/port2", "emw/symp1"});
    model.study("std1").feature("time").set("tlist", "range(0,1,5)");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "wo", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "wo", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "full_geometry", 0);
    model.study("std1").feature("param").setIndex("plistarr", 1, 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");

    model.sol().create("sol4");
    model.sol("sol4").study("std1");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p1").feature("so2").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (emw)");
    model.result("pg1").set("data", "dset4");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg1").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("S \u53c2\u6570 (emw)");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").set("expr", new String[]{"emw.S11dB"});
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").run();
    model.result().numerical("gev1").setResult();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").label("\u7535\u573a, \u5bf9\u6570 (emw)");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(2, 3, 4, 7, 8, 9, 10, 11, 12, 13, 15, 17, 19, 20, 39, 40);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection()
         .set(1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 15, 17, 19, 20, 39, 40);

    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "emw.normE");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection()
         .set(2, 3, 4, 7, 8, 9, 10, 11, 12, 13, 15, 17, 19, 20, 39, 40);
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf1").create("tran1", "Transparency");
    model.result("pg2").feature("surf1").feature("tran1").set("transparency", 0.85);
    model.result("pg2").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg2").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf1").feature("mtrl1").set("family", "rosegold");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("expr", "emw.normE");
    model.result("pg2").feature("surf2").create("sel1", "Selection");
    model.result("pg2").feature("surf2").feature("sel1").selection()
         .set(6, 14, 16, 18, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38);
    model.result("pg2").feature("surf2").set("colortable", "Dipole");
    model.result("pg2").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf2").create("tran1", "Transparency");
    model.result("pg2").feature("surf2").feature("tran1").set("transparency", 0.3);
    model.result("pg2").create("surf3", "Surface");
    model.result("pg2").feature("surf3").set("expr", "emw.normE");
    model.result("pg2").feature("surf3").create("sel1", "Selection");
    model.result("pg2").feature("surf3").feature("sel1").selection().set(1, 5);
    model.result("pg2").feature("surf3").set("colortable", "Dipole");
    model.result("pg2").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf3").create("tran1", "Transparency");
    model.result("pg2").feature("surf3").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").camera()
         .set("position", new double[]{-0.43956222741500195, -0.6634743317313817, 0.5384752543076226});
    model.component("comp1").view("view2").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view2").camera().set("zoomanglefull", 44.330973625183105);

    model.result("pg2").set("view", "view2");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "T");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("xnumber", "0");
    model.result("pg1").feature("mslc1").set("znumber", "0");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").feature("vol1").set("unit", "degC");
    model.result("pg3").feature().remove("vol1");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset3");
    model.result("pg3").create("slc1", "Slice");
    model.result("pg3").feature("slc1").set("expr", "T");
    model.result("pg3").feature("slc1").set("descr", "\u6e29\u5ea6");
    model.result("pg3").feature("slc1").set("unit", "degC");
    model.result("pg3").feature("slc1").set("quickplane", "zx");
    model.result("pg3").feature("slc1").set("quickynumber", 1);
    model.result("pg3").feature("slc1").set("colortable", "HeatCameraLight");
    model.result("pg3").run();
    model.result("pg3").create("slc2", "Slice");
    model.result("pg3").feature("slc2").set("expr", "emw.Ez");
    model.result("pg3").feature("slc2").set("descr", "\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg3").feature("slc2").set("quickplane", "xy");
    model.result("pg3").feature("slc2").set("quickzmethod", "coord");
    model.result("pg3").feature("slc2").set("quickz", 0.1);
    model.result("pg3").feature("slc2").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("slc2").feature("def1").set("expr", new String[]{"0", "0", "patcheval(emw.Ez,2)"});
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("slc2").create("filt1", "Filter");
    model.result("pg3").run();
    model.result("pg3").feature("slc2").feature("filt1").set("expr", "y>0");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").label("\u6e29\u5ea6 (ht) \u548c Ez");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u963b\u52a0\u70ed");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("slc1", "Slice");
    model.result("pg4").feature("slc1").set("expr", "emw.Qrh");
    model.result("pg4").feature("slc1").set("descr", "\u7535\u963b\u635f\u8017");
    model.result("pg4").feature("slc1").set("quickplane", "zx");
    model.result("pg4").feature("slc1").set("quickymethod", "coord");
    model.result("pg4").run();
    model.result().numerical().create("int1", "IntVolume");
    model.result().numerical("int1").set("data", "dset3");
    model.result().numerical("int1").set("expr", new String[]{"ht.Qtot"});
    model.result().numerical("int1").set("descr", new String[]{"\u603b\u70ed\u6e90"});
    model.result().numerical("int1").set("unit", new String[]{"W"});
    model.result().numerical("int1").setIndex("looplevelinput", "first", 0);
    model.result().numerical("int1").selection().named("sel1");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u4f53\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl2");
    model.result().numerical("int1").setResult();
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("pointx", "wo/2");
    model.result().dataset("cpt1").set("pointy", 0);
    model.result().dataset("cpt1").set("pointz", "rpot+bp+hp");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "cpt1");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u571f\u8c46\u7684\u6e29\u5ea6");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u65f6\u95f4");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").set("expr", "T");
    model.result("pg5").feature("ptgr1").set("descr", "\u6e29\u5ea6");
    model.result("pg5").feature("ptgr1").set("unit", "degC");
    model.result("pg5").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").set("plotgroup", "Default");
    model.study("std2").feature("freq").set("ftplistmethod", "manual");
    model.study("std2").feature("freq").set("punit", "Hz");
    model.study("std2").feature("freq").set("plist", "1[MHz]");
    model.study("std2").feature("freq").set("solnum", "auto");
    model.study("std2").feature("freq").set("notsolnum", "auto");
    model.study("std2").feature("freq").set("outputmap", new String[]{});
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").setSolveFor("/physics/emw", true);
    model.study("std2").feature("freq").setSolveFor("/physics/ht", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/emh1", true);
    model.study("std2").feature("freq").setSolveFor("/physics/ht", false);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/emh1", false);
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("plotgroup", "Default");
    model.study("std2").feature("time").set("ftplistmethod", "manual");
    model.study("std2").feature("time").set("initialtime", "0");
    model.study("std2").feature("time").set("solnum", "auto");
    model.study("std2").feature("time").set("notsolnum", "auto");
    model.study("std2").feature("time").set("outputmap", new String[]{});
    model.study("std2").feature("time").setSolveFor("/physics/emw", true);
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/emh1", true);
    model.study("std2").feature("time").setSolveFor("/physics/emw", false);
    model.study("std2").setStoreSolution(true);
    model.study("std2").feature("freq").set("plist", "2.45[GHz]");
    model.study("std2").feature("freq").set("useadvanceddisable", true);
    model.study("std2").feature("freq").set("disabledphysics", new String[]{"emw/port1"});
    model.study("std2").feature("time").set("tlist", "range(0,1,5)");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "wo", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "wo", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "full_geometry", 0);
    model.study("std2").feature("param").setIndex("plistarr", 0, 0);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol9");
    model.sol("sol9").study("std2");
    model.sol("sol9").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("p2").feature("so1").set("psol", "sol9");

    model.sol().create("sol10");
    model.sol("sol10").study("std2");
    model.sol("sol10").label("\u53c2\u6570\u5316\u89e3 4");

    model.batch("p2").feature("so2").set("psol", "sol10");
    model.batch("p2").run("compute");

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u7535\u573a (emw) 1");
    model.result("pg6").set("data", "dset8");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("mslc1", "Multislice");
    model.result("pg6").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg6").feature("mslc1").set("smooth", "internal");
    model.result("pg6").feature("mslc1").set("data", "parent");
    model.result("pg6").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg6").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("S \u53c2\u6570 (emw) 1");
    model.result().numerical("gev2").set("data", "dset6");
    model.result().numerical("gev2").set("expr", new String[]{"emw.S22dB"});
    model.result().table().create("tbl3", "Table");
    model.result().numerical("gev2").set("table", "tbl3");
    model.result().numerical("gev2").run();
    model.result().numerical("gev2").setResult();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset8");
    model.result("pg7").label("\u7535\u573a, \u5bf9\u6570 (emw) 1");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(3, 4, 5, 6, 8, 10, 11, 22);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 13, 16, 22);

    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "emw.normE");
    model.result("pg7").feature("surf1").create("sel1", "Selection");
    model.result("pg7").feature("surf1").feature("sel1").selection().set(3, 4, 5, 6, 8, 10, 11, 22);
    model.result("pg7").feature("surf1").set("colortable", "Dipole");
    model.result("pg7").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg7").feature("surf1").create("tran1", "Transparency");
    model.result("pg7").feature("surf1").feature("tran1").set("transparency", 0.77);
    model.result("pg7").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg7").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg7").feature("surf1").feature("mtrl1").set("family", "rosegold");
    model.result("pg7").feature("surf1").set("expr", "1");
    model.result("pg7").create("surf2", "Surface");
    model.result("pg7").feature("surf2").set("expr", "emw.normE");
    model.result("pg7").feature("surf2").create("sel1", "Selection");
    model.result("pg7").feature("surf2").feature("sel1").selection().set(2, 7, 13, 16);
    model.result("pg7").feature("surf2").set("colortable", "Prism");
    model.result("pg7").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg7").feature("surf2").create("tran1", "Transparency");
    model.result("pg7").feature("surf2").feature("tran1").set("transparency", 0.7);
    model.result("pg7").create("surf3", "Surface");
    model.result("pg7").feature("surf3").set("expr", "emw.normE");
    model.result("pg7").feature("surf3").create("sel1", "Selection");
    model.result("pg7").feature("surf3").feature("sel1").selection().set(9, 12, 14, 15, 17, 18, 19, 20, 21);
    model.result("pg7").feature("surf3").set("colortable", "Dipole");
    model.result("pg7").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg7").feature("surf3").create("tran1", "Transparency");
    model.result("pg7").feature("surf3").feature("tran1").set("transparency", 0.3);
    model.result("pg7").create("surf4", "Surface");
    model.result("pg7").feature("surf4").set("expr", "emw.normE");
    model.result("pg7").feature("surf4").create("sel1", "Selection");
    model.result("pg7").feature("surf4").feature("sel1").selection().set(1);
    model.result("pg7").feature("surf4").set("colortable", "Dipole");
    model.result("pg7").feature("surf4").set("colorscalemode", "logarithmic");
    model.result("pg7").feature("surf4").create("tran1", "Transparency");
    model.result("pg7").feature("surf4").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view4", "geom1");
    model.component("comp1").view("view4").camera()
         .set("position", new double[]{-0.43956222741500195, -0.6634743317313817, 0.5384752543076226});
    model.component("comp1").view("view4").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view4").camera().set("zoomanglefull", 44.330973625183105);

    model.result("pg7").set("view", "view4");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u6e29\u5ea6 (ht)");
    model.result("pg8").set("data", "dset8");
    model.result("pg8").feature().create("vol1", "Volume");
    model.result("pg8").feature("vol1").set("showsolutionparams", "on");
    model.result("pg8").feature("vol1").set("solutionparams", "parent");
    model.result("pg8").feature("vol1").set("expr", "T");
    model.result("pg8").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg8").feature("vol1").set("smooth", "internal");
    model.result("pg8").feature("vol1").set("showsolutionparams", "on");
    model.result("pg8").feature("vol1").set("data", "parent");
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u573a (emw)\uff0c\u534a\u6a21\u578b");
    model.result("pg6").run();
    model.result("pg6").feature("mslc1").set("xnumber", "0");
    model.result("pg6").feature("mslc1").set("znumber", "0");
    model.result("pg6").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg6").feature("mslc1").set("ycoord", 0);
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").set("data", "dset7");
    model.result("pg8").run();
    model.result("pg8").feature("vol1").set("unit", "degC");
    model.result("pg8").run();
    model.result("pg8").create("slc1", "Slice");
    model.result("pg8").feature("slc1").set("expr", "emw.Ez");
    model.result("pg8").feature("slc1").set("descr", "\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg8").feature("slc1").set("quickplane", "xy");
    model.result("pg8").feature("slc1").set("quickzmethod", "coord");
    model.result("pg8").feature("slc1").set("quickz", 0.1);
    model.result("pg8").feature("slc1").create("def1", "Deform");
    model.result("pg8").run();
    model.result("pg8").feature("slc1").feature("def1").set("expr", new String[]{"0", "0", "patcheval(emw.Ez,2)"});
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").label("\u6e29\u5ea6 (ht) \u548c Ez\uff0c\u534a\u6a21\u578b");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").run();
    model.result("pg9").label("\u7535\u963b\u70ed\u534a\u6a21\u578b");
    model.result("pg9").set("data", "dset6");
    model.result("pg9").create("slc1", "Slice");
    model.result("pg9").feature("slc1").set("expr", "emw.Qrh");
    model.result("pg9").feature("slc1").set("descr", "\u7535\u963b\u635f\u8017");
    model.result("pg9").feature("slc1").set("quickplane", "zx");
    model.result("pg9").feature("slc1").set("quickymethod", "coord");
    model.result("pg9").run();
    model.result().numerical().create("int2", "IntVolume");
    model.result().numerical("int2").set("data", "dset7");
    model.result().numerical("int2").setIndex("looplevelinput", "first", 0);
    model.result().numerical("int2").selection().set(3);
    model.result().numerical("int2").selection().named("sel1");
    model.result().numerical("int2").set("expr", new String[]{"ht.Qtot"});
    model.result().numerical("int2").set("descr", new String[]{"\u603b\u70ed\u6e90"});
    model.result().numerical("int2").set("unit", new String[]{"W"});
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u4f53\u79ef\u5206 2");
    model.result().numerical("int2").set("table", "tbl4");
    model.result().numerical("int2").setResult();
    model.result().dataset().create("cpt2", "CutPoint3D");
    model.result().dataset("cpt2").set("data", "dset5");
    model.result().dataset("cpt2").set("pointx", "wo/2");
    model.result().dataset("cpt2").set("pointy", 0);
    model.result().dataset("cpt2").set("pointz", "rpot+bp+hp");
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").set("data", "cpt2");
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "\u571f\u8c46\u7684\u6e29\u5ea6");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("xlabel", "\u65f6\u95f4");
    model.result("pg10").create("ptgr1", "PointGraph");
    model.result("pg10").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg10").feature("ptgr1").set("linewidth", "preference");
    model.result("pg10").feature("ptgr1").set("expr", "T");
    model.result("pg10").feature("ptgr1").set("descr", "\u6e29\u5ea6");
    model.result("pg10").feature("ptgr1").set("unit", "degC");
    model.result("pg10").run();
    model.result("pg8").run();

    model.title("\u5fae\u6ce2\u7089");

    model
         .description("\u672c\u4f8b\u63a2\u8ba8\u5c06\u4e00\u4e2a\u9a6c\u94c3\u85af\u653e\u5165\u5fae\u6ce2\u7089\uff0c\u5e76\u5c06\u5176\u66b4\u9732\u5728 2.45\u00a0GHz \u5fae\u6ce2\u8f90\u5c04\u4e0b\u7684\u60c5\u51b5\u3002\u6c42\u89e3\u5e76\u5206\u6790\u4e86\u9a6c\u94c3\u85af\u5728\u524d 5\u00a0\u79d2\u7684\u52a0\u70ed\u60c5\u51b5\uff0c\u6b64\u540e\uff0c\u9a6c\u94c3\u85af\u7684\u6e29\u5ea6\u63a5\u8fd1 100\u00b0C\u3002");

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

    model.label("microwave_oven.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
