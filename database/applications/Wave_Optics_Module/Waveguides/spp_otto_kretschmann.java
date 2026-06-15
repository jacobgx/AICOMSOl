/*
 * spp_otto_kretschmann.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:44 by COMSOL 6.3.0.290. */
public class spp_otto_kretschmann {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Waveguides");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");
    model.component("comp1").physics().create("ewfd2", "ElectromagneticWavesFrequencyDomain", "geom1");

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
    model.study("std1").feature("wave").setSolveFor("/physics/ewfd2", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

    model.param().set("L", "1[um]");
    model.param().descr("L", "Simulation domain length");
    model.param().set("H", "3[um]");
    model.param().descr("H", "Simulation domain height");
    model.param().set("lda0", "500[nm]");
    model.param().descr("lda0", "Wavelength");
    model.param().set("angle", "0[deg]");
    model.param().descr("angle", "Incident angle");
    model.param().set("n_prism", "1.4");
    model.param().descr("n_prism", "Refractive index, prism");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "H"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "H/2", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "lda0", 1);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"1.25*L", "0"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "lda0/10", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "\u6298\u5c04\u7387");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func()
         .create("int2", "Interpolation");
    model.component("comp1").material("mat1").label("Ag (Silver) (Johnson and Christy 1972: n,k 0.188-1.94 um)");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int1").set("funcname", "nr");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int1")
         .set("table", new String[][]{{"0.1879", "1.07"}, 
         {"0.1916", "1.10"}, 
         {"0.1953", "1.12"}, 
         {"0.1993", "1.14"}, 
         {"0.2033", "1.15"}, 
         {"0.2073", "1.18"}, 
         {"0.2119", "1.20"}, 
         {"0.2164", "1.22"}, 
         {"0.2214", "1.25"}, 
         {"0.2262", "1.26"}, 
         {"0.2313", "1.28"}, 
         {"0.2371", "1.28"}, 
         {"0.2426", "1.30"}, 
         {"0.2490", "1.31"}, 
         {"0.2551", "1.33"}, 
         {"0.2616", "1.35"}, 
         {"0.2689", "1.38"}, 
         {"0.2761", "1.41"}, 
         {"0.2844", "1.41"}, 
         {"0.2924", "1.39"}, 
         {"0.3009", "1.34"}, 
         {"0.3107", "1.13"}, 
         {"0.3204", "0.81"}, 
         {"0.3315", "0.17"}, 
         {"0.3425", "0.14"}, 
         {"0.3542", "0.10"}, 
         {"0.3679", "0.07"}, 
         {"0.3815", "0.05"}, 
         {"0.3974", "0.05"}, 
         {"0.4133", "0.05"}, 
         {"0.4305", "0.04"}, 
         {"0.4509", "0.04"}, 
         {"0.4714", "0.05"}, 
         {"0.4959", "0.05"}, 
         {"0.5209", "0.05"}, 
         {"0.5486", "0.06"}, 
         {"0.5821", "0.05"}, 
         {"0.6168", "0.06"}, 
         {"0.6595", "0.05"}, 
         {"0.7045", "0.04"}, 
         {"0.7560", "0.03"}, 
         {"0.8211", "0.04"}, 
         {"0.8920", "0.04"}, 
         {"0.9840", "0.04"}, 
         {"1.0880", "0.04"}, 
         {"1.2160", "0.09"}, 
         {"1.3930", "0.13"}, 
         {"1.6100", "0.15"}, 
         {"1.9370", "0.24"}});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int1")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int2").set("funcname", "ni");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int2")
         .set("table", new String[][]{{"0.1879", "1.212"}, 
         {"0.1916", "1.232"}, 
         {"0.1953", "1.255"}, 
         {"0.1993", "1.277"}, 
         {"0.2033", "1.296"}, 
         {"0.2073", "1.312"}, 
         {"0.2119", "1.325"}, 
         {"0.2164", "1.336"}, 
         {"0.2214", "1.342"}, 
         {"0.2262", "1.344"}, 
         {"0.2313", "1.357"}, 
         {"0.2371", "1.367"}, 
         {"0.2426", "1.378"}, 
         {"0.2490", "1.389"}, 
         {"0.2551", "1.393"}, 
         {"0.2616", "1.387"}, 
         {"0.2689", "1.372"}, 
         {"0.2761", "1.331"}, 
         {"0.2844", "1.264"}, 
         {"0.2924", "1.161"}, 
         {"0.3009", "0.964"}, 
         {"0.3107", "0.616"}, 
         {"0.3204", "0.392"}, 
         {"0.3315", "0.829"}, 
         {"0.3425", "1.142"}, 
         {"0.3542", "1.419"}, 
         {"0.3679", "1.657"}, 
         {"0.3815", "1.864"}, 
         {"0.3974", "2.070"}, 
         {"0.4133", "2.275"}, 
         {"0.4305", "2.462"}, 
         {"0.4509", "2.657"}, 
         {"0.4714", "2.869"}, 
         {"0.4959", "3.093"}, 
         {"0.5209", "3.324"}, 
         {"0.5486", "3.586"}, 
         {"0.5821", "3.858"}, 
         {"0.6168", "4.152"}, 
         {"0.6595", "4.483"}, 
         {"0.7045", "4.838"}, 
         {"0.7560", "5.242"}, 
         {"0.8211", "5.727"}, 
         {"0.8920", "6.312"}, 
         {"0.9840", "6.992"}, 
         {"1.0880", "7.795"}, 
         {"1.2160", "8.828"}, 
         {"1.3930", "10.10"}, 
         {"1.6100", "11.85"}, 
         {"1.9370", "14.08"}});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int2")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("int2")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").addInput("frequency");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat2").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat2").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").label("Air");
    model.component("comp1").material("mat2").set("family", "air");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat2").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat2").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat2").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat2").materialType("nonSolid");
    model.component("comp1").material("mat2").selection().set(2, 4);
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().set(3, 6);
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").set("n", new String[]{"n_prism"});

    model.component("comp1").physics("ewfd").selection().set(1, 2, 3);
    model.component("comp1").physics("ewfd").create("port1", "Port", 1);
    model.component("comp1").physics("ewfd").feature("port1").selection().set(7);
    model.component("comp1").physics("ewfd").feature("port1").set("PortType", "Periodic");
    model.component("comp1").physics("ewfd").feature("port1").set("InputType", "H");
    model.component("comp1").physics("ewfd").feature("port1").set("Hampl", new int[]{0, 0, 1});
    model.component("comp1").physics("ewfd").feature("port1").set("alpha_inc", "angle");
    model.component("comp1").physics("ewfd").feature().duplicate("port2", "port1");
    model.component("comp1").physics("ewfd").feature("port2").selection().set(2);
    model.component("comp1").physics("ewfd").feature("port2").set("PortExcitation", "off");
    model.component("comp1").physics("ewfd").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("ewfd").feature("pc1").selection().set(1, 3, 5, 8, 9, 10);
    model.component("comp1").physics("ewfd").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("ewfd").feature("pc1").set("Floquet_source", "FromPeriodicPort");
    model.component("comp1").physics("ewfd2").selection().set(4, 5, 6);
    model.component("comp1").physics("ewfd2").create("port1", "Port", 1);
    model.component("comp1").physics("ewfd2").feature("port1").selection().set(17);
    model.component("comp1").physics("ewfd2").feature("port1").set("PortType", "Periodic");
    model.component("comp1").physics("ewfd2").feature("port1").set("InputType", "H");
    model.component("comp1").physics("ewfd2").feature("port1").set("Hampl", new int[]{0, 0, 1});
    model.component("comp1").physics("ewfd2").feature("port1").set("alpha_inc", "angle");
    model.component("comp1").physics("ewfd2").feature().duplicate("port2", "port1");
    model.component("comp1").physics("ewfd2").feature("port2").selection().set(12);
    model.component("comp1").physics("ewfd2").feature("port2").set("PortExcitation", "off");
    model.component("comp1").physics("ewfd2").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("ewfd2").feature("pc1").selection().set(11, 13, 15, 18, 19, 20);
    model.component("comp1").physics("ewfd2").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("ewfd2").feature("pc1").set("Floquet_source", "FromPeriodicPort");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").physics("ewfd").prop("MeshControl").set("ResolveWaveInLossyMedia", true);
    model.component("comp1").physics("ewfd2").prop("MeshControl").set("ResolveWaveInLossyMedia", true);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("wave").set("plist", "lda0");
    model.study("std1").feature("wave").setSolveFor("/physics/ewfd2", false);
    model.study("std1").feature("wave").set("useparam", true);
    model.study("std1").feature("wave").setIndex("pname_aux", "L", 0);
    model.study("std1").feature("wave").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("wave").setIndex("punit_aux", "m", 0);
    model.study("std1").feature("wave").setIndex("pname_aux", "L", 0);
    model.study("std1").feature("wave").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("wave").setIndex("punit_aux", "m", 0);
    model.study("std1").feature("wave").setIndex("pname_aux", "angle", 0);
    model.study("std1").feature("wave").setIndex("plistarr_aux", "range(45,0.05,55)", 0);
    model.study("std1").feature("wave").setIndex("punit_aux", "deg", 0);
    model.study().create("std2");
    model.study("std2").create("wave", "Wavelength");
    model.study("std2").feature("wave").set("ftplistmethod", "manual");
    model.study("std2").feature("wave").set("solnum", "auto");
    model.study("std2").feature("wave").set("notsolnum", "auto");
    model.study("std2").feature("wave").set("outputmap", new String[]{});
    model.study("std2").feature("wave").set("ngenAUX", "1");
    model.study("std2").feature("wave").set("goalngenAUX", "1");
    model.study("std2").feature("wave").set("ngenAUX", "1");
    model.study("std2").feature("wave").set("goalngenAUX", "1");
    model.study("std2").feature("wave").setSolveFor("/physics/ewfd", true);
    model.study("std2").feature("wave").setSolveFor("/physics/ewfd2", true);
    model.study("std2").feature("wave").set("plist", "lda0");
    model.study("std2").feature("wave").setSolveFor("/physics/ewfd", false);
    model.study("std2").feature("wave").set("useparam", true);
    model.study("std2").feature("wave").setIndex("pname_aux", "L", 0);
    model.study("std2").feature("wave").setIndex("plistarr_aux", "", 0);
    model.study("std2").feature("wave").setIndex("punit_aux", "m", 0);
    model.study("std2").feature("wave").setIndex("pname_aux", "L", 0);
    model.study("std2").feature("wave").setIndex("plistarr_aux", "", 0);
    model.study("std2").feature("wave").setIndex("punit_aux", "m", 0);
    model.study("std2").feature("wave").setIndex("pname_aux", "angle", 0);
    model.study("std2").feature("wave").setIndex("plistarr_aux", "range(45,0.05,55)", 0);
    model.study("std2").feature("wave").setIndex("punit_aux", "deg", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"ewfd.Rorder_0", "ewfd.Torder_0", "ewfd.RTtotal", "ewfd.Atotal"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u9636\u6570 0", "\u900f\u5c04\u7387\uff0c\u9636\u6570 0", "\u603b\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387", "\u5438\u6536\u7387"});
    model.result("pg2").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (1)");
    model.result("pg2").feature("glob1").set("xdataexpr", "angle");
    model.result("pg2").feature("glob1").set("xdataunit", "deg");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u6781\u5316\u56fe (ewfd)");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u6781\u5316\u72b6\u6001\uff0cColor\uff1aPhase\uff08Radians\uff09");
    model.result("pg3").setIndex("looplevelinput", "manual", 0);
    model.result("pg3").setIndex("looplevelinput", "manual", 1);
    model.result("pg3").setIndex("looplevel", "1", 0);
    model.result("pg3").setIndex("looplevel", "1", 1);
    model.result("pg3").create("plz1", "Polarization");
    model.result("pg3").feature("plz1").set("linestyle", "solid");
    model.result("pg3").feature("plz1").set("linewidth", 2);
    model.result("pg3").feature("plz1").set("display", "0");
    model.result("pg3").feature("plz1").create("col1", "Color");
    model.result("pg3").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg3").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg3").feature("plz1").set("legend", true);
    model.result("pg3").feature("plz1").set("legendmethod", "manual");
    model.result("pg3").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg3").create("plz2", "Polarization");
    model.result("pg3").feature("plz2").set("linestyle", "dashed");
    model.result("pg3").feature("plz2").set("linewidth", 2);
    model.result("pg3").feature("plz2").set("display", "1");
    model.result("pg3").feature("plz2").create("col1", "Color");
    model.result("pg3").feature("plz2").feature("col1").set("colortable", "Cyclic");
    model.result("pg3").feature("plz2").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("plz2").set("legend", true);
    model.result("pg3").feature("plz2").set("legendmethod", "manual");
    model.result("pg3").feature("plz2").setIndex("legends", "\u4f20\u8f93", 0);
    model.result("pg1").run();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(1, 2, 3);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 81, 0);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "ewfd.Ey");
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").run();
    model.result("pg1").create("arwl1", "ArrowLine");
    model.result("pg1").feature("arwl1").set("expr", new String[]{"ewfd.kIncx_1", "ewfd.Hy"});
    model.result("pg1").feature("arwl1").setIndex("expr", "ewfd.kIncy_1", 1);
    model.result("pg1").feature("arwl1").set("arrowcount", 20);
    model.result("pg1").feature("arwl1").set("scaleactive", true);
    model.result("pg1").feature("arwl1").set("scale", "8E-9");
    model.result("pg1").feature("arwl1").create("sel1", "Selection");
    model.result("pg1").feature("arwl1").feature("sel1").selection().set(7);
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"ewfd.Ex", "ewfd.Ey"});
    model.result("pg1").feature("arws1").set("xnumber", 40);
    model.result("pg1").feature("arws1").set("ynumber", 40);
    model.result("pg1").feature("arws1").set("scaleactive", true);
    model.result("pg1").feature("arws1").set("scale", "3E-7");
    model.result("pg1").run();
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "middleright");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").setIndex("descr", "SPP coupling", 3);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", new int[]{81}, 0);
    model.result("pg3").run();

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u7535\u573a (ewfd2)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 201, 0);
    model.result("pg4").setIndex("looplevel", 1, 1);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "ewfd2.normE");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg5").feature("glob1")
         .set("expr", new String[]{"ewfd2.Rorder_0", "ewfd2.Torder_0", "ewfd2.RTtotal", "ewfd2.Atotal"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u9636\u6570 0", "\u900f\u5c04\u7387\uff0c\u9636\u6570 0", "\u603b\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387", "\u5438\u6536\u7387"});
    model.result("pg5").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd2)");
    model.result("pg5").feature("glob1").set("titletype", "none");
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (1)");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg5").feature("glob1").set("xdataexpr", "angle");
    model.result("pg5").feature("glob1").set("xdataunit", "deg");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").label("\u6781\u5316\u56fe (ewfd2)");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u6781\u5316\u72b6\u6001\uff0cColor\uff1aPhase\uff08Radians\uff09");
    model.result("pg6").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").setIndex("looplevelinput", "manual", 1);
    model.result("pg6").setIndex("looplevel", "1", 0);
    model.result("pg6").setIndex("looplevel", "1", 1);
    model.result("pg6").create("plz1", "Polarization");
    model.result("pg6").feature("plz1").set("linestyle", "solid");
    model.result("pg6").feature("plz1").set("linewidth", 2);
    model.result("pg6").feature("plz1").set("display", "2");
    model.result("pg6").feature("plz1").create("col1", "Color");
    model.result("pg6").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg6").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg6").feature("plz1").set("legend", true);
    model.result("pg6").feature("plz1").set("legendmethod", "manual");
    model.result("pg6").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg6").create("plz2", "Polarization");
    model.result("pg6").feature("plz2").set("linestyle", "dashed");
    model.result("pg6").feature("plz2").set("linewidth", 2);
    model.result("pg6").feature("plz2").set("display", "3");
    model.result("pg6").feature("plz2").create("col1", "Color");
    model.result("pg6").feature("plz2").feature("col1").set("colortable", "Cyclic");
    model.result("pg6").feature("plz2").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("plz2").set("legend", true);
    model.result("pg6").feature("plz2").set("legendmethod", "manual");
    model.result("pg6").feature("plz2").setIndex("legends", "\u4f20\u8f93", 0);
    model.result("pg4").run();
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().set(4, 5, 6);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 81, 0);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "ewfd2.Ey");
    model.result("pg4").feature("surf1").set("colortable", "Wave");
    model.result("pg4").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").run();
    model.result("pg4").create("arwl1", "ArrowLine");
    model.result("pg4").feature("arwl1").set("expr", new String[]{"ewfd2.kIncx_1", "ewfd.Hy"});
    model.result("pg4").feature("arwl1").setIndex("expr", "ewfd2.kIncy_1", 1);
    model.result("pg4").feature("arwl1").set("arrowcount", 20);
    model.result("pg4").feature("arwl1").set("scaleactive", true);
    model.result("pg4").feature("arwl1").set("scale", "8E-9");
    model.result("pg4").feature("arwl1").create("sel1", "Selection");
    model.result("pg4").feature("arwl1").feature("sel1").selection().set(17);
    model.result("pg4").run();
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"ewfd2.Ex", "ewfd2.Ey"});
    model.result("pg4").feature("arws1").set("xnumber", 40);
    model.result("pg4").feature("arws1").set("ynumber", 40);
    model.result("pg4").feature("arws1").set("scaleactive", true);
    model.result("pg4").feature("arws1").set("scale", "3E-7");
    model.result("pg4").run();
    model.result("pg4").feature("arws1").set("color", "black");
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "middleright");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("descr", "SPP coupling", 3);
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", new int[]{81}, 0);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevel", 81, 0);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "ewfd.normEi");
    model.result("pg7").feature("surf1").set("colortable", "GrayBody");
    model.result("pg7").run();
    model.result("pg7").create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").set("expr", new String[]{"ewfd.Ex", "ewfd.Ey"});
    model.result("pg7").feature("arws1").set("xnumber", 30);
    model.result("pg7").feature("arws1").set("ynumber", 30);
    model.result("pg7").feature("arws1").set("scaleactive", true);
    model.result("pg7").feature("arws1").set("scale", "5E-7");
    model.result("pg7").feature("arws1").set("color", "white");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf2", "surf1");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").set("data", "dset2");
    model.result("pg7").feature("surf2").setIndex("looplevel", 81, 0);
    model.result("pg7").feature("surf2").set("expr", "ewfd2.normEi");
    model.result("pg7").feature("surf2").set("inheritplot", "surf1");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("arws2", "arws1");
    model.result("pg7").run();
    model.result("pg7").feature("arws2").set("data", "dset2");
    model.result("pg7").feature("arws2").setIndex("looplevel", 81, 0);
    model.result("pg7").feature("arws2").set("expr", new String[]{"ewfd2.Ex", "ewfd2.Ey"});
    model.result("pg7").run();

    model
         .title("\u901a\u8fc7 Otto \u548c Kretschmann \u914d\u7f6e\u6fc0\u53d1\u8868\u9762\u7b49\u79bb\u6781\u5316\u6fc0\u5143");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528 Otto \u548c Kretschmann \u914d\u7f6e\u6765\u6fc0\u53d1\u8868\u9762\u7b49\u79bb\u6781\u5316\u6fc0\u5143\u7684\u4eff\u771f\u8bbe\u7f6e\u3002\u5176\u57fa\u672c\u539f\u7406\u7531\u5168\u5185\u53cd\u5c04\u4e0e\u500f\u901d\u6ce2\u8026\u5408\u73b0\u8c61\u4e4b\u95f4\u7684\u76f8\u4e92\u4f5c\u7528\u9a71\u52a8\u3002\u540c\u65f6\u8fd8\u8ba1\u7b97\u4e86\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387\uff0c\u4ee5\u5206\u6790\u8868\u9762\u7b49\u79bb\u6fc0\u5143\u7684\u5171\u632f\u6761\u4ef6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("spp_otto_kretschmann.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
