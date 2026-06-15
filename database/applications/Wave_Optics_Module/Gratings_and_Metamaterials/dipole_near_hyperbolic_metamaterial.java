/*
 * dipole_near_hyperbolic_metamaterial.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:37 by COMSOL 6.3.0.290. */
public class dipole_near_hyperbolic_metamaterial {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Gratings_and_Metamaterials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("ftplistmethod", "manual");
    model.study("std1").feature("wave").set("solnum", "auto");
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("outputmap", new String[]{});
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").setSolveFor("/physics/ewfd", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

    model.param().set("W", "2[um]");
    model.param().descr("W", "Geometry width");
    model.param().set("lda0", "480[nm]");
    model.param().descr("lda0", "Wavelength");
    model.param().set("td", "15[nm]");
    model.param().descr("td", "Thickness, dielectric");
    model.param().set("tm", "10[nm]");
    model.param().descr("tm", "Thickness, metal");
    model.param().set("P", "td+tm");
    model.param().descr("P", "Periodicity");
    model.param().set("tPML", "250[nm]");
    model.param().descr("tPML", "Thickness, PML");
    model.param().set("d", "25[nm]");
    model.param().descr("d", "Dipole-metamaterial distance");
    model.param().set("nlayer", "30");
    model.param().descr("nlayer", "Number of layers");
    model.param().set("hMTM", "nlayer*P");
    model.param().descr("hMTM", "Thickness, metamaterial");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "W/2");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "tPML", 0);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W/2", "tm"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-tm"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "tPML", 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"W/2", "td"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "-tm-td"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr1").set("linearsize", "nlayer");
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "-P"});
    model.component("comp1").geom("geom1").feature("arr1").set("selresult", true);
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").feature().duplicate("arr2", "arr1");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("r2");
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"W/2", "tPML"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "-hMTM-tPML"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("layer", "tPML", 0);
    model.component("comp1").geom("geom1").feature("r3").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r3").set("layerright", true);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "d", 1);
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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "\u6298\u5c04\u7387");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func()
         .create("int2", "Interpolation");
    model.component("comp1").material("mat2")
         .label("Ag (Silver) (Ciesielski et al. 2017: Ag/SiO2; n,k 0.191-20.9 um)");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int1").set("funcname", "nr");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int1")
         .set("table", new String[][]{{"0.19077", "1.017"}, 
         {"0.19136", "1.020"}, 
         {"0.19195", "1.023"}, 
         {"0.19255", "1.026"}, 
         {"0.19315", "1.029"}, 
         {"0.19375", "1.032"}, 
         {"0.19436", "1.035"}, 
         {"0.19497", "1.038"}, 
         {"0.19558", "1.041"}, 
         {"0.19620", "1.044"}, 
         {"0.19683", "1.047"}, 
         {"0.19745", "1.050"}, 
         {"0.19808", "1.054"}, 
         {"0.19872", "1.057"}, 
         {"0.19936", "1.060"}, 
         {"0.20000", "1.063"}, 
         {"0.20065", "1.066"}, 
         {"0.20130", "1.070"}, 
         {"0.20195", "1.073"}, 
         {"0.20261", "1.076"}, 
         {"0.20328", "1.080"}, 
         {"0.20395", "1.083"}, 
         {"0.20462", "1.086"}, 
         {"0.20530", "1.090"}, 
         {"0.20598", "1.093"}, 
         {"0.20667", "1.097"}, 
         {"0.20736", "1.100"}, 
         {"0.20805", "1.104"}, 
         {"0.20875", "1.107"}, 
         {"0.20946", "1.111"}, 
         {"0.21017", "1.114"}, 
         {"0.21088", "1.118"}, 
         {"0.21160", "1.122"}, 
         {"0.21233", "1.125"}, 
         {"0.21306", "1.129"}, 
         {"0.21379", "1.133"}, 
         {"0.21453", "1.136"}, 
         {"0.21528", "1.140"}, 
         {"0.21603", "1.144"}, 
         {"0.21678", "1.148"}, 
         {"0.21754", "1.152"}, 
         {"0.21831", "1.155"}, 
         {"0.21908", "1.159"}, 
         {"0.21986", "1.163"}, 
         {"0.22064", "1.167"}, 
         {"0.22143", "1.171"}, 
         {"0.22222", "1.175"}, 
         {"0.22302", "1.179"}, 
         {"0.22383", "1.183"}, 
         {"0.22464", "1.187"}, 
         {"0.22545", "1.191"}, 
         {"0.22628", "1.195"}, 
         {"0.22711", "1.199"}, 
         {"0.22794", "1.203"}, 
         {"0.22878", "1.207"}, 
         {"0.22963", "1.212"}, 
         {"0.23048", "1.216"}, 
         {"0.23134", "1.220"}, 
         {"0.23221", "1.224"}, 
         {"0.23308", "1.228"}, 
         {"0.23396", "1.233"}, 
         {"0.23485", "1.237"}, 
         {"0.23574", "1.241"}, 
         {"0.23664", "1.246"}, 
         {"0.23755", "1.250"}, 
         {"0.23846", "1.255"}, 
         {"0.23938", "1.259"}, 
         {"0.24031", "1.264"}, 
         {"0.24125", "1.268"}, 
         {"0.24219", "1.273"}, 
         {"0.24314", "1.277"}, 
         {"0.24409", "1.282"}, 
         {"0.24506", "1.287"}, 
         {"0.24603", "1.291"}, 
         {"0.24701", "1.296"}, 
         {"0.24800", "1.301"}, 
         {"0.24900", "1.306"}, 
         {"0.25000", "1.311"}, 
         {"0.25101", "1.316"}, 
         {"0.25203", "1.321"}, 
         {"0.25306", "1.326"}, 
         {"0.25410", "1.331"}, 
         {"0.25514", "1.336"}, 
         {"0.25620", "1.341"}, 
         {"0.25726", "1.346"}, 
         {"0.25833", "1.352"}, 
         {"0.25941", "1.357"}, 
         {"0.26050", "1.362"}, 
         {"0.26160", "1.368"}, 
         {"0.26271", "1.373"}, 
         {"0.26383", "1.379"}, 
         {"0.26496", "1.385"}, 
         {"0.26609", "1.391"}, 
         {"0.26724", "1.397"}, 
         {"0.26840", "1.403"}, 
         {"0.26957", "1.409"}, 
         {"0.27074", "1.415"}, 
         {"0.27193", "1.421"}, 
         {"0.27313", "1.428"}, 
         {"0.27434", "1.434"}, 
         {"0.27556", "1.441"}, 
         {"0.27679", "1.448"}, 
         {"0.27803", "1.455"}, 
         {"0.27928", "1.462"}, 
         {"0.28054", "1.469"}, 
         {"0.28182", "1.476"}, 
         {"0.28311", "1.483"}, 
         {"0.28440", "1.490"}, 
         {"0.28571", "1.498"}, 
         {"0.28704", "1.505"}, 
         {"0.28837", "1.512"}, 
         {"0.28972", "1.519"}, 
         {"0.29108", "1.526"}, 
         {"0.29245", "1.533"}, 
         {"0.29384", "1.538"}, 
         {"0.29524", "1.543"}, 
         {"0.29665", "1.546"}, 
         {"0.29808", "1.548"}, 
         {"0.29952", "1.547"}, 
         {"0.30097", "1.542"}, 
         {"0.30244", "1.533"}, 
         {"0.30392", "1.517"}, 
         {"0.30542", "1.492"}, 
         {"0.30693", "1.455"}, 
         {"0.30846", "1.404"}, 
         {"0.31000", "1.338"}, 
         {"0.31156", "1.267"}, 
         {"0.31313", "1.213"}, 
         {"0.31472", "1.145"}, 
         {"0.31633", "1.064"}, 
         {"0.31795", "0.960"}, 
         {"0.31959", "0.853"}, 
         {"0.32124", "0.752"}, 
         {"0.32292", "0.658"}, 
         {"0.32461", "0.574"}, 
         {"0.32632", "0.503"}, 
         {"0.32804", "0.447"}, 
         {"0.32979", "0.404"}, 
         {"0.33155", "0.372"}, 
         {"0.33333", "0.348"}, 
         {"0.33514", "0.329"}, 
         {"0.33696", "0.315"}, 
         {"0.33880", "0.304"}, 
         {"0.34066", "0.295"}, 
         {"0.34254", "0.288"}, 
         {"0.34444", "0.283"}, 
         {"0.34637", "0.278"}, 
         {"0.34831", "0.274"}, 
         {"0.35028", "0.271"}, 
         {"0.35227", "0.268"}, 
         {"0.35429", "0.265"}, 
         {"0.35632", "0.263"}, 
         {"0.35838", "0.260"}, 
         {"0.36047", "0.258"}, 
         {"0.36257", "0.256"}, 
         {"0.36471", "0.253"}, 
         {"0.36686", "0.251"}, 
         {"0.36905", "0.248"}, 
         {"0.37126", "0.245"}, 
         {"0.37349", "0.242"}, 
         {"0.37576", "0.238"}, 
         {"0.37805", "0.235"}, 
         {"0.38037", "0.231"}, 
         {"0.38272", "0.227"}, 
         {"0.38509", "0.222"}, 
         {"0.38750", "0.218"}, 
         {"0.38994", "0.213"}, 
         {"0.39241", "0.208"}, 
         {"0.39490", "0.203"}, 
         {"0.39744", "0.198"}, 
         {"0.40000", "0.193"}, 
         {"0.40260", "0.188"}, 
         {"0.40523", "0.183"}, 
         {"0.40789", "0.178"}, 
         {"0.41060", "0.173"}, 
         {"0.41333", "0.168"}, 
         {"0.41611", "0.163"}, 
         {"0.41892", "0.158"}, 
         {"0.42177", "0.153"}, 
         {"0.42466", "0.148"}, 
         {"0.42759", "0.144"}, 
         {"0.43056", "0.139"}, 
         {"0.43357", "0.135"}, 
         {"0.43662", "0.131"}, 
         {"0.43972", "0.127"}, 
         {"0.44286", "0.123"}, 
         {"0.44604", "0.120"}, 
         {"0.44928", "0.116"}, 
         {"0.45255", "0.113"}, 
         {"0.45588", "0.110"}, 
         {"0.45926", "0.107"}, 
         {"0.46269", "0.104"}, 
         {"0.46617", "0.101"}, 
         {"0.46970", "0.099"}, 
         {"0.47328", "0.096"}, 
         {"0.47692", "0.094"}, 
         {"0.48062", "0.092"}, 
         {"0.48438", "0.090"}, 
         {"0.48819", "0.088"}, 
         {"0.49206", "0.086"}, 
         {"0.49600", "0.085"}, 
         {"0.50000", "0.083"}, 
         {"0.50407", "0.082"}, 
         {"0.50820", "0.080"}, 
         {"0.51240", "0.079"}, 
         {"0.51667", "0.078"}, 
         {"0.52101", "0.077"}, 
         {"0.52542", "0.076"}, 
         {"0.52991", "0.075"}, 
         {"0.53448", "0.074"}, 
         {"0.53913", "0.074"}, 
         {"0.54386", "0.073"}, 
         {"0.54867", "0.072"}, 
         {"0.55357", "0.072"}, 
         {"0.55856", "0.072"}, 
         {"0.56364", "0.071"}, 
         {"0.56881", "0.071"}, 
         {"0.57407", "0.071"}, 
         {"0.57944", "0.071"}, 
         {"0.58491", "0.071"}, 
         {"0.59048", "0.071"}, 
         {"0.59615", "0.071"}, 
         {"0.60194", "0.071"}, 
         {"0.60784", "0.071"}, 
         {"0.61386", "0.071"}, 
         {"0.62000", "0.071"}, 
         {"0.62626", "0.072"}, 
         {"0.63265", "0.072"}, 
         {"0.63918", "0.073"}, 
         {"0.64583", "0.073"}, 
         {"0.65263", "0.074"}, 
         {"0.65957", "0.074"}, 
         {"0.66667", "0.075"}, 
         {"0.67391", "0.076"}, 
         {"0.68132", "0.076"}, 
         {"0.68889", "0.077"}, 
         {"0.69663", "0.078"}, 
         {"0.70455", "0.079"}, 
         {"0.71264", "0.080"}, 
         {"0.72093", "0.081"}, 
         {"0.72941", "0.083"}, 
         {"0.73810", "0.084"}, 
         {"0.74699", "0.085"}, 
         {"0.75610", "0.087"}, 
         {"0.76543", "0.088"}, 
         {"0.77500", "0.090"}, 
         {"0.78481", "0.091"}, 
         {"0.79487", "0.093"}, 
         {"0.80519", "0.095"}, 
         {"0.81579", "0.097"}, 
         {"0.82667", "0.099"}, 
         {"0.83784", "0.101"}, 
         {"0.84932", "0.103"}, 
         {"0.86111", "0.106"}, 
         {"0.87324", "0.108"}, 
         {"0.88571", "0.111"}, 
         {"0.89855", "0.113"}, 
         {"0.91176", "0.116"}, 
         {"0.92537", "0.119"}, 
         {"0.93939", "0.122"}, 
         {"0.95385", "0.126"}, 
         {"0.96875", "0.129"}, 
         {"0.98413", "0.133"}, 
         {"1.0000", "0.137"}, 
         {"1.0164", "0.141"}, 
         {"1.0333", "0.145"}, 
         {"1.0508", "0.149"}, 
         {"1.0690", "0.154"}, 
         {"1.0877", "0.159"}, 
         {"1.1071", "0.165"}, 
         {"1.1273", "0.170"}, 
         {"1.1481", "0.176"}, 
         {"1.1698", "0.182"}, 
         {"1.1923", "0.189"}, 
         {"1.2157", "0.196"}, 
         {"1.2400", "0.204"}, 
         {"1.2653", "0.212"}, 
         {"1.2917", "0.220"}, 
         {"1.3191", "0.229"}, 
         {"1.3478", "0.239"}, 
         {"1.4419", "0.272"}, 
         {"1.4762", "0.285"}, 
         {"1.5122", "0.298"}, 
         {"1.5398", "0.309"}, 
         {"1.5500", "0.313"}, 
         {"1.5773", "0.324"}, 
         {"1.5897", "0.329"}, 
         {"1.6166", "0.340"}, 
         {"1.6316", "0.346"}, 
         {"1.6580", "0.357"}, 
         {"1.6757", "0.365"}, 
         {"1.7015", "0.376"}, 
         {"1.7222", "0.385"}, 
         {"1.7474", "0.396"}, 
         {"1.7714", "0.406"}, 
         {"1.7958", "0.417"}, 
         {"1.8235", "0.430"}, 
         {"1.8469", "0.441"}, 
         {"1.8788", "0.456"}, 
         {"1.9011", "0.467"}, 
         {"1.9375", "0.485"}, 
         {"1.9585", "0.495"}, 
         {"2.0000", "0.516"}, 
         {"2.0195", "0.526"}, 
         {"2.0845", "0.560"}, 
         {"2.1537", "0.597"}, 
         {"2.2277", "0.638"}, 
         {"2.3070", "0.684"}, 
         {"2.3921", "0.734"}, 
         {"2.4838", "0.791"}, 
         {"2.5827", "0.854"}, 
         {"2.6899", "0.926"}, 
         {"2.8064", "1.007"}, 
         {"2.9333", "1.099"}, 
         {"3.0724", "1.204"}, 
         {"3.2252", "1.325"}, 
         {"3.3941", "1.465"}, 
         {"3.5816", "1.629"}, 
         {"3.7911", "1.823"}, 
         {"4.0265", "2.052"}, 
         {"4.2932", "2.328"}, 
         {"4.5977", "2.663"}, 
         {"4.9486", "3.076"}, 
         {"5.3576", "3.592"}, 
         {"5.8403", "4.249"}, 
         {"6.4185", "5.102"}, 
         {"7.1239", "6.236"}, 
         {"8.0033", "7.787"}, 
         {"9.1305", "9.984"}, 
         {"10.627", "13.228"}, 
         {"12.711", "18.272"}, 
         {"15.811", "26.644"}, 
         {"20.912", "41.734"}});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int1")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int2").set("funcname", "ni");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int2")
         .set("table", new String[][]{{"0.19077", "1.100"}, 
         {"0.19136", "1.104"}, 
         {"0.19195", "1.108"}, 
         {"0.19255", "1.111"}, 
         {"0.19315", "1.115"}, 
         {"0.19375", "1.118"}, 
         {"0.19436", "1.122"}, 
         {"0.19497", "1.125"}, 
         {"0.19558", "1.129"}, 
         {"0.19620", "1.132"}, 
         {"0.19683", "1.136"}, 
         {"0.19745", "1.139"}, 
         {"0.19808", "1.142"}, 
         {"0.19872", "1.146"}, 
         {"0.19936", "1.149"}, 
         {"0.20000", "1.152"}, 
         {"0.20065", "1.156"}, 
         {"0.20130", "1.159"}, 
         {"0.20195", "1.162"}, 
         {"0.20261", "1.166"}, 
         {"0.20328", "1.169"}, 
         {"0.20395", "1.172"}, 
         {"0.20462", "1.175"}, 
         {"0.20530", "1.178"}, 
         {"0.20598", "1.181"}, 
         {"0.20667", "1.184"}, 
         {"0.20736", "1.187"}, 
         {"0.20805", "1.190"}, 
         {"0.20875", "1.193"}, 
         {"0.20946", "1.196"}, 
         {"0.21017", "1.199"}, 
         {"0.21088", "1.202"}, 
         {"0.21160", "1.205"}, 
         {"0.21233", "1.208"}, 
         {"0.21306", "1.211"}, 
         {"0.21379", "1.213"}, 
         {"0.21453", "1.216"}, 
         {"0.21528", "1.219"}, 
         {"0.21603", "1.221"}, 
         {"0.21678", "1.224"}, 
         {"0.21754", "1.226"}, 
         {"0.21831", "1.229"}, 
         {"0.21908", "1.231"}, 
         {"0.21986", "1.234"}, 
         {"0.22064", "1.236"}, 
         {"0.22143", "1.238"}, 
         {"0.22222", "1.241"}, 
         {"0.22302", "1.243"}, 
         {"0.22383", "1.245"}, 
         {"0.22464", "1.247"}, 
         {"0.22545", "1.249"}, 
         {"0.22628", "1.251"}, 
         {"0.22711", "1.253"}, 
         {"0.22794", "1.255"}, 
         {"0.22878", "1.257"}, 
         {"0.22963", "1.259"}, 
         {"0.23048", "1.261"}, 
         {"0.23134", "1.262"}, 
         {"0.23221", "1.264"}, 
         {"0.23308", "1.265"}, 
         {"0.23396", "1.267"}, 
         {"0.23485", "1.268"}, 
         {"0.23574", "1.270"}, 
         {"0.23664", "1.271"}, 
         {"0.23755", "1.272"}, 
         {"0.23846", "1.273"}, 
         {"0.23938", "1.274"}, 
         {"0.24031", "1.275"}, 
         {"0.24125", "1.276"}, 
         {"0.24219", "1.276"}, 
         {"0.24314", "1.277"}, 
         {"0.24409", "1.278"}, 
         {"0.24506", "1.278"}, 
         {"0.24603", "1.278"}, 
         {"0.24701", "1.278"}, 
         {"0.24800", "1.279"}, 
         {"0.24900", "1.279"}, 
         {"0.25000", "1.278"}, 
         {"0.25101", "1.278"}, 
         {"0.25203", "1.278"}, 
         {"0.25306", "1.277"}, 
         {"0.25410", "1.276"}, 
         {"0.25514", "1.275"}, 
         {"0.25620", "1.274"}, 
         {"0.25726", "1.273"}, 
         {"0.25833", "1.271"}, 
         {"0.25941", "1.269"}, 
         {"0.26050", "1.268"}, 
         {"0.26160", "1.265"}, 
         {"0.26271", "1.263"}, 
         {"0.26383", "1.260"}, 
         {"0.26496", "1.257"}, 
         {"0.26609", "1.254"}, 
         {"0.26724", "1.250"}, 
         {"0.26840", "1.246"}, 
         {"0.26957", "1.242"}, 
         {"0.27074", "1.237"}, 
         {"0.27193", "1.232"}, 
         {"0.27313", "1.226"}, 
         {"0.27434", "1.220"}, 
         {"0.27556", "1.213"}, 
         {"0.27679", "1.206"}, 
         {"0.27803", "1.198"}, 
         {"0.27928", "1.189"}, 
         {"0.28054", "1.179"}, 
         {"0.28182", "1.168"}, 
         {"0.28311", "1.157"}, 
         {"0.28440", "1.144"}, 
         {"0.28571", "1.130"}, 
         {"0.28704", "1.114"}, 
         {"0.28837", "1.097"}, 
         {"0.28972", "1.078"}, 
         {"0.29108", "1.057"}, 
         {"0.29245", "1.034"}, 
         {"0.29384", "1.008"}, 
         {"0.29524", "0.980"}, 
         {"0.29665", "0.948"}, 
         {"0.29808", "0.912"}, 
         {"0.29952", "0.873"}, 
         {"0.30097", "0.829"}, 
         {"0.30244", "0.781"}, 
         {"0.30392", "0.728"}, 
         {"0.30542", "0.671"}, 
         {"0.30693", "0.612"}, 
         {"0.30846", "0.554"}, 
         {"0.31000", "0.506"}, 
         {"0.31156", "0.477"}, 
         {"0.31313", "0.455"}, 
         {"0.31472", "0.417"}, 
         {"0.31633", "0.379"}, 
         {"0.31795", "0.361"}, 
         {"0.31959", "0.368"}, 
         {"0.32124", "0.393"}, 
         {"0.32292", "0.434"}, 
         {"0.32461", "0.488"}, 
         {"0.32632", "0.552"}, 
         {"0.32804", "0.621"}, 
         {"0.32979", "0.690"}, 
         {"0.33155", "0.757"}, 
         {"0.33333", "0.819"}, 
         {"0.33514", "0.878"}, 
         {"0.33696", "0.932"}, 
         {"0.33880", "0.983"}, 
         {"0.34066", "1.031"}, 
         {"0.34254", "1.076"}, 
         {"0.34444", "1.118"}, 
         {"0.34637", "1.158"}, 
         {"0.34831", "1.196"}, 
         {"0.35028", "1.232"}, 
         {"0.35227", "1.267"}, 
         {"0.35429", "1.301"}, 
         {"0.35632", "1.333"}, 
         {"0.35838", "1.364"}, 
         {"0.36047", "1.394"}, 
         {"0.36257", "1.423"}, 
         {"0.36471", "1.451"}, 
         {"0.36686", "1.479"}, 
         {"0.36905", "1.506"}, 
         {"0.37126", "1.533"}, 
         {"0.37349", "1.560"}, 
         {"0.37576", "1.586"}, 
         {"0.37805", "1.612"}, 
         {"0.38037", "1.637"}, 
         {"0.38272", "1.663"}, 
         {"0.38509", "1.689"}, 
         {"0.38750", "1.715"}, 
         {"0.38994", "1.740"}, 
         {"0.39241", "1.766"}, 
         {"0.39490", "1.792"}, 
         {"0.39744", "1.819"}, 
         {"0.40000", "1.845"}, 
         {"0.40260", "1.872"}, 
         {"0.40523", "1.899"}, 
         {"0.40789", "1.927"}, 
         {"0.41060", "1.954"}, 
         {"0.41333", "1.983"}, 
         {"0.41611", "2.011"}, 
         {"0.41892", "2.039"}, 
         {"0.42177", "2.068"}, 
         {"0.42466", "2.098"}, 
         {"0.42759", "2.127"}, 
         {"0.43056", "2.157"}, 
         {"0.43357", "2.187"}, 
         {"0.43662", "2.218"}, 
         {"0.43972", "2.249"}, 
         {"0.44286", "2.280"}, 
         {"0.44604", "2.311"}, 
         {"0.44928", "2.343"}, 
         {"0.45255", "2.375"}, 
         {"0.45588", "2.407"}, 
         {"0.45926", "2.440"}, 
         {"0.46269", "2.473"}, 
         {"0.46617", "2.506"}, 
         {"0.46970", "2.539"}, 
         {"0.47328", "2.573"}, 
         {"0.47692", "2.607"}, 
         {"0.48062", "2.641"}, 
         {"0.48438", "2.676"}, 
         {"0.48819", "2.711"}, 
         {"0.49206", "2.747"}, 
         {"0.49600", "2.782"}, 
         {"0.50000", "2.818"}, 
         {"0.50407", "2.855"}, 
         {"0.50820", "2.892"}, 
         {"0.51240", "2.929"}, 
         {"0.51667", "2.967"}, 
         {"0.52101", "3.005"}, 
         {"0.52542", "3.043"}, 
         {"0.52991", "3.082"}, 
         {"0.53448", "3.121"}, 
         {"0.53913", "3.161"}, 
         {"0.54386", "3.202"}, 
         {"0.54867", "3.243"}, 
         {"0.55357", "3.284"}, 
         {"0.55856", "3.326"}, 
         {"0.56364", "3.368"}, 
         {"0.56881", "3.411"}, 
         {"0.57407", "3.455"}, 
         {"0.57944", "3.499"}, 
         {"0.58491", "3.544"}, 
         {"0.59048", "3.590"}, 
         {"0.59615", "3.636"}, 
         {"0.60194", "3.683"}, 
         {"0.60784", "3.730"}, 
         {"0.61386", "3.779"}, 
         {"0.62000", "3.828"}, 
         {"0.62626", "3.878"}, 
         {"0.63265", "3.929"}, 
         {"0.63918", "3.980"}, 
         {"0.64583", "4.033"}, 
         {"0.65263", "4.086"}, 
         {"0.65957", "4.141"}, 
         {"0.66667", "4.196"}, 
         {"0.67391", "4.253"}, 
         {"0.68132", "4.310"}, 
         {"0.68889", "4.369"}, 
         {"0.69663", "4.428"}, 
         {"0.70455", "4.489"}, 
         {"0.71264", "4.551"}, 
         {"0.72093", "4.615"}, 
         {"0.72941", "4.680"}, 
         {"0.73810", "4.746"}, 
         {"0.74699", "4.813"}, 
         {"0.75610", "4.882"}, 
         {"0.76543", "4.952"}, 
         {"0.77500", "5.024"}, 
         {"0.78481", "5.098"}, 
         {"0.79487", "5.174"}, 
         {"0.80519", "5.251"}, 
         {"0.81579", "5.330"}, 
         {"0.82667", "5.411"}, 
         {"0.83784", "5.494"}, 
         {"0.84932", "5.579"}, 
         {"0.86111", "5.666"}, 
         {"0.87324", "5.756"}, 
         {"0.88571", "5.847"}, 
         {"0.89855", "5.942"}, 
         {"0.91176", "6.039"}, 
         {"0.92537", "6.138"}, 
         {"0.93939", "6.241"}, 
         {"0.95385", "6.346"}, 
         {"0.96875", "6.455"}, 
         {"0.98413", "6.567"}, 
         {"1.0000", "6.682"}, 
         {"1.0164", "6.801"}, 
         {"1.0333", "6.924"}, 
         {"1.0508", "7.050"}, 
         {"1.0690", "7.181"}, 
         {"1.0877", "7.316"}, 
         {"1.1071", "7.456"}, 
         {"1.1273", "7.601"}, 
         {"1.1481", "7.750"}, 
         {"1.1698", "7.906"}, 
         {"1.1923", "8.067"}, 
         {"1.2157", "8.234"}, 
         {"1.2400", "8.407"}, 
         {"1.2653", "8.588"}, 
         {"1.2917", "8.776"}, 
         {"1.3191", "8.971"}, 
         {"1.3478", "9.175"}, 
         {"1.4419", "9.841"}, 
         {"1.4762", "10.084"}, 
         {"1.5122", "10.338"}, 
         {"1.5398", "10.533"}, 
         {"1.5500", "10.605"}, 
         {"1.5773", "10.798"}, 
         {"1.5897", "10.885"}, 
         {"1.6166", "11.075"}, 
         {"1.6316", "11.180"}, 
         {"1.6580", "11.366"}, 
         {"1.6757", "11.490"}, 
         {"1.7015", "11.672"}, 
         {"1.7222", "11.817"}, 
         {"1.7474", "11.994"}, 
         {"1.7714", "12.163"}, 
         {"1.7958", "12.334"}, 
         {"1.8235", "12.528"}, 
         {"1.8469", "12.692"}, 
         {"1.8788", "12.915"}, 
         {"1.9011", "13.072"}, 
         {"1.9375", "13.326"}, 
         {"1.9585", "13.473"}, 
         {"2.0000", "13.763"}, 
         {"2.0195", "13.900"}, 
         {"2.0845", "14.353"}, 
         {"2.1537", "14.836"}, 
         {"2.2277", "15.352"}, 
         {"2.3070", "15.904"}, 
         {"2.3921", "16.496"}, 
         {"2.4838", "17.133"}, 
         {"2.5827", "17.819"}, 
         {"2.6899", "18.562"}, 
         {"2.8064", "19.367"}, 
         {"2.9333", "20.245"}, 
         {"3.0724", "21.204"}, 
         {"3.2252", "22.256"}, 
         {"3.3941", "23.417"}, 
         {"3.5816", "24.703"}, 
         {"3.7911", "26.136"}, 
         {"4.0265", "27.742"}, 
         {"4.2932", "29.555"}, 
         {"4.5977", "31.618"}, 
         {"4.9486", "33.984"}, 
         {"5.3576", "36.725"}, 
         {"5.8403", "39.937"}, 
         {"6.4185", "43.751"}, 
         {"7.1239", "48.347"}, 
         {"8.0033", "53.987"}, 
         {"9.1305", "61.058"}, 
         {"10.627", "70.151"}, 
         {"12.711", "82.212"}, 
         {"15.811", "98.816"}, 
         {"20.912", "122.727"}});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int2")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int2")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").addInput("frequency");
    model.component("comp1").material("mat2").selection().named("geom1_arr1_dom");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "\u6298\u5c04\u7387");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func()
         .create("int2", "Interpolation");
    model.component("comp1").material("mat3")
         .label("SiO2 (Silicon dioxide, Silica, Quartz) (Gao et al. 2013: Thin film; n,k 0.252-1.25 um)");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int1").set("funcname", "nr");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int1")
         .set("table", new String[][]{{"0.252", "1.523846"}, 
         {"0.254", "1.522945"}, 
         {"0.256", "1.522066"}, 
         {"0.258", "1.521208"}, 
         {"0.260", "1.520370"}, 
         {"0.262", "1.519552"}, 
         {"0.264", "1.518753"}, 
         {"0.266", "1.517972"}, 
         {"0.268", "1.517209"}, 
         {"0.270", "1.516463"}, 
         {"0.272", "1.515734"}, 
         {"0.274", "1.515021"}, 
         {"0.276", "1.514325"}, 
         {"0.278", "1.513643"}, 
         {"0.280", "1.512977"}, 
         {"0.282", "1.512325"}, 
         {"0.284", "1.511687"}, 
         {"0.286", "1.511063"}, 
         {"0.288", "1.510452"}, 
         {"0.290", "1.509854"}, 
         {"0.292", "1.509268"}, 
         {"0.294", "1.508695"}, 
         {"0.296", "1.508134"}, 
         {"0.298", "1.507584"}, 
         {"0.300", "1.507046"}, 
         {"0.302", "1.506518"}, 
         {"0.304", "1.506002"}, 
         {"0.306", "1.505495"}, 
         {"0.308", "1.504999"}, 
         {"0.310", "1.504512"}, 
         {"0.312", "1.504035"}, 
         {"0.314", "1.503568"}, 
         {"0.316", "1.503109"}, 
         {"0.318", "1.502660"}, 
         {"0.320", "1.502219"}, 
         {"0.322", "1.501786"}, 
         {"0.324", "1.501361"}, 
         {"0.326", "1.500945"}, 
         {"0.328", "1.500536"}, 
         {"0.330", "1.500135"}, 
         {"0.332", "1.499742"}, 
         {"0.334", "1.499355"}, 
         {"0.336", "1.498976"}, 
         {"0.338", "1.498603"}, 
         {"0.340", "1.498238"}, 
         {"0.342", "1.497878"}, 
         {"0.344", "1.497525"}, 
         {"0.346", "1.497179"}, 
         {"0.348", "1.496838"}, 
         {"0.350", "1.496504"}, 
         {"0.352", "1.496175"}, 
         {"0.354", "1.495852"}, 
         {"0.356", "1.495534"}, 
         {"0.358", "1.495222"}, 
         {"0.360", "1.494915"}, 
         {"0.362", "1.494613"}, 
         {"0.364", "1.494316"}, 
         {"0.366", "1.494025"}, 
         {"0.368", "1.493738"}, 
         {"0.370", "1.493455"}, 
         {"0.372", "1.493177"}, 
         {"0.374", "1.492904"}, 
         {"0.376", "1.492635"}, 
         {"0.378", "1.492370"}, 
         {"0.380", "1.492110"}, 
         {"0.382", "1.491854"}, 
         {"0.384", "1.491601"}, 
         {"0.386", "1.491353"}, 
         {"0.388", "1.491108"}, 
         {"0.390", "1.490867"}, 
         {"0.392", "1.490629"}, 
         {"0.394", "1.490396"}, 
         {"0.396", "1.490165"}, 
         {"0.398", "1.489938"}, 
         {"0.400", "1.489714"}, 
         {"0.402", "1.489494"}, 
         {"0.404", "1.489276"}, 
         {"0.406", "1.489062"}, 
         {"0.408", "1.488850"}, 
         {"0.410", "1.488641"}, 
         {"0.412", "1.488435"}, 
         {"0.414", "1.488231"}, 
         {"0.416", "1.488031"}, 
         {"0.418", "1.487833"}, 
         {"0.420", "1.487638"}, 
         {"0.422", "1.487446"}, 
         {"0.424", "1.487256"}, 
         {"0.426", "1.487069"}, 
         {"0.428", "1.486884"}, 
         {"0.430", "1.486702"}, 
         {"0.432", "1.486522"}, 
         {"0.434", "1.486345"}, 
         {"0.436", "1.486170"}, 
         {"0.438", "1.485997"}, 
         {"0.440", "1.485827"}, 
         {"0.442", "1.485659"}, 
         {"0.444", "1.485493"}, 
         {"0.446", "1.485329"}, 
         {"0.448", "1.485167"}, 
         {"0.450", "1.485008"}, 
         {"0.452", "1.484851"}, 
         {"0.454", "1.484695"}, 
         {"0.456", "1.484542"}, 
         {"0.458", "1.484390"}, 
         {"0.460", "1.484241"}, 
         {"0.462", "1.484094"}, 
         {"0.464", "1.483948"}, 
         {"0.466", "1.483804"}, 
         {"0.468", "1.483662"}, 
         {"0.470", "1.483522"}, 
         {"0.472", "1.483383"}, 
         {"0.474", "1.483247"}, 
         {"0.476", "1.483111"}, 
         {"0.478", "1.482978"}, 
         {"0.480", "1.482846"}, 
         {"0.482", "1.482716"}, 
         {"0.484", "1.482588"}, 
         {"0.486", "1.482461"}, 
         {"0.488", "1.482335"}, 
         {"0.490", "1.482211"}, 
         {"0.492", "1.482089"}, 
         {"0.494", "1.481968"}, 
         {"0.496", "1.481848"}, 
         {"0.498", "1.481730"}, 
         {"0.500", "1.481613"}, 
         {"0.502", "1.481498"}, 
         {"0.504", "1.481384"}, 
         {"0.506", "1.481271"}, 
         {"0.508", "1.481160"}, 
         {"0.510", "1.481050"}, 
         {"0.512", "1.480941"}, 
         {"0.514", "1.480834"}, 
         {"0.516", "1.480727"}, 
         {"0.518", "1.480622"}, 
         {"0.520", "1.480518"}, 
         {"0.522", "1.480416"}, 
         {"0.524", "1.480314"}, 
         {"0.526", "1.480214"}, 
         {"0.528", "1.480115"}, 
         {"0.530", "1.480016"}, 
         {"0.532", "1.479919"}, 
         {"0.534", "1.479823"}, 
         {"0.536", "1.479729"}, 
         {"0.538", "1.479635"}, 
         {"0.540", "1.479542"}, 
         {"0.542", "1.479450"}, 
         {"0.544", "1.479359"}, 
         {"0.546", "1.479269"}, 
         {"0.548", "1.479181"}, 
         {"0.550", "1.479093"}, 
         {"0.552", "1.479006"}, 
         {"0.554", "1.478920"}, 
         {"0.556", "1.478835"}, 
         {"0.558", "1.478751"}, 
         {"0.560", "1.478667"}, 
         {"0.562", "1.478585"}, 
         {"0.564", "1.478503"}, 
         {"0.566", "1.478423"}, 
         {"0.568", "1.478343"}, 
         {"0.570", "1.478264"}, 
         {"0.572", "1.478186"}, 
         {"0.574", "1.478108"}, 
         {"0.576", "1.478032"}, 
         {"0.578", "1.477956"}, 
         {"0.580", "1.477881"}, 
         {"0.582", "1.477807"}, 
         {"0.584", "1.477733"}, 
         {"0.586", "1.477660"}, 
         {"0.588", "1.477588"}, 
         {"0.590", "1.477517"}, 
         {"0.592", "1.477447"}, 
         {"0.594", "1.477377"}, 
         {"0.596", "1.477308"}, 
         {"0.598", "1.477239"}, 
         {"0.600", "1.477171"}, 
         {"0.602", "1.477104"}, 
         {"0.604", "1.477038"}, 
         {"0.606", "1.476972"}, 
         {"0.608", "1.476907"}, 
         {"0.610", "1.476842"}, 
         {"0.612", "1.476778"}, 
         {"0.614", "1.476715"}, 
         {"0.616", "1.476652"}, 
         {"0.618", "1.476590"}, 
         {"0.620", "1.476529"}, 
         {"0.622", "1.476468"}, 
         {"0.624", "1.476408"}, 
         {"0.626", "1.476348"}, 
         {"0.628", "1.476289"}, 
         {"0.630", "1.476230"}, 
         {"0.632", "1.476172"}, 
         {"0.634", "1.476115"}, 
         {"0.636", "1.476058"}, 
         {"0.638", "1.476001"}, 
         {"0.640", "1.475946"}, 
         {"0.642", "1.475890"}, 
         {"0.644", "1.475835"}, 
         {"0.646", "1.475781"}, 
         {"0.648", "1.475727"}, 
         {"0.650", "1.475674"}, 
         {"0.652", "1.475621"}, 
         {"0.654", "1.475568"}, 
         {"0.656", "1.475517"}, 
         {"0.658", "1.475465"}, 
         {"0.660", "1.475414"}, 
         {"0.662", "1.475364"}, 
         {"0.664", "1.475314"}, 
         {"0.666", "1.475264"}, 
         {"0.668", "1.475215"}, 
         {"0.670", "1.475166"}, 
         {"0.672", "1.475118"}, 
         {"0.674", "1.475070"}, 
         {"0.676", "1.475022"}, 
         {"0.678", "1.474975"}, 
         {"0.680", "1.474929"}, 
         {"0.682", "1.474883"}, 
         {"0.684", "1.474837"}, 
         {"0.686", "1.474791"}, 
         {"0.688", "1.474746"}, 
         {"0.690", "1.474702"}, 
         {"0.692", "1.474657"}, 
         {"0.694", "1.474614"}, 
         {"0.696", "1.474570"}, 
         {"0.698", "1.474527"}, 
         {"0.700", "1.474484"}, 
         {"0.702", "1.474442"}, 
         {"0.704", "1.474400"}, 
         {"0.706", "1.474358"}, 
         {"0.708", "1.474317"}, 
         {"0.710", "1.474276"}, 
         {"0.712", "1.474235"}, 
         {"0.714", "1.474195"}, 
         {"0.716", "1.474155"}, 
         {"0.718", "1.474115"}, 
         {"0.720", "1.474076"}, 
         {"0.722", "1.474037"}, 
         {"0.724", "1.473998"}, 
         {"0.726", "1.473960"}, 
         {"0.728", "1.473922"}, 
         {"0.730", "1.473884"}, 
         {"0.732", "1.473847"}, 
         {"0.734", "1.473810"}, 
         {"0.736", "1.473773"}, 
         {"0.738", "1.473737"}, 
         {"0.740", "1.473700"}, 
         {"0.742", "1.473665"}, 
         {"0.744", "1.473629"}, 
         {"0.746", "1.473594"}, 
         {"0.748", "1.473559"}, 
         {"0.750", "1.473524"}, 
         {"0.752", "1.473489"}, 
         {"0.754", "1.473455"}, 
         {"0.756", "1.473421"}, 
         {"0.758", "1.473387"}, 
         {"0.760", "1.473354"}, 
         {"0.762", "1.473321"}, 
         {"0.764", "1.473288"}, 
         {"0.766", "1.473255"}, 
         {"0.768", "1.473223"}, 
         {"0.770", "1.473191"}, 
         {"0.772", "1.473159"}, 
         {"0.774", "1.473127"}, 
         {"0.776", "1.473096"}, 
         {"0.778", "1.473065"}, 
         {"0.780", "1.473034"}, 
         {"0.782", "1.473003"}, 
         {"0.784", "1.472973"}, 
         {"0.786", "1.472942"}, 
         {"0.788", "1.472912"}, 
         {"0.790", "1.472883"}, 
         {"0.792", "1.472853"}, 
         {"0.794", "1.472824"}, 
         {"0.796", "1.472795"}, 
         {"0.798", "1.472766"}, 
         {"0.800", "1.472737"}, 
         {"0.802", "1.472709"}, 
         {"0.804", "1.472680"}, 
         {"0.806", "1.472652"}, 
         {"0.808", "1.472625"}, 
         {"0.810", "1.472597"}, 
         {"0.812", "1.472570"}, 
         {"0.814", "1.472542"}, 
         {"0.816", "1.472515"}, 
         {"0.818", "1.472489"}, 
         {"0.820", "1.472462"}, 
         {"0.822", "1.472436"}, 
         {"0.824", "1.472409"}, 
         {"0.826", "1.472383"}, 
         {"0.828", "1.472357"}, 
         {"0.830", "1.472332"}, 
         {"0.832", "1.472306"}, 
         {"0.834", "1.472281"}, 
         {"0.836", "1.472256"}, 
         {"0.838", "1.472231"}, 
         {"0.840", "1.472206"}, 
         {"0.842", "1.472182"}, 
         {"0.844", "1.472157"}, 
         {"0.846", "1.472133"}, 
         {"0.848", "1.472109"}, 
         {"0.850", "1.472085"}, 
         {"0.852", "1.472061"}, 
         {"0.854", "1.472038"}, 
         {"0.856", "1.472014"}, 
         {"0.858", "1.471991"}, 
         {"0.860", "1.471968"}, 
         {"0.862", "1.471945"}, 
         {"0.864", "1.471922"}, 
         {"0.866", "1.471900"}, 
         {"0.868", "1.471877"}, 
         {"0.870", "1.471855"}, 
         {"0.872", "1.471833"}, 
         {"0.874", "1.471811"}, 
         {"0.876", "1.471789"}, 
         {"0.878", "1.471767"}, 
         {"0.880", "1.471746"}, 
         {"0.882", "1.471724"}, 
         {"0.884", "1.471703"}, 
         {"0.886", "1.471682"}, 
         {"0.888", "1.471661"}, 
         {"0.890", "1.471640"}, 
         {"0.892", "1.471620"}, 
         {"0.894", "1.471599"}, 
         {"0.896", "1.471579"}, 
         {"0.898", "1.471558"}, 
         {"0.900", "1.471538"}, 
         {"0.902", "1.471518"}, 
         {"0.904", "1.471498"}, 
         {"0.906", "1.471479"}, 
         {"0.908", "1.471459"}, 
         {"0.910", "1.471439"}, 
         {"0.912", "1.471420"}, 
         {"0.914", "1.471401"}, 
         {"0.916", "1.471382"}, 
         {"0.918", "1.471363"}, 
         {"0.920", "1.471344"}, 
         {"0.922", "1.471325"}, 
         {"0.924", "1.471307"}, 
         {"0.926", "1.471288"}, 
         {"0.928", "1.471270"}, 
         {"0.930", "1.471251"}, 
         {"0.932", "1.471233"}, 
         {"0.934", "1.471215"}, 
         {"0.936", "1.471197"}, 
         {"0.938", "1.471180"}, 
         {"0.940", "1.471162"}, 
         {"0.942", "1.471144"}, 
         {"0.944", "1.471127"}, 
         {"0.946", "1.471110"}, 
         {"0.948", "1.471092"}, 
         {"0.950", "1.471075"}, 
         {"0.952", "1.471058"}, 
         {"0.954", "1.471041"}, 
         {"0.956", "1.471025"}, 
         {"0.958", "1.471008"}, 
         {"0.960", "1.470991"}, 
         {"0.962", "1.470975"}, 
         {"0.964", "1.470958"}, 
         {"0.966", "1.470942"}, 
         {"0.968", "1.470926"}, 
         {"0.970", "1.470910"}, 
         {"0.972", "1.470894"}, 
         {"0.974", "1.470878"}, 
         {"0.976", "1.470862"}, 
         {"0.978", "1.470846"}, 
         {"0.980", "1.470831"}, 
         {"0.982", "1.470815"}, 
         {"0.984", "1.470800"}, 
         {"0.986", "1.470785"}, 
         {"0.988", "1.470769"}, 
         {"0.990", "1.470754"}, 
         {"0.992", "1.470739"}, 
         {"0.994", "1.470724"}, 
         {"0.996", "1.470709"}, 
         {"0.998", "1.470695"}, 
         {"1.000", "1.470680"}, 
         {"1.002", "1.470665"}, 
         {"1.004", "1.470651"}, 
         {"1.006", "1.470636"}, 
         {"1.008", "1.470622"}, 
         {"1.010", "1.470608"}, 
         {"1.012", "1.470594"}, 
         {"1.014", "1.470580"}, 
         {"1.016", "1.470566"}, 
         {"1.018", "1.470552"}, 
         {"1.020", "1.470538"}, 
         {"1.022", "1.470524"}, 
         {"1.024", "1.470510"}, 
         {"1.026", "1.470497"}, 
         {"1.028", "1.470483"}, 
         {"1.030", "1.470470"}, 
         {"1.032", "1.470456"}, 
         {"1.034", "1.470443"}, 
         {"1.036", "1.470430"}, 
         {"1.038", "1.470417"}, 
         {"1.040", "1.470404"}, 
         {"1.042", "1.470391"}, 
         {"1.044", "1.470378"}, 
         {"1.046", "1.470365"}, 
         {"1.048", "1.470352"}, 
         {"1.050", "1.470340"}, 
         {"1.052", "1.470327"}, 
         {"1.054", "1.470314"}, 
         {"1.056", "1.470302"}, 
         {"1.058", "1.470290"}, 
         {"1.060", "1.470277"}, 
         {"1.062", "1.470265"}, 
         {"1.064", "1.470253"}, 
         {"1.066", "1.470241"}, 
         {"1.068", "1.470229"}, 
         {"1.070", "1.470217"}, 
         {"1.072", "1.470205"}, 
         {"1.074", "1.470193"}, 
         {"1.076", "1.470181"}, 
         {"1.078", "1.470169"}, 
         {"1.080", "1.470158"}, 
         {"1.082", "1.470146"}, 
         {"1.084", "1.470135"}, 
         {"1.086", "1.470123"}, 
         {"1.088", "1.470112"}, 
         {"1.090", "1.470100"}, 
         {"1.092", "1.470089"}, 
         {"1.094", "1.470078"}, 
         {"1.096", "1.470067"}, 
         {"1.098", "1.470056"}, 
         {"1.100", "1.470045"}, 
         {"1.102", "1.470034"}, 
         {"1.104", "1.470023"}, 
         {"1.106", "1.470012"}, 
         {"1.108", "1.470001"}, 
         {"1.110", "1.469990"}, 
         {"1.112", "1.469980"}, 
         {"1.114", "1.469969"}, 
         {"1.116", "1.469958"}, 
         {"1.118", "1.469948"}, 
         {"1.120", "1.469937"}, 
         {"1.122", "1.469927"}, 
         {"1.124", "1.469917"}, 
         {"1.126", "1.469906"}, 
         {"1.128", "1.469896"}, 
         {"1.130", "1.469886"}, 
         {"1.132", "1.469876"}, 
         {"1.134", "1.469866"}, 
         {"1.136", "1.469856"}, 
         {"1.138", "1.469846"}, 
         {"1.140", "1.469836"}, 
         {"1.142", "1.469826"}, 
         {"1.144", "1.469816"}, 
         {"1.146", "1.469807"}, 
         {"1.148", "1.469797"}, 
         {"1.150", "1.469787"}, 
         {"1.152", "1.469778"}, 
         {"1.154", "1.469768"}, 
         {"1.156", "1.469758"}, 
         {"1.158", "1.469749"}, 
         {"1.160", "1.469740"}, 
         {"1.162", "1.469730"}, 
         {"1.164", "1.469721"}, 
         {"1.166", "1.469712"}, 
         {"1.168", "1.469702"}, 
         {"1.170", "1.469693"}, 
         {"1.172", "1.469684"}, 
         {"1.174", "1.469675"}, 
         {"1.176", "1.469666"}, 
         {"1.178", "1.469657"}, 
         {"1.180", "1.469648"}, 
         {"1.182", "1.469639"}, 
         {"1.184", "1.469630"}, 
         {"1.186", "1.469622"}, 
         {"1.188", "1.469613"}, 
         {"1.190", "1.469604"}, 
         {"1.192", "1.469595"}, 
         {"1.194", "1.469587"}, 
         {"1.196", "1.469578"}, 
         {"1.198", "1.469570"}, 
         {"1.200", "1.469561"}, 
         {"1.202", "1.469553"}, 
         {"1.204", "1.469544"}, 
         {"1.206", "1.469536"}, 
         {"1.208", "1.469528"}, 
         {"1.210", "1.469519"}, 
         {"1.212", "1.469511"}, 
         {"1.214", "1.469503"}, 
         {"1.216", "1.469495"}, 
         {"1.218", "1.469487"}, 
         {"1.220", "1.469478"}, 
         {"1.222", "1.469470"}, 
         {"1.224", "1.469462"}, 
         {"1.226", "1.469454"}, 
         {"1.228", "1.469447"}, 
         {"1.230", "1.469439"}, 
         {"1.232", "1.469431"}, 
         {"1.234", "1.469423"}, 
         {"1.236", "1.469415"}, 
         {"1.238", "1.469407"}, 
         {"1.240", "1.469400"}, 
         {"1.242", "1.469392"}, 
         {"1.244", "1.469384"}, 
         {"1.246", "1.469377"}, 
         {"1.248", "1.469369"}, 
         {"1.250", "1.469362"}});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int1")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int2").set("funcname", "ni");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int2")
         .set("table", new String[][]{{"0.252", "0.001211"}, 
         {"0.254", "0.001171"}, 
         {"0.256", "0.001131"}, 
         {"0.258", "0.001093"}, 
         {"0.260", "0.001055"}, 
         {"0.262", "0.001019"}, 
         {"0.264", "0.000983"}, 
         {"0.266", "0.000949"}, 
         {"0.268", "0.000915"}, 
         {"0.270", "0.000882"}, 
         {"0.272", "0.000850"}, 
         {"0.274", "0.000819"}, 
         {"0.276", "0.000789"}, 
         {"0.278", "0.000759"}, 
         {"0.280", "0.000730"}, 
         {"0.282", "0.000702"}, 
         {"0.284", "0.000675"}, 
         {"0.286", "0.000649"}, 
         {"0.288", "0.000623"}, 
         {"0.290", "0.000598"}, 
         {"0.292", "0.000574"}, 
         {"0.294", "0.000550"}, 
         {"0.296", "0.000527"}, 
         {"0.298", "0.000505"}, 
         {"0.300", "0.000483"}, 
         {"0.302", "0.000462"}, 
         {"0.304", "0.000441"}, 
         {"0.306", "0.000421"}, 
         {"0.308", "0.000402"}, 
         {"0.310", "0.000383"}, 
         {"0.312", "0.000365"}, 
         {"0.314", "0.000348"}, 
         {"0.316", "0.000331"}, 
         {"0.318", "0.000314"}, 
         {"0.320", "0.000298"}, 
         {"0.322", "0.000283"}, 
         {"0.324", "0.000268"}, 
         {"0.326", "0.000253"}, 
         {"0.328", "0.000240"}, 
         {"0.330", "0.000226"}, 
         {"0.332", "0.000213"}, 
         {"0.334", "0.000200"}, 
         {"0.336", "0.000188"}, 
         {"0.338", "0.000177"}, 
         {"0.340", "0.000166"}, 
         {"0.342", "0.000155"}, 
         {"0.344", "0.000144"}, 
         {"0.346", "0.000135"}, 
         {"0.348", "0.000125"}, 
         {"0.350", "0.000116"}, 
         {"0.352", "0.000107"}, 
         {"0.354", "0.000099"}, 
         {"0.356", "0.000091"}, 
         {"0.358", "0.000083"}, 
         {"0.360", "0.000076"}, 
         {"0.362", "0.000069"}, 
         {"0.364", "0.000063"}, 
         {"0.366", "0.000057"}, 
         {"0.368", "0.000051"}, 
         {"0.370", "0.000045"}, 
         {"0.372", "0.000040"}, 
         {"0.374", "0.000036"}, 
         {"0.376", "0.000031"}, 
         {"0.378", "0.000027"}, 
         {"0.380", "0.000023"}, 
         {"0.382", "0.000020"}, 
         {"0.384", "0.000016"}, 
         {"0.386", "0.000014"}, 
         {"0.388", "0.000011"}, 
         {"0.390", "0.000009"}, 
         {"0.392", "0.000007"}, 
         {"0.394", "0.000005"}, 
         {"0.396", "0.000003"}, 
         {"0.398", "0.000002"}, 
         {"0.400", "0.000001"}, 
         {"0.402", "0.000001"}, 
         {"0.404", "0.000000"}, 
         {"0.406", "0.000000"}, 
         {"0.408", "0.000000"}, 
         {"0.410", "0.000000"}, 
         {"0.412", "0.000000"}, 
         {"0.414", "0.000000"}, 
         {"0.416", "0.000000"}, 
         {"0.418", "0.000000"}, 
         {"0.420", "0.000000"}, 
         {"0.422", "0.000000"}, 
         {"0.424", "0.000000"}, 
         {"0.426", "0.000000"}, 
         {"0.428", "0.000000"}, 
         {"0.430", "0.000000"}, 
         {"0.432", "0.000000"}, 
         {"0.434", "0.000000"}, 
         {"0.436", "0.000000"}, 
         {"0.438", "0.000000"}, 
         {"0.440", "0.000000"}, 
         {"0.442", "0.000000"}, 
         {"0.444", "0.000000"}, 
         {"0.446", "0.000000"}, 
         {"0.448", "0.000000"}, 
         {"0.450", "0.000000"}, 
         {"0.452", "0.000000"}, 
         {"0.454", "0.000000"}, 
         {"0.456", "0.000000"}, 
         {"0.458", "0.000000"}, 
         {"0.460", "0.000000"}, 
         {"0.462", "0.000000"}, 
         {"0.464", "0.000000"}, 
         {"0.466", "0.000000"}, 
         {"0.468", "0.000000"}, 
         {"0.470", "0.000000"}, 
         {"0.472", "0.000000"}, 
         {"0.474", "0.000000"}, 
         {"0.476", "0.000000"}, 
         {"0.478", "0.000000"}, 
         {"0.480", "0.000000"}, 
         {"0.482", "0.000000"}, 
         {"0.484", "0.000000"}, 
         {"0.486", "0.000000"}, 
         {"0.488", "0.000000"}, 
         {"0.490", "0.000000"}, 
         {"0.492", "0.000000"}, 
         {"0.494", "0.000000"}, 
         {"0.496", "0.000000"}, 
         {"0.498", "0.000000"}, 
         {"0.500", "0.000000"}, 
         {"0.502", "0.000000"}, 
         {"0.504", "0.000000"}, 
         {"0.506", "0.000000"}, 
         {"0.508", "0.000000"}, 
         {"0.510", "0.000000"}, 
         {"0.512", "0.000000"}, 
         {"0.514", "0.000000"}, 
         {"0.516", "0.000000"}, 
         {"0.518", "0.000000"}, 
         {"0.520", "0.000000"}, 
         {"0.522", "0.000000"}, 
         {"0.524", "0.000000"}, 
         {"0.526", "0.000000"}, 
         {"0.528", "0.000000"}, 
         {"0.530", "0.000000"}, 
         {"0.532", "0.000000"}, 
         {"0.534", "0.000000"}, 
         {"0.536", "0.000000"}, 
         {"0.538", "0.000000"}, 
         {"0.540", "0.000000"}, 
         {"0.542", "0.000000"}, 
         {"0.544", "0.000000"}, 
         {"0.546", "0.000000"}, 
         {"0.548", "0.000000"}, 
         {"0.550", "0.000000"}, 
         {"0.552", "0.000000"}, 
         {"0.554", "0.000000"}, 
         {"0.556", "0.000000"}, 
         {"0.558", "0.000000"}, 
         {"0.560", "0.000000"}, 
         {"0.562", "0.000000"}, 
         {"0.564", "0.000000"}, 
         {"0.566", "0.000000"}, 
         {"0.568", "0.000000"}, 
         {"0.570", "0.000000"}, 
         {"0.572", "0.000000"}, 
         {"0.574", "0.000000"}, 
         {"0.576", "0.000000"}, 
         {"0.578", "0.000000"}, 
         {"0.580", "0.000000"}, 
         {"0.582", "0.000000"}, 
         {"0.584", "0.000000"}, 
         {"0.586", "0.000000"}, 
         {"0.588", "0.000000"}, 
         {"0.590", "0.000000"}, 
         {"0.592", "0.000000"}, 
         {"0.594", "0.000000"}, 
         {"0.596", "0.000000"}, 
         {"0.598", "0.000000"}, 
         {"0.600", "0.000000"}, 
         {"0.602", "0.000000"}, 
         {"0.604", "0.000000"}, 
         {"0.606", "0.000000"}, 
         {"0.608", "0.000000"}, 
         {"0.610", "0.000000"}, 
         {"0.612", "0.000000"}, 
         {"0.614", "0.000000"}, 
         {"0.616", "0.000000"}, 
         {"0.618", "0.000000"}, 
         {"0.620", "0.000000"}, 
         {"0.622", "0.000000"}, 
         {"0.624", "0.000000"}, 
         {"0.626", "0.000000"}, 
         {"0.628", "0.000000"}, 
         {"0.630", "0.000000"}, 
         {"0.632", "0.000000"}, 
         {"0.634", "0.000000"}, 
         {"0.636", "0.000000"}, 
         {"0.638", "0.000000"}, 
         {"0.640", "0.000000"}, 
         {"0.642", "0.000000"}, 
         {"0.644", "0.000000"}, 
         {"0.646", "0.000000"}, 
         {"0.648", "0.000000"}, 
         {"0.650", "0.000000"}, 
         {"0.652", "0.000000"}, 
         {"0.654", "0.000000"}, 
         {"0.656", "0.000000"}, 
         {"0.658", "0.000000"}, 
         {"0.660", "0.000000"}, 
         {"0.662", "0.000000"}, 
         {"0.664", "0.000000"}, 
         {"0.666", "0.000000"}, 
         {"0.668", "0.000000"}, 
         {"0.670", "0.000000"}, 
         {"0.672", "0.000000"}, 
         {"0.674", "0.000000"}, 
         {"0.676", "0.000000"}, 
         {"0.678", "0.000000"}, 
         {"0.680", "0.000000"}, 
         {"0.682", "0.000000"}, 
         {"0.684", "0.000000"}, 
         {"0.686", "0.000000"}, 
         {"0.688", "0.000000"}, 
         {"0.690", "0.000000"}, 
         {"0.692", "0.000000"}, 
         {"0.694", "0.000000"}, 
         {"0.696", "0.000000"}, 
         {"0.698", "0.000000"}, 
         {"0.700", "0.000000"}, 
         {"0.702", "0.000000"}, 
         {"0.704", "0.000000"}, 
         {"0.706", "0.000000"}, 
         {"0.708", "0.000000"}, 
         {"0.710", "0.000000"}, 
         {"0.712", "0.000000"}, 
         {"0.714", "0.000000"}, 
         {"0.716", "0.000000"}, 
         {"0.718", "0.000000"}, 
         {"0.720", "0.000000"}, 
         {"0.722", "0.000000"}, 
         {"0.724", "0.000000"}, 
         {"0.726", "0.000000"}, 
         {"0.728", "0.000000"}, 
         {"0.730", "0.000000"}, 
         {"0.732", "0.000000"}, 
         {"0.734", "0.000000"}, 
         {"0.736", "0.000000"}, 
         {"0.738", "0.000000"}, 
         {"0.740", "0.000000"}, 
         {"0.742", "0.000000"}, 
         {"0.744", "0.000000"}, 
         {"0.746", "0.000000"}, 
         {"0.748", "0.000000"}, 
         {"0.750", "0.000000"}, 
         {"0.752", "0.000000"}, 
         {"0.754", "0.000000"}, 
         {"0.756", "0.000000"}, 
         {"0.758", "0.000000"}, 
         {"0.760", "0.000000"}, 
         {"0.762", "0.000000"}, 
         {"0.764", "0.000000"}, 
         {"0.766", "0.000000"}, 
         {"0.768", "0.000000"}, 
         {"0.770", "0.000000"}, 
         {"0.772", "0.000000"}, 
         {"0.774", "0.000000"}, 
         {"0.776", "0.000000"}, 
         {"0.778", "0.000000"}, 
         {"0.780", "0.000000"}, 
         {"0.782", "0.000000"}, 
         {"0.784", "0.000000"}, 
         {"0.786", "0.000000"}, 
         {"0.788", "0.000000"}, 
         {"0.790", "0.000000"}, 
         {"0.792", "0.000000"}, 
         {"0.794", "0.000000"}, 
         {"0.796", "0.000000"}, 
         {"0.798", "0.000000"}, 
         {"0.800", "0.000000"}, 
         {"0.802", "0.000000"}, 
         {"0.804", "0.000000"}, 
         {"0.806", "0.000000"}, 
         {"0.808", "0.000000"}, 
         {"0.810", "0.000000"}, 
         {"0.812", "0.000000"}, 
         {"0.814", "0.000000"}, 
         {"0.816", "0.000000"}, 
         {"0.818", "0.000000"}, 
         {"0.820", "0.000000"}, 
         {"0.822", "0.000000"}, 
         {"0.824", "0.000000"}, 
         {"0.826", "0.000000"}, 
         {"0.828", "0.000000"}, 
         {"0.830", "0.000000"}, 
         {"0.832", "0.000000"}, 
         {"0.834", "0.000000"}, 
         {"0.836", "0.000000"}, 
         {"0.838", "0.000000"}, 
         {"0.840", "0.000000"}, 
         {"0.842", "0.000000"}, 
         {"0.844", "0.000000"}, 
         {"0.846", "0.000000"}, 
         {"0.848", "0.000000"}, 
         {"0.850", "0.000000"}, 
         {"0.852", "0.000000"}, 
         {"0.854", "0.000000"}, 
         {"0.856", "0.000000"}, 
         {"0.858", "0.000000"}, 
         {"0.860", "0.000000"}, 
         {"0.862", "0.000000"}, 
         {"0.864", "0.000000"}, 
         {"0.866", "0.000000"}, 
         {"0.868", "0.000000"}, 
         {"0.870", "0.000000"}, 
         {"0.872", "0.000000"}, 
         {"0.874", "0.000000"}, 
         {"0.876", "0.000000"}, 
         {"0.878", "0.000000"}, 
         {"0.880", "0.000000"}, 
         {"0.882", "0.000000"}, 
         {"0.884", "0.000000"}, 
         {"0.886", "0.000000"}, 
         {"0.888", "0.000000"}, 
         {"0.890", "0.000000"}, 
         {"0.892", "0.000000"}, 
         {"0.894", "0.000000"}, 
         {"0.896", "0.000000"}, 
         {"0.898", "0.000000"}, 
         {"0.900", "0.000000"}, 
         {"0.902", "0.000000"}, 
         {"0.904", "0.000000"}, 
         {"0.906", "0.000000"}, 
         {"0.908", "0.000000"}, 
         {"0.910", "0.000000"}, 
         {"0.912", "0.000000"}, 
         {"0.914", "0.000000"}, 
         {"0.916", "0.000000"}, 
         {"0.918", "0.000000"}, 
         {"0.920", "0.000000"}, 
         {"0.922", "0.000000"}, 
         {"0.924", "0.000000"}, 
         {"0.926", "0.000000"}, 
         {"0.928", "0.000000"}, 
         {"0.930", "0.000000"}, 
         {"0.932", "0.000000"}, 
         {"0.934", "0.000000"}, 
         {"0.936", "0.000000"}, 
         {"0.938", "0.000000"}, 
         {"0.940", "0.000000"}, 
         {"0.942", "0.000000"}, 
         {"0.944", "0.000000"}, 
         {"0.946", "0.000000"}, 
         {"0.948", "0.000000"}, 
         {"0.950", "0.000000"}, 
         {"0.952", "0.000000"}, 
         {"0.954", "0.000000"}, 
         {"0.956", "0.000000"}, 
         {"0.958", "0.000000"}, 
         {"0.960", "0.000000"}, 
         {"0.962", "0.000000"}, 
         {"0.964", "0.000000"}, 
         {"0.966", "0.000000"}, 
         {"0.968", "0.000000"}, 
         {"0.970", "0.000000"}, 
         {"0.972", "0.000000"}, 
         {"0.974", "0.000000"}, 
         {"0.976", "0.000000"}, 
         {"0.978", "0.000000"}, 
         {"0.980", "0.000000"}, 
         {"0.982", "0.000000"}, 
         {"0.984", "0.000000"}, 
         {"0.986", "0.000000"}, 
         {"0.988", "0.000000"}, 
         {"0.990", "0.000000"}, 
         {"0.992", "0.000000"}, 
         {"0.994", "0.000000"}, 
         {"0.996", "0.000000"}, 
         {"0.998", "0.000000"}, 
         {"1.000", "0.000000"}, 
         {"1.002", "0.000000"}, 
         {"1.004", "0.000000"}, 
         {"1.006", "0.000000"}, 
         {"1.008", "0.000000"}, 
         {"1.010", "0.000000"}, 
         {"1.012", "0.000000"}, 
         {"1.014", "0.000000"}, 
         {"1.016", "0.000000"}, 
         {"1.018", "0.000000"}, 
         {"1.020", "0.000000"}, 
         {"1.022", "0.000000"}, 
         {"1.024", "0.000000"}, 
         {"1.026", "0.000000"}, 
         {"1.028", "0.000000"}, 
         {"1.030", "0.000000"}, 
         {"1.032", "0.000000"}, 
         {"1.034", "0.000000"}, 
         {"1.036", "0.000000"}, 
         {"1.038", "0.000000"}, 
         {"1.040", "0.000000"}, 
         {"1.042", "0.000000"}, 
         {"1.044", "0.000000"}, 
         {"1.046", "0.000000"}, 
         {"1.048", "0.000000"}, 
         {"1.050", "0.000000"}, 
         {"1.052", "0.000000"}, 
         {"1.054", "0.000000"}, 
         {"1.056", "0.000000"}, 
         {"1.058", "0.000000"}, 
         {"1.060", "0.000000"}, 
         {"1.062", "0.000000"}, 
         {"1.064", "0.000000"}, 
         {"1.066", "0.000000"}, 
         {"1.068", "0.000000"}, 
         {"1.070", "0.000000"}, 
         {"1.072", "0.000000"}, 
         {"1.074", "0.000000"}, 
         {"1.076", "0.000000"}, 
         {"1.078", "0.000000"}, 
         {"1.080", "0.000000"}, 
         {"1.082", "0.000000"}, 
         {"1.084", "0.000000"}, 
         {"1.086", "0.000000"}, 
         {"1.088", "0.000000"}, 
         {"1.090", "0.000000"}, 
         {"1.092", "0.000000"}, 
         {"1.094", "0.000000"}, 
         {"1.096", "0.000000"}, 
         {"1.098", "0.000000"}, 
         {"1.100", "0.000000"}, 
         {"1.102", "0.000000"}, 
         {"1.104", "0.000000"}, 
         {"1.106", "0.000000"}, 
         {"1.108", "0.000000"}, 
         {"1.110", "0.000000"}, 
         {"1.112", "0.000000"}, 
         {"1.114", "0.000000"}, 
         {"1.116", "0.000000"}, 
         {"1.118", "0.000000"}, 
         {"1.120", "0.000000"}, 
         {"1.122", "0.000000"}, 
         {"1.124", "0.000000"}, 
         {"1.126", "0.000000"}, 
         {"1.128", "0.000000"}, 
         {"1.130", "0.000000"}, 
         {"1.132", "0.000000"}, 
         {"1.134", "0.000000"}, 
         {"1.136", "0.000000"}, 
         {"1.138", "0.000000"}, 
         {"1.140", "0.000000"}, 
         {"1.142", "0.000000"}, 
         {"1.144", "0.000000"}, 
         {"1.146", "0.000000"}, 
         {"1.148", "0.000000"}, 
         {"1.150", "0.000000"}, 
         {"1.152", "0.000000"}, 
         {"1.154", "0.000000"}, 
         {"1.156", "0.000000"}, 
         {"1.158", "0.000000"}, 
         {"1.160", "0.000000"}, 
         {"1.162", "0.000000"}, 
         {"1.164", "0.000000"}, 
         {"1.166", "0.000000"}, 
         {"1.168", "0.000000"}, 
         {"1.170", "0.000000"}, 
         {"1.172", "0.000000"}, 
         {"1.174", "0.000000"}, 
         {"1.176", "0.000000"}, 
         {"1.178", "0.000000"}, 
         {"1.180", "0.000000"}, 
         {"1.182", "0.000000"}, 
         {"1.184", "0.000000"}, 
         {"1.186", "0.000000"}, 
         {"1.188", "0.000000"}, 
         {"1.190", "0.000000"}, 
         {"1.192", "0.000000"}, 
         {"1.194", "0.000000"}, 
         {"1.196", "0.000000"}, 
         {"1.198", "0.000000"}, 
         {"1.200", "0.000000"}, 
         {"1.202", "0.000000"}, 
         {"1.204", "0.000000"}, 
         {"1.206", "0.000000"}, 
         {"1.208", "0.000000"}, 
         {"1.210", "0.000000"}, 
         {"1.212", "0.000000"}, 
         {"1.214", "0.000000"}, 
         {"1.216", "0.000000"}, 
         {"1.218", "0.000000"}, 
         {"1.220", "0.000000"}, 
         {"1.222", "0.000000"}, 
         {"1.224", "0.000000"}, 
         {"1.226", "0.000000"}, 
         {"1.228", "0.000000"}, 
         {"1.230", "0.000000"}, 
         {"1.232", "0.000000"}, 
         {"1.234", "0.000000"}, 
         {"1.236", "0.000000"}, 
         {"1.238", "0.000000"}, 
         {"1.240", "0.000000"}, 
         {"1.242", "0.000000"}, 
         {"1.244", "0.000000"}, 
         {"1.246", "0.000000"}, 
         {"1.248", "0.000000"}, 
         {"1.250", "0.000000"}});

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int2")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int2")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").addInput("frequency");
    model.component("comp1").material("mat3").selection().named("geom1_arr2_dom");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("epsr_air", "(mat1.rfi.n11-i*mat1.rfi.ki11)^2");
    model.component("comp1").variable("var1").descr("epsr_air", "Relative permittivity, air");
    model.component("comp1").variable("var1")
         .set("epsr_m", "(mat2.rfi.nr(ewfd.lambda0)-i*mat2.rfi.ni(ewfd.lambda0))^2");
    model.component("comp1").variable("var1").descr("epsr_m", "Relative permittivity, metal");
    model.component("comp1").variable("var1")
         .set("epsr_d", "(mat3.rfi.nr(ewfd.lambda0)-i*mat3.rfi.ni(ewfd.lambda0))^2");
    model.component("comp1").variable("var1").descr("epsr_d", "Relative permittivity, dielectric");
    model.component("comp1").variable("var1").set("epsr_ave", "(epsr_air+epsr_d)/2");
    model.component("comp1").variable("var1").descr("epsr_ave", "Average relative permittivity");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1")
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61);

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(63);
    model.component("comp1").coordSystem().create("pml2", "PML");
    model.component("comp1").coordSystem("pml2").selection()
         .set(1, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124);
    model.component("comp1").coordSystem("pml2").set("ScalingType", "Cylindrical");
    model.component("comp1").coordSystem("pml2").set("wavelengthSourceType", "userDefined");
    model.component("comp1").coordSystem("pml2").set("typicalWavelength", "lda0");

    model.component("comp1").physics("ewfd").prop("components").set("components", "inplane");
    model.component("comp1").physics("ewfd").create("epd1", "ElectricPointDipole", 0);
    model.component("comp1").physics("ewfd").feature("epd1").selection().set(63);
    model.component("comp1").physics("ewfd").feature("epd1").set("pIz", 1);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.06);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 0.01);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("wave").set("plist", "lda0");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result("pg1").run();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62);
    model.result().dataset("rev1").set("startangle", 0);
    model.result().dataset("rev1").set("revangle", 180);
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickplane", "xz");
    model.result("pg1").run();
    model.result("pg1").set("data", "cpl1");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("filt1", "Filter");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("filt1").set("expr", "z<=0");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colortable", "GrayBody");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -1);
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "ewfd.normEi");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature().remove("filt1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "ewfd.Er");
    model.result("pg3").feature("surf1").set("colortable", "ThermalWaveDark");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("rangecolormin", -1.1E16);
    model.result("pg3").feature("surf1").set("rangecolormax", 1.1E16);
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("expr", "ewfd.Ez");
    model.result("pg3").feature("surf2").set("colorlegend", false);
    model.result("pg3").feature("surf2").create("trn1", "Transformation");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("trn1").set("move", new String[]{"W-1.5*tPML", "0"});
    model.result("pg3").run();
    model.result("pg3").set("legendpos", "bottom");
    model.result("pg3").run();

    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").set("f", "tm/(tm+td)");
    model.component("comp1").variable("var2").descr("f", "Filling ratio, metal");
    model.component("comp1").variable("var2").set("epsr_rr_EMT", "f*epsr_m+(1-f)*epsr_d");
    model.component("comp1").variable("var2").descr("epsr_rr_EMT", "Effective relative permittivity, rr-component");
    model.component("comp1").variable("var2").set("epsr_zz_EMT", "((f/epsr_m)+((1-f)/epsr_d))^-1");
    model.component("comp1").variable("var2").descr("epsr_zz_EMT", "Effective relative permittivity, zz-component");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").selection().geom("geom1", 2);
    model.component("comp1").variable("var3").selection().named("geom1_arr1_dom");
    model.component("comp1").variable("var3").set("t", "tm");
    model.component("comp1").variable("var3").descr("t", "Metal thickness variable");
    model.component("comp1").variable().duplicate("var4", "var3");
    model.component("comp1").variable("var4").selection().named("geom1_arr2_dom");
    model.component("comp1").variable("var4").set("t", "td");
    model.component("comp1").variable("var4").descr("t", "Dielectric thickness variable");

    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel1"});
    model.component("comp1").selection("adj1").set("exterior", false);
    model.component("comp1").selection("adj1").set("interior", true);

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().named("adj1");

    model.component("comp1").variable().create("var5");
    model.component("comp1").variable("var5").set("meanDr", "(up(ewfd.Dr)*up(t)+down(ewfd.Dr)*down(t))/(tm+td)");
    model.component("comp1").variable("var5").descr("meanDr", "Average electric displacement field, r-component");
    model.component("comp1").variable("var5").set("meanEr", "(up(ewfd.Er)*up(t)+down(ewfd.Er)*down(t))/(tm+td)");
    model.component("comp1").variable("var5").descr("meanEr", "Average electric field, r-component");
    model.component("comp1").variable("var5").set("meanDz", "(up(ewfd.Dz)*up(t)+down(ewfd.Dz)*down(t))/(tm+td)");
    model.component("comp1").variable("var5").descr("meanDz", "Average electric displacement field, z-component");
    model.component("comp1").variable("var5").set("meanEz", "(up(ewfd.Ez)*up(t)+down(ewfd.Ez)*down(t))/(tm+td)");
    model.component("comp1").variable("var5").descr("meanEz", "Average electric field, z-component");
    model.component("comp1").variable("var5").set("epsr_rr_cal", "aveop1(meanDr/(epsilon0_const*meanEr))");
    model.component("comp1").variable("var5")
         .descr("epsr_rr_cal", "Effective relative permittivity, rr-component (calculated)");
    model.component("comp1").variable("var5").set("epsr_zz_cal", "aveop1(meanDz/(epsilon0_const*meanEz))");
    model.component("comp1").variable("var5")
         .descr("epsr_zz_cal", "Effective relative permittivity, zz-component (calculated)");

    model.study().create("std2");
    model.study("std2").create("wave", "Wavelength");
    model.study("std2").feature("wave").set("plotgroup", "Default");
    model.study("std2").feature("wave").set("ftplistmethod", "manual");
    model.study("std2").feature("wave").set("solnum", "auto");
    model.study("std2").feature("wave").set("notsolnum", "auto");
    model.study("std2").feature("wave").set("outputmap", new String[]{});
    model.study("std2").feature("wave").set("ngenAUX", "1");
    model.study("std2").feature("wave").set("goalngenAUX", "1");
    model.study("std2").feature("wave").set("ngenAUX", "1");
    model.study("std2").feature("wave").set("goalngenAUX", "1");
    model.study("std2").feature("wave").setSolveFor("/physics/ewfd", true);
    model.study("std2").feature("wave").set("plist", "range(450[nm],25[nm],900[nm])");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "real(epsr_rr_EMT)", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "real(epsr_zz_EMT)", 1);
    model.result("pg4").feature("glob1").setIndex("expr", "imag(epsr_rr_EMT)", 2);
    model.result("pg4").feature("glob1").setIndex("expr", "imag(epsr_zz_EMT)", 3);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "ewfd.lambda0");
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "Re(epsr_rr_EMT)", 0);
    model.result("pg4").feature("glob1").setIndex("legends", "Re(epsr_zz_EMT)", 1);
    model.result("pg4").feature("glob1").setIndex("legends", "Im(epsr_rr_EMT)", 2);
    model.result("pg4").feature("glob1").setIndex("legends", "Im(epsr_zz_EMT)", 3);
    model.result("pg4").feature().duplicate("glob2", "glob1");
    model.result("pg4").run();
    model.result("pg4").feature("glob2").setIndex("expr", "real(epsr_rr_cal)", 0);
    model.result("pg4").feature("glob2").setIndex("expr", "real(epsr_zz_cal)", 1);
    model.result("pg4").feature("glob2").setIndex("expr", "imag(epsr_rr_cal)", 2);
    model.result("pg4").feature("glob2").setIndex("expr", "imag(epsr_zz_cal)", 3);
    model.result("pg4").feature("glob2").set("linestyle", "none");
    model.result("pg4").feature("glob2").set("linemarker", "circle");
    model.result("pg4").feature("glob2").setIndex("legends", "Re(epsr_rr_cal)", 0);
    model.result("pg4").feature("glob2").setIndex("legends", "Re(epsr_zz_cal)", 1);
    model.result("pg4").feature("glob2").setIndex("legends", "Im(epsr_rr_cal)", 2);
    model.result("pg4").feature("glob2").setIndex("legends", "Im(epsr_zz_cal)", 3);
    model.result("pg4").run();
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("legendlayout", "outside");
    model.result("pg4").set("legendposoutside", "top");
    model.result("pg4").run();

    model
         .title("\u91d1\u5c5e-\u4ecb\u7535\u591a\u5c42\u8d85\u6750\u6599\u4e2d\u8272\u6563\u4e0e\u53cc\u66f2\u6ce2\u7684\u4eff\u771f");

    model
         .description("\u672c\u6a21\u578b\u8ba8\u8bba\u5982\u4f55\u4f7f\u7528\u4f4d\u4e8e\u7531\u5468\u671f\u6027\u6392\u5217\u7684\u91d1\u5c5e-\u4ecb\u7535\u5c42\u6784\u6210\u7684\u53cc\u66f2\u8d85\u6750\u6599\u7ed3\u6784\u4e0a\u65b9\u7a7a\u6c14\u4e2d\u7684\u7535\u70b9\u5076\u6781\u5b50\uff0c\u6a21\u62df\u5728\u8be5\u8d85\u6750\u6599\u4e2d\u4f20\u64ad\u7684\u53cc\u66f2\u6ce2\u3002\u6b64\u5916\uff0c\u8fd8\u6f14\u793a\u4e86\u8ba1\u7b97\u8d85\u6750\u6599\u6709\u6548\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\u5f20\u91cf\u5206\u91cf\u7684\u8fc7\u7a0b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("dipole_near_hyperbolic_metamaterial.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
