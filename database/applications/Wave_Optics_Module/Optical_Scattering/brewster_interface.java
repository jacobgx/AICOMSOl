/*
 * brewster_interface.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:40 by COMSOL 6.3.0.290. */
public class brewster_interface {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Optical_Scattering");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewbe", "ElectromagneticWavesBeamEnvelopes", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("ftplistmethod", "manual");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/ewbe", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("lda0", "1[um]", "\u6ce2\u957f");
    model.param().set("f0", "c_const/lda0", "\u9891\u7387");
    model.param().set("w0", "10*lda0", "\u5149\u6591\u534a\u5f84");
    model.param().set("z0", "pi*w0^2/lda0", "\u745e\u5229\u8303\u56f4");
    model.param().set("alpha", "atan(n2)", "\u5165\u5c04\u89d2\uff08Brewster \u89d2\u5904\uff09");
    model.param().set("a", "6*w0", "\u5bbd\u5ea6");
    model.param().set("b", "n2*a", "\u4f20\u64ad\u957f\u5ea6");
    model.param().set("n2", "1.5", "\u6298\u5c04\u7387");
    model.param().set("alpha2", "asin(sin(alpha)/n2)", "\u6298\u5c04\u89d2");
    model.param().set("k0", "2*pi/lda0", "\u7a7a\u6c14\u4e2d\u7684\u6ce2\u6570");
    model.param()
         .set("k1x_air", "k0", "\u7a7a\u6c14\u4e2d\u7684\u6ce2\u77e2\uff0c\u7b2c\u4e00\u4e2a\u6ce2\uff0cx \u5206\u91cf");
    model.param()
         .set("k1y_air", "0[1/m]", "\u7a7a\u6c14\u4e2d\u7684\u6ce2\u77e2\uff0c\u7b2c\u4e00\u4e2a\u6ce2\uff0cy \u5206\u91cf");
    model.param()
         .set("k2x_air", "-k0*cos(2*alpha)", "\u7a7a\u6c14\u4e2d\u7684\u6ce2\u77e2\uff0c\u7b2c\u4e8c\u4e2a\u6ce2\uff0cx \u5206\u91cf");
    model.param()
         .set("k2y_air", "-k0*sin(2*alpha)", "\u7a7a\u6c14\u4e2d\u7684\u6ce2\u77e2\uff0c\u7b2c\u4e8c\u4e2a\u6ce2\uff0cy \u5206\u91cf");
    model.param()
         .set("k1x_glass", "k0*n2*cos(alpha-alpha2)", "\u73bb\u7483\u4e2d\u7684\u6ce2\u77e2\uff0c\u7b2c\u4e00\u4e2a\u6ce2\uff0cx \u5206\u91cf");
    model.param()
         .set("k1y_glass", "k0*n2*sin(alpha-alpha2)", "\u73bb\u7483\u4e2d\u7684\u6ce2\u77e2\uff0c\u7b2c\u4e00\u4e2a\u6ce2\uff0cy \u5206\u91cf");
    model.param()
         .set("k2x_glass", "-k0*n2*cos(alpha+alpha2)", "\u73bb\u7483\u4e2d\u7684\u6ce2\u77e2\uff0c\u7b2c\u4e8c\u4e2a\u6ce2\uff0cx \u5206\u91cf");
    model.param()
         .set("k2y_glass", "-k0*n2*sin(alpha+alpha2)", "\u73bb\u7483\u4e2d\u7684\u6ce2\u77e2\uff0c\u7b2c\u4e8c\u4e2a\u6ce2\uff0cy \u5206\u91cf");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"b", "a"});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-b/2", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "a/2", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "b/2", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-a/2", 1, 1);
    model.component("comp1").geom("geom1").runPre("fin");

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

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u73bb\u7483");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"n2"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().set(1);
    model.component("comp1").variable("var1").set("phi1", "k1x_air*x+k1y_air*y");
    model.component("comp1").variable("var1")
         .descr("phi1", "\u7a7a\u6c14\u4e2d\u7684\u76f8\uff0c\u7b2c\u4e00\u4e2a\u6ce2");
    model.component("comp1").variable("var1").set("phi2", "k2x_air*x+k2y_air*y");
    model.component("comp1").variable("var1")
         .descr("phi2", "\u7a7a\u6c14\u4e2d\u7684\u76f8\uff0c\u7b2c\u4e8c\u4e2a\u6ce2");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection().set(2);
    model.component("comp1").variable("var2")
         .set("phi1", "k1x_glass*x+k1y_glass*y", "\u7a7a\u6c14\u4e2d\u7684\u76f8\uff0c\u7b2c\u4e00\u4e2a\u6ce2");
    model.component("comp1").variable("var2")
         .descr("phi1", "\u73bb\u7483\u4e2d\u7684\u76f8\uff0c\u7b2c\u4e00\u4e2a\u6ce2");
    model.component("comp1").variable("var2")
         .set("phi2", "k2x_glass*x+k2y_glass*y", "\u7a7a\u6c14\u4e2d\u7684\u76f8\uff0c\u7b2c\u4e8c\u4e2a\u6ce2");
    model.component("comp1").variable("var2")
         .descr("phi2", "\u73bb\u7483\u4e2d\u7684\u76f8\uff0c\u7b2c\u4e8c\u4e2a\u6ce2");

    model.component("comp1").physics("ewbe").prop("WaveVector").set("PhaseSpec", "UserDefined");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("phase1", "phi1");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("phase2", "phi2");
    model.component("comp1").physics("ewbe").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewbe").create("mbc1", "MatchedBoundaryCondition", 1);
    model.component("comp1").physics("ewbe").feature("mbc1").selection().set(1);
    model.component("comp1").physics("ewbe").feature("mbc1").set("IncidentField", "GaussianBeam");
    model.component("comp1").physics("ewbe").feature("mbc1").set("w0", "w0");
    model.component("comp1").physics("ewbe").feature("mbc1").set("Eg0", new String[]{"0", "0", "1[V/m]"});
    model.component("comp1").physics("ewbe").feature("mbc1").set("NoScatteredField", true);
    model.component("comp1").physics("ewbe").create("mbc2", "MatchedBoundaryCondition", 1);
    model.component("comp1").physics("ewbe").feature("mbc2").selection().set(4, 5);
    model.component("comp1").physics("ewbe").feature("mbc2").set("InputWave", "SecondWave");
    model.component("comp1").physics("ewbe").create("mbc3", "MatchedBoundaryCondition", 1);
    model.component("comp1").physics("ewbe").feature("mbc3").selection().set(2);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(1);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(4, 5);
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop3").selection().set(2);

    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").set("Pin", "-intop1(ewbe.nPoav)");
    model.component("comp1").variable("var3").descr("Pin", "\u8f93\u5165\u529f\u7387");
    model.component("comp1").variable("var3").set("Pt", "intop2(ewbe.nPoav)");
    model.component("comp1").variable("var3").descr("Pt", "\u900f\u5c04\u529f\u7387");
    model.component("comp1").variable("var3").set("Pr", "intop3(ewbe.nPoav)");
    model.component("comp1").variable("var3").descr("Pr", "\u53cd\u5c04\u529f\u7387");

    model.component("comp1").physics("ewbe").prop("MeshControl").set("MeshType", "Triangular");
    model.component("comp1").physics("ewbe").prop("MeshControl").set("hMax", "lda0/2");

    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a\uff0c\u7b2c\u4e00\u4e2a\u6ce2 (ewbe)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u7535\u573a");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("expr", "ewbe.normE1");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a\uff0c\u7b2c\u4e8c\u4e2a\u6ce2 (ewbe)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u7535\u573a");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "ewbe.normE2");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u573a (ewbe)");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "ewbe.normE");
    model.result("pg3").feature("surf1").set("resolution", "extrafine");
    model.result("pg3").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "Pr/Pin", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical("gev1").setIndex("expr", "((1-n2^2)/(1+n2^2))^2", 0);
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").appendResult();
    model.result().numerical("gev1").setIndex("expr", "(Pr+Pt)/Pin", 0);
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").appendResult();

    model.component("comp1").physics("ewbe").prop("components").set("components", "inplane");
    model.component("comp1").physics("ewbe").feature("mbc1").set("Eg0", new String[]{"0", "1[V/m]", "0"});

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result().numerical("gev1").setIndex("expr", "Pr/Pin", 0);
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").appendResult();
    model.result("pg3").run();

    model.title("\u4ee5 Brewster \u89d2\u5165\u5c04\u7684\u9ad8\u65af\u5149\u675f");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5728\u4e24\u4e2a\u4ecb\u8d28\u7684\u754c\u9762\u4e0a\u4ee5 Brewster \u89d2\u5165\u5c04\u7684\u9ad8\u65af\u5149\u675f\u7684\u504f\u632f\u5c5e\u6027\u3002\u6a21\u578b\u663e\u793a\u5982\u4f55\u4f7f\u7528\u201c\u7535\u78c1\u6ce2\uff0c\u6ce2\u675f\u5305\u7edc\u201d\u63a5\u53e3\u548c\u7528\u6237\u5b9a\u4e49\u7684\u76f8\u4f4d\u660e\u7ec6\uff0c\u4f7f\u7528\u201c\u5339\u914d\u8fb9\u754c\u6761\u4ef6\u201d\u7279\u5f81\u5728\u8fb9\u754c\u4e0a\u5438\u6536\u4ee5\u6307\u5b9a\u659c\u89d2\u5165\u5c04\u7684\u6ce2\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("brewster_interface.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
