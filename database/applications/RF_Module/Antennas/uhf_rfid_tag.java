/*
 * uhf_rfid_tag.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:29 by COMSOL 6.3.0.290. */
public class uhf_rfid_tag {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Antennas");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/emw", true);
    model.study("std1").feature("freq").set("plist", "915[MHz]");

    model.param().set("Zc", "15-j*125[ohm]");
    model.param().descr("Zc", "\u82af\u7247\u963b\u6297");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "uhf_rfid_tag.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", 150);
    model.component("comp1").geom("geom1").feature("sph1").setIndex("layer", 30, 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Gamma", "(emw.Zport_1-conj(Zc))/(emw.Zport_1+Zc)");
    model.component("comp1").variable("var1")
         .descr("Gamma", "\u7528\u4e8e\u590d\u963b\u6297\u5339\u914d\u7684\u53cd\u5c04\u7cfb\u6570");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(1, 2, 3, 4, 9, 10, 11, 12);
    model.component("comp1").coordSystem("pml1").set("ScalingType", "Spherical");

    model.component("comp1").physics("emw").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("emw").feature("pec2").selection().set(25, 27, 54);
    model.component("comp1").physics("emw").create("lport1", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport1").selection().set(35);
    model.component("comp1").physics("emw").create("ffd1", "FarFieldDomain", 3);

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
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("FR4 (Circuit Board)");
    model.component("comp1").material("mat2").set("family", "pcb");
    model.component("comp1").material("mat2").set("color", "custom");
    model.component("comp1").material("mat2").set("customcolor", "0 0.5019607843137255 0.25098039215686274");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0.004[S/m]", "0", "0", "0", "0.004[S/m]", "0", "0", "0", "0.004[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"18e-6[1/K]", "0", "0", "0", "18e-6[1/K]", "0", "0", "0", "18e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "1369[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "1900[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "22[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.15");
    model.component("comp1").material("mat2").selection().set(7);
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().set(6);
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"2.1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide1").set(6, 10, 16, 37, 40, 42);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (emw)");
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
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1").set("expr", new String[]{"emw.S11dB"});
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").run();
    model.result().numerical("gev1").setResult();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u7535\u573a, \u5bf9\u6570 (emw)");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(25, 27, 54);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(5, 6, 7, 8, 39, 40, 45, 50);

    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "emw.normE");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().set(25, 27, 54);
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg2").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf1").feature("mtrl1").set("family", "chrome");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", "emw.normE");
    model.result("pg2").feature("mslc1").create("sel1", "Selection");
    model.result("pg2").feature("mslc1").feature("sel1").selection().set(5);
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("mslc1").set("colortabletype", "discrete");
    model.result("pg2").feature("mslc1").set("bandcount", 20);
    model.result("pg2").feature("mslc1").create("tran1", "Transparency");
    model.result("pg2").feature("mslc1").feature("tran1").set("transparency", 0.5);
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("expr", "emw.normE");
    model.result("pg2").feature("surf2").create("sel1", "Selection");
    model.result("pg2").feature("surf2").feature("sel1").selection()
         .set(13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 26, 28, 29, 30, 31, 32, 33, 34, 35, 51, 52, 55, 56, 57, 58, 59, 60, 61, 62);
    model.result("pg2").feature("surf2").set("colortable", "Dipole");
    model.result("pg2").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf2").create("tran1", "Transparency");
    model.result("pg2").feature("surf2").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").camera()
         .set("position", new double[]{-610.2321458899457, -813.6428965692935, 610.2321458899457});
    model.component("comp1").view("view2").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view2").camera().set("zoomanglefull", 20.85742950439453);

    model.result("pg2").set("view", "view2");
    model.result().create("pg3", "PolarGroup");
    model.result("pg3").label("\u4e8c\u7ef4\u8fdc\u573a (emw)");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("rp1", "RadiationPattern");
    model.result("pg3").feature("rp1").set("legend", "on");
    model.result("pg3").feature("rp1").set("phidisc", "180");
    model.result("pg3").feature("rp1").set("expr", new String[]{"emw.normEfar"});
    model.result("pg3").feature("rp1").create("exp1", "Export");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u4e09\u7ef4\u8fdc\u573a\uff0c\u589e\u76ca (emw)");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").set("view", "new");
    model.result("pg4").set("edges", "off");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").create("rp1", "RadiationPattern");
    model.result("pg4").feature("rp1").set("expr", new String[]{"emw.rGaindBEfar"});
    model.result("pg4").feature("rp1").set("colorexpr", new String[]{"emw.normEfar"});
    model.result("pg4").feature("rp1").set("useradiusascolor", true);
    model.result("pg4").feature("rp1").set("directivityexpr", new String[]{"emw.normEfar^2"});
    model.result("pg4").feature("rp1").set("thetadisc", "45");
    model.result("pg4").feature("rp1").set("phidisc", "90");
    model.result("pg4").feature("rp1").set("directivity", "on");
    model.result("pg4").feature("rp1").set("colortable", "RainbowLight");
    model.result("pg4").feature("rp1").create("exp1", "Export");
    model.result("pg4").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.theta", 0);
    model.result("pg4").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.phi", 1);
    model.result("pg3").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.theta", 0);
    model.result("pg3").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.phi", 1);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("xnumber", "0");
    model.result("pg1").feature("mslc1").set("ynumber", "0");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("rp1").set("phidisc", 40);
    model.result("pg4").run();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("expr", new String[]{"emw.Zport_1"});
    model.result().numerical("gev2").set("descr", new String[]{"\u96c6\u603b\u7aef\u53e3\u201c1\u201d\u963b\u6297"});
    model.result().numerical("gev2").set("unit", new String[]{"\u03a9"});
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").setIndex("expr", "20*log10(abs(Gamma))", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u5168\u5c40\u8ba1\u7b97 3");
    model.result().numerical("gev3").set("table", "tbl3");
    model.result().numerical("gev3").setResult();

    model.title("UHF RFID \u6807\u7b7e\u5efa\u6a21");

    model
         .description("UHF RFID \u6807\u7b7e\u5e7f\u6cdb\u7528\u4e8e\u6807\u8bb0\u548c\u8ddf\u8e2a\u5bb6\u755c\u3002\u672c\u4f8b\u6a21\u62df\u7279\u9ad8\u9891 (UHF) \u8303\u56f4\u4e0b\u7684\u65e0\u6e90\u5c04\u9891\u8bc6\u522b (RFID) \u6807\u7b7e\u3002\u5bf9\u4e8e\u82af\u7247\u5e94\u7b54\u5668\u7684\u590d\u963b\u6297\uff0c\u8ba1\u7b97\u4e86\u53cd\u5c04\u7cfb\u6570\u3002\u5176\u4e2d\u4f7f\u7528\u4e86\u4e0e\u5e38\u89c4\u6563\u5c04\u53c2\u6570\u5206\u6790\u6cd5\u4e0d\u540c\u7684\u5b9e\u53c2\u8003\u963b\u6297\u503c\u65b9\u6cd5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("uhf_rfid_tag.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
