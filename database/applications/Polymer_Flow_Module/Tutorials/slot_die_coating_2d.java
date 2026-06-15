/*
 * slot_die_coating_2d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:10 by COMSOL 6.3.0.290. */
public class slot_die_coating_2d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Polymer_Flow_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("pf", "PhaseField", "geom1");

    model.component("comp1").multiphysics().create("tpf1", "TwoPhaseFlowPhaseField", 2);
    model.component("comp1").multiphysics("tpf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("tpf1").set("Mathematics_physics", "pf");
    model.component("comp1").multiphysics("tpf1").selection().all();

    model.study().create("std1");
    model.study("std1").create("phasei", "PhaseInitialization");
    model.study("std1").feature("phasei").set("ftplistmethod", "manual");
    model.study("std1").feature("phasei").set("solnum", "auto");
    model.study("std1").feature("phasei").set("notsolnum", "auto");
    model.study("std1").feature("phasei").set("outputmap", new String[]{});
    model.study("std1").feature("phasei").set("ngenAUX", "1");
    model.study("std1").feature("phasei").set("goalngenAUX", "1");
    model.study("std1").feature("phasei").set("ngenAUX", "1");
    model.study("std1").feature("phasei").set("goalngenAUX", "1");
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/pf", true);
    model.study("std1").feature("phasei").setSolveFor("/multiphysics/tpf1", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("initstudy", "std1");
    model.study("std1").feature("time").set("notstudy", "std1");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("useinitsol", "on");
    model.study("std1").feature("time").set("notsolmethod", "sol");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/pf", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/tpf1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("W", "0.2[mm]", "\u69fd\u95f4\u9699");
    model.param().set("Hc", "1.35[mm]", "\u5165\u53e3\u9ad8\u5ea6");
    model.param().set("W_dd", "1.5[mm]", "\u4e0b\u6e38\u6a21\u53e3\u5bbd\u5ea6");
    model.param().set("W_ud", "1.35[mm]", "\u4e0a\u6e38\u6a21\u53e3\u5bbd\u5ea6");
    model.param().set("alpha_u", "45[deg]", "\u4e0a\u6e38\u6a21\u53e3\u89d2\u5ea6");
    model.param().set("alpha_d", "55[deg]", "\u4e0b\u6e38\u6a21\u53e3\u89d2\u5ea6");
    model.param().set("L_u", "5[mm]", "\u4e0a\u6e38\u957f\u5ea6");
    model.param().set("L_d", "10[mm]", "\u4e0b\u6e38\u957f\u5ea6");
    model.param().set("H", "0.2[mm]", "\u6d82\u5e03\u95f4\u9699");
    model.param().set("U_wall", "0.12[m/s]", "\u6d82\u5e03\u901f\u5ea6");
    model.param().set("U_in", "0.1[m/s]", "\u5165\u53e3\u901f\u5ea6");

    model.func().create("lsq1", "LeastSquares");
    model.func("lsq1")
         .set("table", new String[][]{{"0.5", "8.4"}, 
         {"0.79", "8.24"}, 
         {"1.26", "7.53"}, 
         {"1.99", "7.1"}, 
         {"3.15", "6.51"}, 
         {"5", "6.2"}, 
         {"7.92", "5.87"}, 
         {"12.56", "5.32"}, 
         {"19.91", "5.13"}, 
         {"31.55", "4.66"}, 
         {"50", "4.4"}, 
         {"79.24", "4.2"}});
    model.func("lsq1").setEntry("args", "col1", "gammadot");
    model.func("lsq1").setEntry("unit", "col1", "1");
    model.func("lsq1").setEntry("funcs", "col2", "mu_app");
    model.func("lsq1").setEntry("exprs", "col2", "m*max(gammadot,0.01)^(n-1)");
    model.func("lsq1").setEntry("unit", "col2", "Pa*s");
    model.func("lsq1").setIndex("pname", "m", 0);
    model.func("lsq1").setIndex("plist", 1, 0);
    model.func("lsq1").setIndex("pname", "n", 1);
    model.func("lsq1").setIndex("plist", 1, 1);
    model.func("lsq1").run();
    model.func("lsq1").createPlot("pg1");

    model.result("pg1").run();

    model.func().create("step1", "Step");
    model.func("step1").set("location", 0.01);
    model.func("step1").set("smooth", 0.02);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W", "Hc"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-W/2", "H"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "0.1[mm]", 0);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-W/2", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "H", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-W/2-W_ud", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "H", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-W/2-W_ud-tan(alpha_u)*Hc", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "Hc+H", 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-W/2-L_u", 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "Hc+H", 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-W/2-L_u", 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 4, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "W/2+L_d", 5, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 5, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "W/2+L_d", 6, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "Hc+H", 6, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "W/2+W_dd+tan(alpha_d)*Hc", 7, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "Hc+H", 7, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "W/2+W_dd", 8, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "H", 8, 1);
    model.component("comp1").geom("geom1").run("pol1");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(16);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(5);

    model.component("comp1").material().create("mpmat1", "Multiphase");
    model.component("comp1").material("mpmat1").selection().geom("geom1", 2);
    model.component("comp1").material("mpmat1").selection().set(1, 2, 3);
    model.component("comp1").material("mpmat1").selection().inherit(false);
    model.component("comp1").material("mpmat1").selection().embedded(false);
    model.component("comp1").material("mpmat1").set("vfDefinition", "pf");
    model.component("comp1").material("mpmat1").feature().create("phase2", "PhaseLink", "comp1");

    model.component("comp1").multiphysics("tpf1").set("multiphaseMaterialList", "mpmat1");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.material("mat1").propertyGroup().create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.material("mat1").propertyGroup().create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.material("mat1").label("Air");
    model.material("mat1").set("family", "air");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.material("mat1").propertyGroup("def").func("rho").set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.material("mat1").propertyGroup("def").func("rho").set("argunit", new String[]{"Pa", "K"});
    model.material("mat1").propertyGroup("def").func("rho").set("plotaxis", new String[]{"off", "on"});
    model.material("mat1").propertyGroup("def").func("rho").set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.material("mat1").propertyGroup("def").func("cs").set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("cs").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"Pa", "K"});
    model.material("mat1").propertyGroup("def").func("an1").set("plotaxis", new String[]{"off", "on"});
    model.material("mat1").propertyGroup("def").func("an1").set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an2").set("plotfixedvalue", new String[]{"200"});
    model.material("mat1").propertyGroup("def").func("an2").set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.material("mat1").propertyGroup("def").set("molarmass", "");
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.material("mat1").propertyGroup("def").addInput("temperature");
    model.material("mat1").propertyGroup("def").addInput("pressure");
    model.material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.material("mat1").materialType("nonSolid");
    model.component("comp1").material("mpmat1").feature("phase1").set("link", "mat1");
    model.material().create("mat2", "Common", "");
    model.component("comp1").material("mpmat1").feature("phase2").set("link", "mat2");
    model.material("mat2").label("\u6d82\u6599");
    model.material("mat2").propertyGroup().create("PowerLaw", "PowerLaw", "Non-Newtonian_power_law");
    model.material("mat2").propertyGroup("PowerLaw").set("m_pow", new String[]{"lsq1.m"});
    model.material("mat2").propertyGroup("PowerLaw").set("n_pow", new String[]{"lsq1.n"});
    model.material("mat2").propertyGroup("def").set("density", new String[]{"1400"});
    model.component("comp1").material("mpmat1").propertyGroup()
         .create("PowerLaw", "PowerLaw", "Non-Newtonian_power_law");
    model.component("comp1").material("mpmat1").propertyGroup("PowerLaw").setMixingRule("m_pow", "volume_average");
    model.component("comp1").material("mpmat1").propertyGroup("PowerLaw").setMixingRule("n_pow", "volume_average");
    model.component("comp1").material("mpmat1").propertyGroup("PowerLaw").setMixingRule("mu_app", "volume_average");
    model.component("comp1").material("mpmat1").propertyGroup("def").setMixingRule("density", "heaviside_function");
    model.component("comp1").material("mpmat1").set("heavisideMixPar", "0.9");
    model.component("comp1").material("mpmat1").propertyGroup("def")
         .setMixingRule("dynamicviscosity", "heaviside_function");
    model.component("comp1").material("mpmat1").set("heavisideMixPar", "0.9");
    model.component("comp1").material("mpmat1").propertyGroup("PowerLaw")
         .setMixingRule("mu_app", "heaviside_function");
    model.component("comp1").material("mpmat1").set("heavisideMixPar", "0.9");

    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(2);
    model.component("comp1").physics("spf").feature("wallbc2").set("BoundaryCondition", "NavierSlip");
    model.component("comp1").physics("spf").feature("wallbc2").set("SlidingWall", true);
    model.component("comp1").physics("spf").feature("wallbc2").set("uvw", "-U_wall");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(10);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "step1(t[1/s])*U_in");
    model.component("comp1").physics("spf").create("open1", "OpenBoundary", 1);
    model.component("comp1").physics("spf").feature("open1").selection().set(1, 16);
    model.component("comp1").physics("pf").feature("initfluid2").selection().set(3);
    model.component("comp1").physics("pf").feature("ww1").set("thetaw", "68.5[deg]");
    model.component("comp1").physics("pf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("pf").feature("inl1").set("pfcond", "Fluid2pf");
    model.component("comp1").physics("pf").feature("inl1").selection().set(10);
    model.component("comp1").physics("pf").create("out1", "Outlet", 1);
    model.component("comp1").physics("pf").feature("out1").selection().set(1, 16);
    model.component("comp1").physics("pf").create("ww2", "WettedWall", 1);
    model.component("comp1").physics("pf").feature("ww2").selection().set(2);
    model.component("comp1").physics("pf").feature("ww2").set("thetaw", "74[deg]");

    model.component("comp1").multiphysics("tpf1").set("ShiftSurfaceTensionForce", true);
    model.component("comp1").multiphysics("tpf1").set("SurfaceTensionCoefficient", "userdef");
    model.component("comp1").multiphysics("tpf1").set("sigma", 0.049);

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u901f\u5ea6 (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u538b\u529b (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("con1", "Contour");
    model.result("pg3").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("expr", "p");
    model.result("pg3").feature("con1").set("number", 40);
    model.result("pg3").feature("con1").set("levelrounding", false);
    model.result("pg3").feature("con1").set("smooth", "internal");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6d41\u4f53 1 \u7684\u4f53\u79ef\u5206\u6570 (pf)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "pf.Vf1");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").set("expr", "pf.Vf1");
    model.result("pg4").feature("con1").set("levelmethod", "levels");
    model.result("pg4").feature("con1").set("levels", "0.5");
    model.result("pg4").feature("con1").set("coloring", "uniform");
    model.result("pg4").feature("con1").set("colorlegend", false);
    model.result("pg4").feature("con1").set("color", "gray");
    model.result("pg4").feature("con1").set("smooth", "none");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result("pg2").run();

    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("plotgroup", "pg4");
    model.study("std1").feature("time").set("plotfreq", "tsteps");
    model.study("std1").feature("time").set("tlist", "range(0,0.01,0.25)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 4, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 7, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 21, 0);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u819c\u539a\u548c\u4e0a\u6e38\u5f2f\u6db2\u9762\u4f4d\u7f6e");
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "intop1(pf.Vf2)", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u819c\u539a", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "intop2(pf.Vf2)/intop2(1)", 1);
    model.result("pg5").feature("glob1").setIndex("descr", "\u4e0a\u6e38\u5f2f\u6708\u9762\u4f4d\u7f6e", 1);
    model.result("pg5").run();

    model.title("\u975e\u725b\u987f\u72ed\u7f1d\u5f0f\u6d82\u5e03 - \u4e8c\u7ef4");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5bf9\u72ed\u7f1d\u5f0f\u6d82\u5e03\u5de5\u827a\u8fdb\u884c\u5efa\u6a21\uff0c\u5176\u4e2d\u4f7f\u7528\u5e42\u5f8b\u975e\u725b\u987f\u6d41\u4f53\u63cf\u8ff0\u4e24\u76f8\u4e2d\u7684\u4e00\u76f8\u3002\u8be5\u6a21\u578b\u4f7f\u7528\u4e24\u76f8\u6d41\u76f8\u573a\u6cd5\u3002");

    model.label("slot_die_coating_2d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
