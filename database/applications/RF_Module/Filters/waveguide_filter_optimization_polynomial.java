/*
 * waveguide_filter_optimization_polynomial.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:37 by COMSOL 6.3.0.290. */
public class waveguide_filter_optimization_polynomial {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Filters");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

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

    model.component("comp1").geom("geom1")
         .insertFile("waveguide_filter_optimization_polynomial_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("unisel1");

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u4f18\u5316\u53c2\u6570");
    model.param("par2").set("f1", "9[GHz]*2[cm]/w_wg");
    model.param("par2").descr("f1", "\u4e00\u9636\u9891\u7387");
    model.param("par2").set("f2", "f1/9*10");
    model.param("par2").descr("f2", "\u901a\u5e26\u9891\u7387");
    model.param("par2").set("f3", "1.1*f2");
    model.param("par2").descr("f3", "\u4e8c\u9636\u9891\u7387");
    model.param("par2").set("df", "0.03*f2");
    model.param("par2").descr("df", "\u901a\u9891\u5e26\u5bbd");
    model.param("par2").set("dfN", "5");
    model.param("par2").descr("dfN", "\u5e26\u5bbd\u9891\u7387");

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
    model.component("comp1").material().create("matlnk1", "Link");

    model.component("comp1").physics("emw").prop("components").set("components", "outofplane");
    model.component("comp1").physics("emw").create("port1", "Port", 1);
    model.component("comp1").physics("emw").feature("port1").selection().named("geom1_boxsel4");
    model.component("comp1").physics("emw").feature("port1").set("PortType", "Rectangular");
    model.component("comp1").physics("emw").create("port2", "Port", 1);
    model.component("comp1").physics("emw").feature("port2").selection().named("geom1_boxsel5");
    model.component("comp1").physics("emw").feature("port2").set("PortType", "Rectangular");

    model.component("comp1").common().create("fsd1", "FreeShapeDomain");
    model.component("comp1").common("fsd1").selection().all();
    model.component("comp1").common().create("sss1", "SectorShape");
    model.component("comp1").common("sss1").selection().named("geom1_comsel1");
    model.component("comp1").common().create("fsr1", "FreeShapeSymmetry");
    model.component("comp1").common("fsr1").selection().named("geom1_unisel1");
    model.component("comp1").common().create("poly1", "PolynomialBoundary");
    model.component("comp1").common("poly1").selection().named("geom1_boxsel3");
    model.component("comp1").common("poly1").set("maximumDisplacement", "w_wg/6");
    model.component("comp1").common("poly1").set("order", "1");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("obj", "if(abs(emw.freq-f2)<df/1.9,emw.S11dB,emw.S21dB)");
    model.component("comp1").variable("var1").descr("obj", "\u76ee\u6807\u51fd\u6570");

    model.study("std1").feature("freq").set("plist", "f1 range(f2-df/2,df/(dfN-1),f2+df/2) f3");
    model.study("std1").create("sho", "ShapeOptimization");
    model.study("std1").feature("sho").set("mmamaxiter", 25);
    model.study("std1").feature("sho").set("movelimitactive", false);
    model.study("std1").feature("sho").set("optobj", new String[]{"comp1.obj"});
    model.study("std1").feature("sho").set("descr", new String[]{"\u76ee\u6807\u51fd\u6570"});
    model.study("std1").feature("sho").set("objectivesolution", "max");
    model.study("std1").label("\u7814\u7a76 1\uff1a\u5f62\u72b6\u4f18\u5316");
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (emw)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"S11", "S21"});
    model.result("pg2").label("S \u53c2\u6570 (emw)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg2").feature("glob1").set("xdataexpr", "freq");
    model.result("pg2").feature("glob1").set("xdataunit", "GHz");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg3", "SmithGroup");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("rgr1", "ReflectionGraph");
    model.result("pg3").feature("rgr1").set("unit", new String[]{""});
    model.result("pg3").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg3").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg3").label("\u53f2\u5bc6\u65af\u56fe (emw)");
    model.result("pg3").feature("rgr1").set("titletype", "manual");
    model.result("pg3").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg3").feature("rgr1").set("linemarker", "point");
    model.result("pg3").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("rgr1").create("col1", "Color");
    model.result("pg3").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg3").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result().create("pg4", "PlotGroup2D");
    model.result().dataset().duplicate("dset1g1", "dset1");
    model.result().dataset("dset1g1")
         .label("\u7814\u7a76 1\uff1a\u5f62\u72b6\u4f18\u5316/\u89e3 1 (1) - \u51e0\u4f55");
    model.result().dataset("dset1g1").set("frametype", "geometry");
    model.result("pg4").label("\u5f62\u72b6\u4f18\u5316");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").set("frametype", "geometry");
    model.result("pg4").set("edgecolor", "gray");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", "1");
    model.result("pg4").feature("line1").set("coloring", "uniform");
    model.result("pg4").feature("line1").set("color", "fromtheme");
    model.result("pg4").create("arwl1", "ArrowLine");
    model.result("pg4").feature("arwl1").set("data", "dset1g1");
    model.result("pg4").feature("arwl1").set("expr", new String[]{"fsd1.dXg", "fsd1.dYg"});
    model.result("pg4").feature("arwl1").set("scaleactive", true);
    model.result("pg4").feature("arwl1").set("scale", "1");
    model.result("pg4").feature("arwl1").set("placement", "uniform");
    model.result("pg4").feature("arwl1").set("arrowcount", 200);
    model.result("pg4").feature("arwl1").create("col1", "Color");
    model.result("pg4").feature("arwl1").feature("col1").set("expr", "fsd1.rel_disp");
    model.result("pg4").feature("arwl1").feature("col1").set("rangecoloractive", "on");
    model.result("pg4").feature("arwl1").feature("col1").set("rangecolormin", 0);
    model.result("pg4").feature("arwl1").feature("col1").set("rangecolormax", 1);
    model.result("pg4").feature("arwl1").create("sel1", "Selection");
    model.result("pg4").feature("arwl1").feature("sel1").selection().named("dsel_fsd1");
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").feature("arwl1").set("placement", "elements");

    model.study("std1").feature("sho").set("plot", true);
    model.study("std1").feature("sho").set("plotgroup", "pg4");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.study("std1").feature("sho").set("probewindow", "");

    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("hght1", "Height");
    model.result("pg1").run();
    model.result().dataset().create("extr1", "Extrude2D");
    model.result().dataset("extr1").set("zmax", "h_wg");
    model.result().dataset().create("filt1", "Filter");
    model.result().dataset("filt1").set("data", "extr1");
    model.result().dataset("filt1").set("expr", "1");
    model.result().dataset("filt1").set("lowerexpr", "0.5");
    model.result().dataset("filt1").createMeshPart("mcomp1", "mgeom1", "mpart1", "imp1");

    model.mesh("mpart1").feature("imp1").set("facepartition", "detectfaces");
    model.mesh("mpart1").feature("imp1").importData();
    model.mesh("mpart1").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").lengthUnit("mm");

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("mesh", "mpart1");
    model.component("comp2").geom("geom2").run("imp1");
    model.component("comp2").geom("geom2").create("boxsel1", "BoxSelection");
    model.component("comp2").geom("geom2").feature("boxsel1").label("\u7aef\u53e3 1");
    model.component("comp2").geom("geom2").feature("boxsel1").set("entitydim", 2);
    model.component("comp2").geom("geom2").feature("boxsel1").set("xmax", "-l_wg-spacing*cavities/2.1");
    model.component("comp2").geom("geom2").feature("boxsel1").set("condition", "inside");
    model.component("comp2").geom("geom2").feature().duplicate("boxsel2", "boxsel1");
    model.component("comp2").geom("geom2").feature("boxsel2").label("\u7aef\u53e3 2");
    model.component("comp2").geom("geom2").feature("boxsel2").set("xmin", "l_wg+spacing*cavities/2.1");
    model.component("comp2").geom("geom2").feature("boxsel2").set("xmax", Double.POSITIVE_INFINITY);
    model.component("comp2").geom("geom2").run();

    model.component("comp2").material().create("matlnk2", "Link");

    model.component("comp2").physics().create("emw2", "ElectromagneticWaves", "geom2");

    model.study("std1").feature("freq").setSolveFor("/physics/emw2", false);

    model.component("comp2").physics("emw2").create("port1", "Port", 2);
    model.component("comp2").physics("emw2").feature("port1").selection().named("geom2_boxsel1");
    model.component("comp2").physics("emw2").feature("port1").set("PortType", "Rectangular");
    model.component("comp2").physics("emw2").create("port2", "Port", 2);
    model.component("comp2").physics("emw2").feature("port2").selection().named("geom2_boxsel2");
    model.component("comp2").physics("emw2").feature("port2").set("PortType", "Rectangular");

    model.component("comp2").mesh("mesh2").create("swe1", "Sweep");
    model.component("comp2").mesh("mesh2").feature("swe1").set("facemethod", "tri");
    model.component("comp2").mesh("mesh2").feature("size").set("hauto", 1);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").set("plotgroup", "Default");
    model.study("std2").feature("freq").set("solnum", "auto");
    model.study("std2").feature("freq").set("notsolnum", "auto");
    model.study("std2").feature("freq").set("outputmap", new String[]{});
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").setSolveFor("/physics/emw", false);
    model.study("std2").feature("freq").setSolveFor("/physics/emw2", true);
    model.study("std2").feature("freq").set("plist", "range(f1-df,0.02[GHz],f3+df)");
    model.study("std2").feature("freq").set("probesel", "none");
    model.study("std2").feature("freq").setSolveFor("/frame/material1", false);
    model.study("std2").feature("freq").setEntry("outputmap", "emw2", "selection");
    model.study("std2").feature("freq").setEntry("outputselectionmap", "emw2", "geom2_boxsel1;geom2_boxsel2");
    model.study("std2").feature("freq").setEntry("mesh", "geom1", "nomesh");
    model.study("std2").setGenPlots(false);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u9a8c\u8bc1\uff08\u4e09\u7ef4\uff09");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("linestyle", "none");
    model.result("pg2").feature("glob1").set("linemarker", "square");
    model.result("pg2").run();
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("linewidth", "preference");
    model.result("pg2").feature("glob2").set("data", "dset3");
    model.result("pg2").feature("glob2").set("expr", new String[]{"emw2.S11dB"});
    model.result("pg2").feature("glob2").set("descr", new String[]{"S11"});
    model.result("pg2").feature("glob2").set("expr", new String[]{"emw2.S11dB", "emw2.S21dB"});
    model.result("pg2").feature("glob2").set("descr", new String[]{"S11", "S21"});
    model.result("pg2").feature("glob2").setIndex("descr", "S11 (3D)", 0);
    model.result("pg2").feature("glob2").setIndex("descr", "S21 (3D)", 1);
    model.result("pg2").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg2").feature("glob2").set("linewidth", 2);
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 4, 0);
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").run();

    model
         .title("\u6ce2\u5bfc\u8679\u819c\u5e26\u901a\u6ee4\u6ce2\u5668\u7684\u4f18\u5316 - \u591a\u9879\u5f0f\u7248\u672c");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u5f62\u72b6\u4f18\u5316\u6765\u8bbe\u8ba1\u6ce2\u5bfc\u6ee4\u6ce2\u5668\uff0c\u901a\u8fc7\u79fb\u52a8\u51e0\u4f55\u4e2d\u7684\u9876\u70b9\u6765\u8fdb\u884c\u4f18\u5316\u3002\u5176\u4e2d\u5bf9\u521d\u59cb\u51e0\u4f55\u7684\u8679\u819c\u8fdb\u884c\u4f18\u5316\uff0c\u4ee5\u786e\u4fdd\u826f\u597d\u7684\u5e26\u901a\u54cd\u5e94\u548c\u5e26\u5916\u6291\u5236\uff0c\u540c\u65f6\u4fdd\u6301\u7ed3\u6784\u7684\u53cc\u955c\u5bf9\u79f0\u3002");

    model.component("comp1").mesh("mesh1").clearMesh();
    model.mesh("mpart1").clearMesh();
    model.component("comp2").mesh("mesh2").clearMesh();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("waveguide_filter_optimization_polynomial.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
