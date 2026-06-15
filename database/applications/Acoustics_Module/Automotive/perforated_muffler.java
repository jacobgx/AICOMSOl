/*
 * perforated_muffler.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:25 by COMSOL 6.3.0.290. */
public class perforated_muffler {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Automotive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);

    model.component("comp1").geom("geom1").insertFile("perforated_muffler_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("boxsel1");

    model.param().set("p0", "1[Pa]");
    model.param().descr("p0", "\u5165\u53e3\u538b\u529b");
    model.param().set("t_w", "1.5[mm]");
    model.param().descr("t_w", "\u58c1\u539a");
    model.param().set("d_h", "5[mm]");
    model.param().descr("d_h", "\u5b54\u5f84");
    model.param().set("sigma_p", "0.22");
    model.param().descr("sigma_p", "\u7a7f\u5b54\u7ba1\u5b54\u9699\u7387");
    model.param().set("sigma_bi", "0.46");
    model.param().descr("sigma_bi", "\u5165\u53e3\u4fa7\u6321\u677f\u7684\u5b54\u9699\u7387");
    model.param().set("sigma_bo", "0.3");
    model.param().descr("sigma_bo", "\u51fa\u53e3\u4fa7\u6321\u677f\u7684\u5b54\u9699\u7387");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "perforated_muffler_exp_data.txt");
    model.component("comp1").func("int1").importData();
    model.component("comp1").func("int1").set("funcname", "TL_exp");

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

    model.component("comp1").physics("acpr").create("ishb1", "InteriorSoundHard", 2);
    model.component("comp1").physics("acpr").feature("ishb1").selection().named("geom1_difsel1");
    model.component("comp1").physics("acpr").create("ipp1", "InteriorPerforatedPlate", 2);
    model.component("comp1").physics("acpr").feature("ipp1").selection().named("geom1_sel3");
    model.component("comp1").physics("acpr").feature("ipp1").set("porArea", "sigma_p");
    model.component("comp1").physics("acpr").feature("ipp1").set("tp", "t_w");
    model.component("comp1").physics("acpr").feature("ipp1").set("dh", "d_h");
    model.component("comp1").physics("acpr").feature("ipp1").set("FluidMaterial", "mat1");
    model.component("comp1").physics("acpr").create("ipp2", "InteriorPerforatedPlate", 2);
    model.component("comp1").physics("acpr").feature("ipp2").selection().named("geom1_sel4");
    model.component("comp1").physics("acpr").feature("ipp2").set("porArea", "sigma_p");
    model.component("comp1").physics("acpr").feature("ipp2").set("tp", "t_w");
    model.component("comp1").physics("acpr").feature("ipp2").set("dh", "d_h");
    model.component("comp1").physics("acpr").feature("ipp2").set("FluidMaterial", "mat1");
    model.component("comp1").physics("acpr").feature("ipp2").set("IncludeUserDefinedTerms", true);
    model.component("comp1").physics("acpr").feature("ipp2").set("theta", 1);
    model.component("comp1").physics("acpr").create("ipp3", "InteriorPerforatedPlate", 2);
    model.component("comp1").physics("acpr").feature("ipp3").selection().named("geom1_sel5");
    model.component("comp1").physics("acpr").feature("ipp3").set("porArea", "sigma_bi");
    model.component("comp1").physics("acpr").feature("ipp3").set("tp", "t_w");
    model.component("comp1").physics("acpr").feature("ipp3").set("dh", "d_h");
    model.component("comp1").physics("acpr").feature("ipp3").set("FluidMaterial", "mat1");
    model.component("comp1").physics("acpr").create("ipp4", "InteriorPerforatedPlate", 2);
    model.component("comp1").physics("acpr").feature("ipp4").selection().named("geom1_sel6");
    model.component("comp1").physics("acpr").feature("ipp4").set("porArea", "sigma_bo");
    model.component("comp1").physics("acpr").feature("ipp4").set("tp", "t_w");
    model.component("comp1").physics("acpr").feature("ipp4").set("dh", "d_h");
    model.component("comp1").physics("acpr").feature("ipp4").set("FluidMaterial", "mat1");
    model.component("comp1").physics("acpr").create("port1", "Port", 2);
    model.component("comp1").physics("acpr").feature("port1").selection().named("geom1_sel1");
    model.component("comp1").physics("acpr").feature("port1").set("PortType", "Circular");
    model.component("comp1").physics("acpr").feature("port1").set("pamp", "p0");
    model.component("comp1").physics("acpr").feature("port1").create("cpra1", "CircularPortReferenceAxis", 0);
    model.component("comp1").physics("acpr").feature("port1").feature("cpra1").selection().set(5, 8);
    model.component("comp1").physics("acpr").create("port2", "Port", 2);
    model.component("comp1").physics("acpr").feature("port2").selection().named("geom1_sel2");
    model.component("comp1").physics("acpr").feature("port2").set("PortType", "Circular");
    model.component("comp1").physics("acpr").feature("port2").create("cpra1", "CircularPortReferenceAxis", 0);
    model.component("comp1").physics("acpr").feature("port2").feature("cpra1").selection().set(1, 4);
    model.component("comp1").physics("acpr").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("acpr").feature("sym1").selection().named("geom1_boxsel1");

    model.study("std1").feature("freq").set("plist", "range(20,5,600)");

    model.component("comp1").physics("acpr").prop("MeshControl").set("ElementsPerWavelength", "UserDefined");
    model.component("comp1").physics("acpr").prop("MeshControl").set("nperlambda", 8);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 117, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 117, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 117, 0);
    model.result("pg3").create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("iso1").set("number", "10");
    model.result("pg3").feature("iso1").set("colortable", "Wave");
    model.result("pg3").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr)");
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickplane", "xy");
    model.result("pg1").run();
    model.result("pg1").set("data", "mir1");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").active(false);
    model.result("pg2").run();
    model.result("pg2").set("data", "mir1");
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", "acpr.Lp_t");
    model.result("pg2").feature("mslc1").set("descr", "\u603b\u58f0\u538b\u7ea7");
    model.result("pg2").feature("mslc1").set("xnumber", "5");
    model.result("pg2").feature("mslc1").set("ynumber", "0");
    model.result("pg2").feature("mslc1").set("colortable", "Rainbow");
    model.result("pg2").feature("mslc1").set("colorscalemode", "linear");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 103, 0);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("legendpos", "bottom");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 108, 0);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("data", "mir1");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u4f20\u8f93\u635f\u8017");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u4f20\u8f93\u635f\u8017 (dB)");
    model.result("pg4").create("oct1", "OctaveBand");
    model.result("pg4").feature("oct1").set("quantity", "bandpower");
    model.result("pg4").feature("oct1").set("markerpos", "datapoints");
    model.result("pg4").feature("oct1").set("linewidth", "preference");
    model.result("pg4").feature("oct1").selection().geom("geom1");
    model.result("pg4").feature("oct1").set("exprtype", "power");
    model.result("pg4").feature("oct1").set("expr", "acpr.port1.P_in");
    model.result("pg4").feature("oct1").set("powerref", "acpr.port2.P_out");
    model.result("pg4").feature("oct1").set("quantity", "continuous");
    model.result("pg4").feature("oct1").set("legend", true);
    model.result("pg4").feature("oct1").set("legendmethod", "manual");
    model.result("pg4").feature("oct1").setIndex("legends", "\u8ba1\u7b97\u503c", 0);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "TL_exp(freq)", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u4f20\u8f93\u635f\u8017", 0);
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "\u6d4b\u91cf\u503c", 0);
    model.result("pg4").run();
    model.result("pg4").set("xlog", false);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u5f3a\u5ea6");
    model.result("pg5").set("data", "mir1");
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"acpr.Ix", "acpr.Iy", "acpr.Iz"});
    model.result("pg5").feature("str1").set("descr", "\u5f3a\u5ea6");
    model.result("pg5").feature("str1").set("linetype", "tube");
    model.result("pg5").feature("str1").create("col1", "Color");
    model.result("pg5").run();
    model.result("pg5").feature("str1").feature("col1").set("expr", "acpr.I_mag");
    model.result("pg5").feature("str1").feature("col1").set("descr", "\u5f3a\u5ea6\u5927\u5c0f");
    model.result("pg5").feature("str1").feature("col1").set("colortable", "Rainbow");
    model.result("pg5").feature("str1").feature("col1").set("colorscalemode", "linear");
    model.result("pg5").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 106, 0);

    model.title("\u7a7f\u5b54\u677f\u6d88\u58f0\u5668");

    model
         .description("\u672c\u4f8b\u5206\u6790\u4e86\u5e26\u7a7f\u5b54\u6321\u677f\u548c\u7ba1\u9053\u7684\u6c7d\u8f66\u6d88\u58f0\u5668\u5728\u4e00\u5b9a\u9891\u7387\u8303\u56f4\u5185\u7684\u58f0\u5b66\u95ee\u9898\u3002\u4eff\u771f\u7ed3\u679c\u663e\u793a\uff0c\u4f20\u8f93\u635f\u8017\u4e0e\u5b9e\u9a8c\u6570\u636e\u5b8c\u5168\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("perforated_muffler.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
