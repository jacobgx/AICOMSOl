/*
 * inkjet_nozzle_ls.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:12 by COMSOL 6.3.0.290. */
public class inkjet_nozzle_ls {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Two-Phase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("ls", "LevelSet", "geom1");

    model.component("comp1").multiphysics().create("tpf1", "TwoPhaseFlowLevelSet", 2);
    model.component("comp1").multiphysics("tpf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("tpf1").set("Mathematics_physics", "ls");
    model.component("comp1").multiphysics("tpf1").selection().all();
    model.component("comp1").multiphysics().create("ww1", "WettedWall", 1);
    model.component("comp1").multiphysics("ww1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("ww1").set("Mathematics_physics", "ls");

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
    model.study("std1").feature("phasei").setSolveFor("/physics/ls", true);
    model.study("std1").feature("phasei").setSolveFor("/multiphysics/tpf1", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", false);
    model.study("std1").feature("phasei").setSolveFor("/multiphysics/ww1", true);
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
    model.study("std1").feature("time").setSolveFor("/physics/ls", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/tpf1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ww1", true);

    model.param().set("InletR", "0.1[mm]");
    model.param().descr("InletR", "\u55b7\u5634\u5165\u53e3\u534a\u5f84");
    model.param().set("NozzleL", "0.375[mm]");
    model.param().descr("NozzleL", "\u55b7\u5634\u957f\u5ea6");
    model.param().set("NozzleR", "0.025[mm]");
    model.param().descr("NozzleR", "\u55b7\u5634\u534a\u5f84");
    model.param().set("ThroatL", "0.025[mm]");
    model.param().descr("ThroatL", "\u5589\u90e8\u957f\u5ea6");
    model.param().set("TargetL", "1[mm]");
    model.param().descr("TargetL", "\u5230\u76ee\u6807\u7684\u8ddd\u79bb");
    model.param().set("AirW", "0.1[mm]");
    model.param().descr("AirW", "\u7a7a\u6c14\u901a\u9053\u5bbd\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"InletR", "2*InletR"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "2*InletR", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "InletR", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "2*InletR", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "NozzleR", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "2*InletR+NozzleL", 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "2*InletR+NozzleL", 3, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"NozzleR", "ThroatL+TargetL"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "NozzleL+2*InletR"});
    model.component("comp1").geom("geom1").feature().duplicate("r3", "r2");
    model.component("comp1").geom("geom1").feature("r3").setIndex("size", "AirW", 0);
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"AirW", "TargetL"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "ThroatL+NozzleL+2*InletR"});
    model.component("comp1").geom("geom1").feature().duplicate("r4", "r3");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"2*AirW", "AirW"});
    model.component("comp1").geom("geom1").feature("r4")
         .set("pos", new String[]{"0", "ThroatL+NozzleL+2*InletR+TargetL-AirW"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mpmat1", "Multiphase");
    model.component("comp1").material("mpmat1").selection().geom("geom1", 2);
    model.component("comp1").material("mpmat1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.component("comp1").material("mpmat1").selection().inherit(false);
    model.component("comp1").material("mpmat1").selection().embedded(false);
    model.component("comp1").material("mpmat1").set("vfDefinition", "ls");
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
    model.material("mat2").propertyGroup("def").set("density", new String[]{"1e3[kg/m^3]"});
    model.material("mat2").propertyGroup("def").set("dynamicviscosity", new String[]{"1e-2[Pa*s]"});
    model.material("mat2").label("\u58a8\u6c34");

    model.func().create("rect1", "Rectangle");
    model.func("rect1").set("lower", "1e-6");
    model.func("rect1").set("upper", "13e-6");
    model.func("rect1").set("smooth", "2e-6");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().all();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("v_in", "0.56[m/s]*rect1(t[1/s])");
    model.component("comp1").variable("var1").descr("v_in", "\u5165\u53e3\u901f\u5ea6");
    model.component("comp1").variable("var1").set("m_d", "intop1(1e3[kg/m^3]*phils*(z>0.7[mm]))");
    model.component("comp1").variable("var1").descr("m_d", "\u6db2\u6ef4\u8d28\u91cf");

    model.component("comp1").multiphysics("tpf1").set("IncludeSurfaceTension", true);
    model.component("comp1").multiphysics("tpf1").set("SurfaceTensionCoefficient", "userdef");
    model.component("comp1").multiphysics("tpf1").set("sigma", 0.07);

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "v_in");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(24);
    model.component("comp1").physics("ls").feature("lsm1").set("epsilon_ls", "2.5e-6");
    model.component("comp1").physics("ls").feature("lsm1").set("gamma", 10);
    model.component("comp1").physics("ls").feature("initfluid2").selection().set(1, 2, 3);
    model.component("comp1").physics("ls").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("ls").feature("inl1").selection().set(2);
    model.component("comp1").physics("ls").feature("inl1").set("lscond", "Fluid2ls");
    model.component("comp1").physics("ls").create("out1", "Outlet", 1);
    model.component("comp1").physics("ls").feature("out1").selection().set(24);

    model.component("comp1").multiphysics("ww1").selection().set(11, 12, 13, 15, 18, 19, 20, 22, 23);
    model.component("comp1").multiphysics("ww1").set("NavierSlip", "userdef");
    model.component("comp1").multiphysics("ww1").set("beta", "10[um]");

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,10e-6,200e-6)");
    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("timeadaption", "adaption");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v2").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_u").set("scaleval", 10);
    model.sol("sol1").feature("v2").feature("comp1_p").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_p").set("scaleval", "1e4");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").label("\u7ec6\u5316\u7f51\u683c\u89e3 1");
    model.sol("sol3").study("std1");
    model.sol("sol3").setClusterStorage("all");
    model.sol("sol1").feature("t1").feature("taDef").set("tadapsol", "sol3");

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");

