/*
 * second_harmonic_generation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:40 by COMSOL 6.3.0.290. */
public class second_harmonic_generation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Nonlinear_Optics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewt", "ElectromagneticWavesTransient", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ewt", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

    model.param().set("w0", "2[um]");
    model.param().descr("w0", "\u6fc0\u5149\u675f\u6700\u5c0f\u5149\u6591\u534a\u5f84");
    model.param().set("lda0", "1.06[um]");
    model.param().descr("lda0", "\u5165\u5c04\u6fc0\u5149\u675f\u7684\u6ce2\u957f");
    model.param().set("E0", "30[kV/m]");
    model.param().descr("E0", "\u7535\u573a\u5cf0\u503c");
    model.param().set("x0", "pi*w0^2/lda0");
    model.param().descr("x0", "\u745e\u5229\u8303\u56f4");
    model.param().set("k0", "2*pi/lda0");
    model.param().descr("k0", "\u4f20\u64ad\u5e38\u6570");
    model.param().set("omega0", "k0*c_const");
    model.param().descr("omega0", "\u89d2\u9891\u7387");
    model.param().set("t0", "25[fs]");
    model.param().descr("t0", "\u8109\u51b2\u5ef6\u8fdf\u65f6\u95f4");
    model.param().set("dt", "10[fs]");
    model.param().descr("dt", "\u8109\u51b2\u5bbd\u5ea6");
    model.param().set("d33", "1e-17[F/V]");
    model.param().descr("d33", "\u4e8c\u6b21\u8c10\u6ce2\u4ea7\u751f\u7684\u77e9\u9635\u5355\u5143");
    model.param().set("lda2", "lda0/2");
    model.param().descr("lda2", "\u4e8c\u6b21\u8c10\u6ce2\u7684\u6ce2\u957f");
    model.param().set("hmax", "lda2/6");
    model.param().descr("hmax", "\u6700\u5927\u7f51\u683c\u5355\u5143\u5927\u5c0f");
    model.param().set("CFL", "0.15");
    model.param().descr("CFL", "CFL \u6570");
    model.param().set("tstep", "CFL*hmax/c_const");
    model.param().descr("tstep", "\u65f6\u6b65");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "w");
    model.func("an1").set("expr", "w0*sqrt(1+(x/x0)^2)");
    model.func("an1").setIndex("argunit", "m", 0);
    model.func("an1").set("fununit", "m");
    model.func().create("an2", "Analytic");
    model.func("an2").set("funcname", "eta");
    model.func("an2").set("expr", "atan2(x,x0)/2");
    model.func("an2").setIndex("argunit", "m", 0);
    model.func("an2").set("fununit", "rad");
    model.func().create("an3", "Analytic");
    model.func("an3").set("funcname", "R");
    model.func("an3").set("expr", "x*(1+(x0/x)^2)");
    model.func("an3").setIndex("argunit", "m", 0);
    model.func("an3").set("fununit", "m");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{20, 6});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{-10, -6});
    model.component("comp1").geom("geom1").runPre("fin");
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

    model.component("comp1").physics("ewt").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewt").create("pmc1", "PerfectMagneticConductor", 1);
    model.component("comp1").physics("ewt").feature("pmc1").selection().set(3);
    model.component("comp1").physics("ewt").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewt").feature("sctr1").selection().set(1);
    model.component("comp1").physics("ewt").feature("sctr1").set("IncidentField", "EField");
    model.component("comp1").physics("ewt").feature("sctr1")
         .set("E0i", new String[]{"0", "0", "E0*sqrt(w0/w(x))*exp(-y^2/w(x)^2)*cos(omega0*t-k0*x+eta(x)-k0*y^2/(2*R(x)))*exp(-(t-t0)^2/dt^2)"});
    model.component("comp1").physics("ewt").create("sctr2", "Scattering", 1);
    model.component("comp1").physics("ewt").feature("sctr2").selection().set(4);
    model.component("comp1").physics("ewt").feature("wee1").set("DisplacementFieldModel", "RemanentDisplacement");
    model.component("comp1").physics("ewt").feature("wee1").set("Dr", new String[]{"0", "0", "d33*ewt.Ez^2"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "explicit");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1")
         .set("explicit", "sin(range(0,0.025*pi,0.5*pi))");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "hmax");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").probe().create("pdom1", "DomainPoint");
    model.component("comp1").probe("pdom1").setIndex("coords2", 10, 0);
    model.component("comp1").probe("pdom1").feature("ppb1").set("probename", "Eout");
    model.component("comp1").probe("pdom1").feature("ppb1").set("expr", "ewt.Ez");
    model.component("comp1").probe("pdom1").feature("ppb1").set("descr", "\u7535\u573a\uff0cz \u5206\u91cf");

    model.study("std1").feature("time").set("tlist", "0 61[fs] 90[fs] 120[fs]");
    model.study("std1").feature("time").set("plot", true);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol1").feature("t1").set("timestepgenalpha", "tstep");
    model.sol("sol1").feature("t1").feature("fc1").set("useheuristicfact", true);

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("pdom1").genResult("none");

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");

    model.sol("sol1").runAll();

    model.result().remove("pg2");

    model.study("std1").feature("time").set("plotgroup", "Default");

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("solvertype", "none");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solvertype", "none");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solvertype", "none");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solvertype", "none");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solvertype", "none");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solvertype", "none");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "ewt.Ez");
    model.result("pg2").feature("surf1").set("descr", "\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg2").feature("surf1").create("hght1", "Height");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 2, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 3, 0);
    model.result("pg2").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("transform", "fourier");
    model.result("pg1").feature("tblp1").set("fouriershow", "spectrum");
    model.result("pg1").feature("tblp1").set("freqrangeactive", true);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("window", "graphics");
    model.result("pg1").run();
    model.result("pg2").run();

    model.title("\u9ad8\u65af\u5149\u675f\u7684\u4e8c\u6b21\u8c10\u6ce2\u4ea7\u751f");

    model
         .description("\u6fc0\u5149\u7cfb\u7edf\u662f\u73b0\u4ee3\u7535\u5b50\u5b66\u4e2d\u7684\u4e00\u4e2a\u91cd\u8981\u5e94\u7528\u9886\u57df\u3002\u501f\u52a9\u975e\u7ebf\u6027\u6750\u6599\uff0c\u53ef\u4ee5\u4ea7\u751f\u9891\u7387\u4e3a\u6fc0\u5149\u9891\u7387\u6570\u500d\u7684\u8c10\u6ce2\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u975e\u7ebf\u6027\u6750\u6599\u5c5e\u6027\uff0c\u901a\u8fc7\u77ac\u6001\u6ce2\u4eff\u771f\u6765\u5b9e\u73b0\u4e8c\u6b21\u8c10\u6ce2\u7684\u4ea7\u751f\uff0c\u5c06\u4e00\u4e2a Nd:YAG\uff08lambda=1.06\u00a0\u5fae\u7c73\uff09\u6fc0\u5149\u675f\u805a\u7126\u5728\u975e\u7ebf\u6027\u6676\u4f53\u4e0a\uff0c\u4ee5\u4f7f\u5149\u675f\u7684\u675f\u8170\u4f4d\u4e8e\u6676\u4f53\u5185\u90e8\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("second_harmonic_generation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
