/*
 * michelson_interferometer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:21 by COMSOL 6.3.0.290. */
public class michelson_interferometer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Spectrometers_and_Monochromators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.param().set("lam", "632.8[nm]");
    model.param().descr("lam", "Wavelength of He-Ne laser");
    model.param().set("delta_d", "8000*lam");
    model.param().descr("delta_d", "Optical path length difference");
    model.param().set("th", "0.12[in]");
    model.param().descr("th", "Thickness of mirrors");
    model.param().set("dia", "0.5[in]");
    model.param().descr("dia", "Diameter of mirrors");
    model.param().set("d1", "2.335[in]");
    model.param().descr("d1", "Distance between mirror M1 and the center of the beam splitter");
    model.param().set("d2", "d1-delta_d");
    model.param().descr("d2", "Distance between mirror M2 and the center of the beam splitter");
    model.param().set("dE", "12[in]");
    model.param().descr("dE", "Distance between the beam splitter and the screen");
    model.param().set("n_int", "3.9641");
    model.param().descr("n_int", "Refractive index of the beam-splitter interface");
    model.param().set("n_coat", "1.2354");
    model.param().descr("n_coat", "Refractive index of the anti-reflective coating");

    model.geom()
         .load(new String[]{"part1"}, "Ray_Optics_Module\\3D\\Beam_Splitters\\beam_splitter_cube.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d", "dia");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "dia/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "th");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "-d2-th", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "y");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "dia/2");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "th");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"d1", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"4*dia", "th", "4*dia"});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "dE+th/2", "0"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"200[mm]", "450[mm]", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "100[mm]", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "100[mm]", "0"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").set("transparency", true);
    model.component("comp1").view("view1").hideObjects("hide1").set("fin", 1, 2, 3, 4, 5, 33);

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
         .create("DispersionModelSellmeierStandard", "DispersionModelSellmeierStandard", "Sellmeier");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ThermoOpticDispersionModelSchott", "ThermoOpticDispersionModelSchott", "Schott \u70ed\u5149");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "\u6768\u6c0f\u6a21\u91cf\u548c\u6cca\u677e\u6bd4");
    model.component("comp1").material("mat2").propertyGroup()
         .create("InternalTransmittance10", "InternalTransmittance10", "\u5185\u90e8\u900f\u5c04\u7387\uff0c10\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("InternalTransmittance25", "InternalTransmittance25", "\u5185\u90e8\u900f\u5c04\u7387\uff0c25\u00a0mm \u6837\u672c\u539a\u5ea6");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").label("Schott N-BK7 Glass");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2.51[g/cm^3]");
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "0.858[J/(g*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.114[W/(m*K)]", "0", "0", "0", "1.114[W/(m*K)]", "0", "0", "0", "1.114[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.1E-6[1/K]", "0", "0", "0", "7.1E-6[1/K]", "0", "0", "0", "7.1E-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .set("ODsma", new String[]{"1.03961212E+00", "2.31792344E-01", "1.01046945E+00", "6.00069867E-03", "2.00179144E-02", "1.03560653E+02"});
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .set("Trefsma", "22[degC]");
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .set("Prefsma", "1[atm]");
    model.component("comp1").material("mat2").propertyGroup("DispersionModelSellmeierStandard")
         .addInput("frequency");
    model.component("comp1").material("mat2").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("TOsco", new String[]{"1.86E-6", "1.31E-8", "-1.37E-11", "4.34E-7", "6.27E-10", "0.17"});
    model.component("comp1").material("mat2").propertyGroup("ThermoOpticDispersionModelSchott")
         .set("Trefsco", "20[degC]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "82.0[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.206");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("funcname", "taui10");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("table", new String[][]{{"2500", "0.665"}, 
         {"2325", "0.793"}, 
         {"1970", "0.933"}, 
         {"1530", "0.992"}, 
         {"1060", "0.999"}, 
         {"700", "0.998"}, 
         {"660", "0.998"}, 
         {"620", "0.998"}, 
         {"580", "0.998"}, 
         {"546", "0.998"}, 
         {"500", "0.998"}, 
         {"460", "0.997"}, 
         {"436", "0.997"}, 
         {"420", "0.997"}, 
         {"405", "0.997"}, 
         {"400", "0.997"}, 
         {"390", "0.996"}, 
         {"380", "0.993"}, 
         {"370", "0.991"}, 
         {"365", "0.988"}, 
         {"350", "0.967"}, 
         {"334", "0.905"}, 
         {"320", "0.77"}, 
         {"310", "0.574"}, 
         {"300", "0.292"}, 
         {"290", "0.063"}});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10")
         .set("taui10", "taui10(c_const/freq)");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance10").addInput("frequency");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").func("int1")
         .set("funcname", "taui25");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").func("int1")
         .set("table", new String[][]{{"2500", "0.36"}, 
         {"2325", "0.56"}, 
         {"1970", "0.84"}, 
         {"1530", "0.98"}, 
         {"1060", "0.997"}, 
         {"700", "0.996"}, 
         {"660", "0.994"}, 
         {"620", "0.994"}, 
         {"580", "0.995"}, 
         {"546", "0.996"}, 
         {"500", "0.994"}, 
         {"460", "0.993"}, 
         {"436", "0.992"}, 
         {"420", "0.993"}, 
         {"405", "0.993"}, 
         {"400", "0.992"}, 
         {"390", "0.989"}, 
         {"380", "0.983"}, 
         {"370", "0.977"}, 
         {"365", "0.971"}, 
         {"350", "0.92"}, 
         {"334", "0.78"}, 
         {"320", "0.52"}, 
         {"310", "0.25"}, 
         {"300", "0.05"}});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").func("int1")
         .set("extrap", "none");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").func("int1")
         .set("argunit", new String[]{"nm"});
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25")
         .set("taui25", "taui25(c_const/freq)");
    model.component("comp1").material("mat2").propertyGroup("InternalTransmittance25").addInput("frequency");
    model.component("comp1").material("mat2").selection().set(4, 5);

    model.component("comp1").physics("gop").prop("IntensityComputation")
         .setIndex("IntensityComputation", "ComputeIntensityAndPower", 0);
    model.component("comp1").physics("gop").prop("ComputePhase").setIndex("ComputePhase", 1, 0);
    model.component("comp1").physics("gop").feature("matd1")
         .set("ThinDielectricFilmsOnBoundary", "AntiReflectiveCoating");
    model.component("comp1").physics("gop").feature("matd1").set("TreatAsSingleLayerDielectricFilm", true);
    model.component("comp1").physics("gop").feature("matd1").set("lambda0t", "lam");
    model.component("comp1").physics("gop").feature("op1").set("lambda0", "lam");
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").selection().set(7);
    model.component("comp1").physics("gop").create("mir1", "Mirror", 2);
    model.component("comp1").physics("gop").feature("mir1").selection().set(14, 27);
    model.component("comp1").physics("gop").create("matd2", "MaterialDiscontinuity", 2);
    model.component("comp1").physics("gop").feature("matd2")
         .set("ThinDielectricFilmsOnBoundary", "SpecifyReflectance");
    model.component("comp1").physics("gop").feature("matd2").set("Rf", 0.5);
    model.component("comp1").physics("gop").feature("matd2").set("TreatAsSingleLayerDielectricFilm", true);
    model.component("comp1").physics("gop").feature("matd2").set("nf", "n_int");
    model.component("comp1").physics("gop").feature("matd2").set("lambda0t", "lam");
    model.component("comp1").physics("gop").feature("matd2").set("thetait", "45[deg]");
    model.component("comp1").physics("gop").feature("matd2").selection().set(19);
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", "-100[mm]", 0);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new int[]{1, 0, 0});
    model.component("comp1").physics("gop").feature("relg1").set("WavefrontShape", "SphericalWave");
    model.component("comp1").physics("gop").feature("relg1").set("r00", "-1[m]");
    model.component("comp1").physics("gop").feature("relg1").set("InitialPolarizationType", "FullyPolarized");
    model.component("comp1").physics("gop").feature("relg1").set("InitialPolarization", "UserDefined");
    model.component("comp1").physics("gop").feature("relg1").set("uref", new int[]{0, 0, 1});
    model.component("comp1").physics("gop").create("rt1", "RayTermination", -1);
    model.component("comp1").physics("gop").feature("rt1")
         .set("SpatialExtentsOfRayPropagation", "BoundingBoxFromGeometry");

    model.component("comp1").mesh("mesh1").autoMeshSize(7);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("tlist", "range(0,0.1,2.2)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol1");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 23, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.I");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("data", "ray1");
    model.result().dataset("cpl1").set("quickplane", "xz");
    model.result().dataset("cpl1").set("quicky", "dE");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").set("data", "cpl1");
    model.result("pg2").create("intr1", "InterferencePattern");
    model.result("pg2").feature("intr1").set("originspec", "intensity");
    model.result("pg2").run();

    model.title("\u8fc8\u514b\u5c14\u900a\u5e72\u6d89\u4eea");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6839\u636e\u5149\u7a0b\u957f\u5ea6\u7565\u6709\u4e0d\u540c\u7684\u4e24\u675f\u5149\u7ebf\u7684\u53e0\u52a0\u6765\u7ed8\u5236\u5e72\u6d89\u56fe\u3002\u91c7\u7528\u4e86\u7b80\u5355\u7684\u8fc8\u514b\u5c14\u900a\u5e72\u6d89\u4eea\uff0c\u901a\u8fc7\u79fb\u52a8\u5176\u4e2d\u4e00\u4e2a\u53cd\u5c04\u955c\u6765\u5b9e\u73b0\u5149\u7a0b\u957f\u5ea6\u7684\u6539\u53d8\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("michelson_interferometer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