    model.sol("sol1").runAll();

    model.result().remove("pg1");

    model.study("std1").feature("time").set("plotgroup", "Default");

    model.result().dataset("dset3").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 30, 0);
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 30, 0);
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset3");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6d41\u4f53 1 \u7684\u4f53\u79ef\u5206\u6570 (ls)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 30, 0);
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "ls.Vf1");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").set("expr", "ls.Vf1");
    model.result("pg4").feature("con1").set("levelmethod", "levels");
    model.result("pg4").feature("con1").set("levels", "0.5");
    model.result("pg4").feature("con1").set("coloring", "uniform");
    model.result("pg4").feature("con1").set("colorlegend", false);
    model.result("pg4").feature("con1").set("color", "gray");
    model.result("pg4").feature("con1").set("smooth", "none");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").label("\u4e8c\u7ef4\u65cb\u8f6c 1");
    model.result().dataset("rev2").set("data", "dset3");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6d41\u4f53 1 \u7684\u4f53\u79ef\u5206\u6570 (ls) 1");
    model.result("pg5").set("data", "rev2");
    model.result("pg5").setIndex("looplevel", 30, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("iso1", "Isosurface");
    model.result("pg5").feature("iso1").set("expr", "ls.Vf1");
    model.result("pg5").feature("iso1").set("levelmethod", "levels");
    model.result("pg5").feature("iso1").set("levels", "0.5");
    model.result("pg5").feature("iso1").set("coloring", "uniform");
    model.result("pg5").feature("iso1").set("color", "gray");
    model.result("pg5").feature("iso1").set("smooth", "none");
    model.result("pg5").feature("iso1").set("data", "parent");
    model.result("pg1").run();
    model.result().dataset().create("edg1", "Edge2D");
    model.result().dataset("edg1").selection().set(12, 13, 15, 19, 20, 22, 24);
    model.result().dataset().create("rev3", "Revolve2D");
    model.result().dataset("rev3").set("data", "edg1");
    model.result().dataset("rev3").set("revangle", 230);
    model.result().dataset().create("edg2", "Edge2D");
    model.result().dataset("edg2").selection().set(12, 13, 19);
    model.result().dataset().create("rev4", "Revolve2D");
    model.result().dataset("rev4").set("data", "edg2");
    model.result().dataset("rev4").set("startangle", 230);
    model.result().dataset("rev4").set("revangle", 130);
    model.result("pg5").run();
    model.result("pg5").feature("iso1").set("color", "black");
    model.result("pg5").run();
    model.result("pg5").create("slc1", "Slice");
    model.result("pg5").feature("slc1").set("quickplane", "zx");
    model.result("pg5").feature("slc1").set("quickynumber", 1);
    model.result("pg5").feature("slc1").set("colortable", "Cividis");
    model.result("pg5").run();
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("data", "rev3");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "gray");
    model.result("pg5").run();
    model.result("pg5").create("surf2", "Surface");
    model.result("pg5").feature("surf2").set("data", "rev4");
    model.result("pg5").feature("surf2").set("coloring", "uniform");
    model.result("pg5").feature("surf2").set("color", "black");
    model.result("pg5").run();
    model.result("pg5").set("view", "view2");
    model.result("pg5").set("looplevel", new int[]{7});
    model.result("pg5").set("edges", false);
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{1});
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{3});
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{6});
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{12});
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{18});
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{24});
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{30});
    model.result("pg5").run();
    model.result("pg5").set("looplevel", new int[]{6});
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset3");
    model.result("pg6").set("showlegends", false);
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{"m_d"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u6db2\u6ef4\u8d28\u91cf"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"kg"});
    model.result("pg6").run();
    model.result("pg5").run();

    model.title("\u55b7\u58a8\u6253\u5370\u673a\u55b7\u5634 - \u6c34\u5e73\u96c6\u65b9\u6cd5");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9\u55b7\u58a8\u6253\u5370\u673a\u55b7\u5634\u7684\u6d41\u4f53\u6d41\u52a8\u8fdb\u884c\u5efa\u6a21\u3002\u58a8\u6ef4\u901a\u8fc7\u55b7\u5634\u55b7\u51fa\uff0c\u5728\u7a7a\u6c14\u4e2d\u79fb\u52a8\uff0c\u76f4\u5230\u649e\u51fb\u76ee\u6807\u7269\u4f53\u3002\u5176\u4e2d\u901a\u8fc7\u5e26\u8868\u9762\u5f20\u529b\u7684\u4e0d\u53ef\u538b\u7f29\u7eb3\u7ef4-\u65af\u6258\u514b\u65af\u65b9\u7a0b\u5bf9\u6d41\u4f53\u6d41\u52a8\u8fdb\u884c\u5efa\u6a21\u3002\u8be5\u6a21\u578b\u53ef\u4ee5\u901a\u8fc7\u6c34\u5e73\u96c6\u65b9\u6cd5\u6216\u76f8\u573a\u6cd5\u8ffd\u8e2a\u6d41\u4f53\u754c\u9762\u6765\u8fdb\u884c\u6c42\u89e3\uff0c\u672c\u4f8b\u91c7\u7528\u6c34\u5e73\u96c6\u65b9\u6cd5\u3002\u6b64\u5916\uff0c\u5176\u4e2d\u8fd8\u4f7f\u7528\u4e86\u81ea\u9002\u5e94\u7f51\u683c\u5212\u5206\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("inkjet_nozzle_ls.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
