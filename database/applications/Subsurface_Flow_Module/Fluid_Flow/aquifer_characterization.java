/*
 * aquifer_characterization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:28 by COMSOL 6.3.0.290. */
public class aquifer_characterization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/dl", true);

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", 100);
    model.component("comp1").geom("geom1").feature("sq1").set("pos", new int[]{-150, -150});
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("sq1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{3, 3});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{100, 100});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "-50,0,0,50", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "0,-50,50,0", 1);
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("N0", "0.1[kg/(m*s)]");
    model.param().descr("N0", "\u6cf5\u6e90\u5f3a\u5ea6");
    model.param().set("deltaH", "1[cm]");
    model.param().descr("deltaH", "\u6c34\u5934\u6d4b\u91cf\u8bef\u5dee");
    model.param().set("logKs0", "-5");
    model.param().descr("logKs0", "\u6c34\u529b\u4f20\u5bfc\u7387\uff0c\u521d\u59cb log10 \u503c");
    model.param().set("th", "1");
    model.param().descr("th", "\u6d4b\u91cf\u7cfb\u5217\u6307\u6570");
    model.param().set("sigma", "1");
    model.param().descr("sigma", "Sill \u53c2\u6570");
    model.param().set("r", "50[m]");
    model.param().descr("r", "Range \u53c2\u6570");
    model.param().set("elementTypeFactor", "1");
    model.param()
         .descr("elementTypeFactor", "1 \u8868\u793a\u56db\u8fb9\u5f62\uff0c0.5 \u8868\u793a\u4e09\u89d2\u5f62");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "aquifer_characterization_logKs_ref.txt");
    model.func("int1").set("struct", "grid");
    model.func("int1").importData();
    model.func("int1").label("logKs \u53c2\u8003");
    model.func("int1").setIndex("funcnametable", "logKs_ref", 0, 0);
    model.func("int1").set("interp", "neighbor");
    model.func("int1").setIndex("fununit", 1, 0);
    model.func("int1").setIndex("argunit", "m", 0);
    model.func("int1").setIndex("argunit", "m", 1);

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");
    model.component("comp1").coordSystem("ie1").selection().set(1, 2, 3, 4, 6, 7, 8, 9);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("logKs", "logKs_ref(x,y)");
    model.component("comp1").variable("var1").descr("logKs", "\u6c34\u529b\u4f20\u5bfc\u7387\uff0clog 10 \u503c");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Water, liquid");
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");

    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("permeabilityModelType", "conductivity");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("K", new String[]{"10^logKs0", "0", "0", "0", "10^logKs0", "0", "0", "0", "10^logKs0"});
    model.component("comp1").physics("dl").create("porous2", "PorousMedium", 2);
    model.component("comp1").physics("dl").feature("porous2").selection().set(5);
    model.component("comp1").physics("dl").feature("porous2").feature("pm1")
         .set("permeabilityModelType", "conductivity");
    model.component("comp1").physics("dl").feature("porous2").feature("pm1")
         .set("K", new String[]{"10^logKs", "0", "0", "0", "10^logKs", "0", "0", "0", "10^logKs"});
    model.component("comp1").physics("dl").create("hh1", "HydraulicHead", 1);
    model.component("comp1").physics("dl").feature("hh1").selection().set(1, 2, 3, 5, 7, 9, 15, 19, 25, 26, 27, 28);
    model.component("comp1").physics("dl").create("lms1", "LineMassSource", 0);
    model.component("comp1").physics("dl").feature("lms1").selection().set(6);
    model.component("comp1").physics("dl").feature("lms1").set("N0", "if(th==1,N0,0)");
    model.component("comp1").physics("dl").create("lms2", "LineMassSource", 0);
    model.component("comp1").physics("dl").feature("lms2").selection().set(7);
    model.component("comp1").physics("dl").feature("lms2").set("N0", "if(th==2,N0,0)");
    model.component("comp1").physics("dl").create("lms3", "LineMassSource", 0);
    model.component("comp1").physics("dl").feature("lms3").selection().set(8);
    model.component("comp1").physics("dl").feature("lms3").set("N0", "if(th==3,N0,0)");
    model.component("comp1").physics("dl").create("lms4", "LineMassSource", 0);
    model.component("comp1").physics("dl").feature("lms4").selection().set(10);
    model.component("comp1").physics("dl").feature("lms4").set("N0", "if(th==4,N0,0)");
    model.component("comp1").physics("dl").create("lms5", "LineMassSource", 0);
    model.component("comp1").physics("dl").feature("lms5").selection().set(11);
    model.component("comp1").physics("dl").feature("lms5").set("N0", "if(th==4,-N0,0)");
    model.component("comp1").physics("dl").create("lms6", "LineMassSource", 0);
    model.component("comp1").physics("dl").feature("lms6").selection().set(13);
    model.component("comp1").physics("dl").feature("lms6").set("N0", "if(th==3,-N0,0)");
    model.component("comp1").physics("dl").create("lms7", "LineMassSource", 0);
    model.component("comp1").physics("dl").feature("lms7").selection().set(14);
    model.component("comp1").physics("dl").feature("lms7").set("N0", "if(th==2,-N0,0)");
    model.component("comp1").physics("dl").create("lms8", "LineMassSource", 0);
    model.component("comp1").physics("dl").feature("lms8").selection().set(15);
    model.component("comp1").physics("dl").feature("lms8").set("N0", "if(th==1,-N0,0)");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 5);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection()
         .set(6, 7, 8, 10, 11, 13, 14, 15);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", 0.1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u538b\u529b (dl)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result("pg1").feature("str1").set("smooth", "internal");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colortable", "Darner");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").label("\u538b\u529b\uff0c\u6b63\u6f14");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u6d41\u7f51 (dl)");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "dl.H");
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("color", "green");
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("posmethod", "magnitude");
    model.result("pg2").feature("str1").set("madv", "manual");
    model.result("pg2").feature("str1").set("msatfactor", 1.4);
    model.result("pg2").feature("str1").set("color", "blue");
    model.result("pg2").feature("str1").set("resolution", "extrafine");
    model.result("pg2").feature("str1").set("smooth", "internal");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28);
    model.result("pg2").label("\u6d41\u7f51 (dl)");
    model.result("pg2").run();
    model.result("pg2").label("\u6e17\u6d41\u7f51\uff0c\u6b63\u6f14");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").physics().create("dode", "DomainODE", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/dode", true);

    model.component("comp2").physics("dode").prop("EquationForm").set("form", "Automatic");

    model.component("comp2").geom("geom2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std2").feature("stat").setSolveFor("/physics/dode", true);

    model.component("comp2").geom("geom2").create("sq1", "Square");
    model.component("comp2").geom("geom2").feature("sq1").set("size", 100);
    model.component("comp2").geom("geom2").feature("sq1").set("base", "center");
    model.component("comp2").geom("geom2").run("sq1");

    model.component("comp2").cpl().create("genext1", "GeneralExtrusion");

    model.component("comp2").geom("geom2").run();

    model.component("comp2").cpl("genext1").selection().all();
    model.component("comp2").cpl().create("aveop1", "Average");
    model.component("comp2").cpl("aveop1").set("axisym", true);
    model.component("comp2").cpl("aveop1").set("opname", "mean");
    model.component("comp2").cpl("aveop1").selection().all();
    model.component("comp2").cpl().create("intop1", "Integration");
    model.component("comp2").cpl("intop1").set("axisym", true);
    model.component("comp2").cpl("intop1").set("opname", "int0");
    model.component("comp2").cpl("intop1").selection().all();
    model.component("comp2").cpl("intop1").set("intorder", 0);

    model.component("comp2").variable().create("var2");
    model.component("comp2").variable("var2").label("\u53d8\u91cf\uff0c\u57df");
    model.component("comp2").variable("var2").selection().geom("geom2", 2);
    model.component("comp2").variable("var2").selection().set(1);
    model.component("comp2").variable("var2").set("logKs_ref", "logKs_ref(x,y)");
    model.component("comp2").variable("var2")
         .descr("logKs_ref", "\u6c34\u529b\u4f20\u5bfc\u7387\uff0c\u53c2\u8003\u6a21\u578b");
    model.component("comp2").variable("var2").set("areaFactor", "1/(elementTypeFactor*dvol)");
    model.component("comp2").variable("var2").descr("areaFactor", "\u6c42\u548c\u8865\u507f\u56e0\u5b50");
    model.component("comp2").variable("var2").set("dist", "sqrt((x-dest(x))^2+(y-dest(y))^2)");
    model.component("comp2").variable("var2").descr("dist", "\u6c42\u548c\u70b9\u95f4\u8ddd");
    model.component("comp2").variable().create("var3");
    model.component("comp2").variable("var3").label("\u53d8\u91cf\uff0c\u5168\u5c40");
    model.component("comp2").variable("var3").set("MSE", "mean((logKs-logKs_ref)^2)");
    model.component("comp2").variable("var3").descr("MSE", "\u9762\u79ef\u52a0\u6743\u5747\u65b9\u8bef\u5dee");
    model.component("comp2").variable("var3").set("logKs_mean", "int0(logKs*areaFactor)/int0(areaFactor)");
    model.component("comp2").variable("var3")
         .descr("logKs_mean", "\u79bb\u6563\u63a7\u5236\u53d8\u91cf\u5747\u503c");
    model.component("comp2").variable("var3").set("L_penalty", "int0((logKs-logKs_mean)*u*areaFactor)");
    model.component("comp2").variable("var3").descr("L_penalty", "\u7f5a\u51fd\u6570");

    model.component("comp2").func().create("an1", "Analytic");
    model.component("comp2").func("an1").label("\u534f\u65b9\u5dee\u51fd\u6570");
    model.component("comp2").func("an1").set("funcname", "Q");
    model.component("comp2").func("an1").set("expr", "sigma^2*exp(-x/r)");
    model.component("comp2").func("an1").setIndex("argunit", "m", 0);
    model.component("comp2").func("an1").set("fununit", "1");

    model.component("comp2").common().create("cvf1", "ControlVariableField");
    model.component("comp2").common("cvf1").set("name", "logKs");
    model.component("comp2").common("cvf1").selection().all();
    model.component("comp2").common("cvf1").set("initialValue", "logKs_ref");
    model.component("comp2").common("cvf1").set("shapeFunctionType", "shdisc");
    model.component("comp2").common("cvf1").set("order", "0");
    model.component("comp2").common().create("lso1", "LeastSquaresObjective");
    model.component("comp2").common("lso1").set("filename", "aquifer_characterization_zero.csv");
    model.component("comp2").common("lso1").importData();
    model.component("comp2").common("lso1").setEntry("columnType", "col1", "value");
    model.component("comp2").common("lso1").setEntry("scaleMethod", "col1", "manual");
    model.component("comp2").common("lso1").setEntry("variance", "col1", "manual");
    model.component("comp2").common("lso1").setEntry("modelExpression", "col1", "sqrt(L_penalty)");

    model.component("comp2").physics("dode").prop("Units").setIndex("CustomSourceTermUnit", 1, 0, 0);
    model.component("comp2").physics("dode").prop("ShapeProperty").set("order", 0);
    model.component("comp2").physics("dode").feature("dode1")
         .setIndex("f", "(logKs-logKs_mean-int0(u*Q(dist)*areaFactor))", 0);

    model.component("comp2").mesh("mesh2").create("map1", "Map");
    model.component("comp2").mesh("mesh2").feature("map1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").selection().all();
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("numelem", 10);
    model.component("comp2").mesh("mesh2").run();

    model.component("comp1").variable().duplicate("var4", "var1");
    model.component("comp1").variable("var4").set("logKs", "comp2.genext1(comp2.logKs)");

    model.component("comp1").common().create("lso2", "LeastSquaresObjective");
    model.component("comp1").common("lso2").selection().geom("geom1", 2);
    model.component("comp1").common("lso2").selection().all();
    model.component("comp1").common("lso2").set("filename", "aquifer_characterization_H1.csv");
    model.component("comp1").common("lso2").importData();
    model.component("comp1").common("lso2").setEntry("columnType", "col1", "coord");
    model.component("comp1").common("lso2").setEntry("columnType", "col2", "coord");
    model.component("comp1").common("lso2").setEntry("coordinate", "col2", "2");
    model.component("comp1").common("lso2").setEntry("modelExpression", "col3", "comp1.dl.H");
    model.component("comp1").common("lso2").setEntry("scaleMethod", "col3", "manual");
    model.component("comp1").common("lso2").setEntry("scaleValue", "col3", "deltaH");
    model.component("comp1").common("lso2").setEntry("variance", "col3", "manual");
    model.component("comp1").common("lso2").setIndex("paramNames", "N0", 0);
    model.component("comp1").common("lso2").setIndex("paramExprs", "", 0);
    model.component("comp1").common("lso2").setIndex("paramNames", "N0", 0);
    model.component("comp1").common("lso2").setIndex("paramExprs", "", 0);
    model.component("comp1").common("lso2").setIndex("paramNames", "th", 0);
    model.component("comp1").common("lso2").setIndex("paramExprs", 1, 0);
    model.component("comp1").common().duplicate("lso3", "lso2");
    model.component("comp1").common("lso3").discardData();
    model.component("comp1").common("lso3").set("filename", "aquifer_characterization_H2.csv");
    model.component("comp1").common("lso3").importData();
    model.component("comp1").common("lso3").setIndex("paramExprs", 2, 0);
    model.component("comp1").common().duplicate("lso4", "lso3");
    model.component("comp1").common("lso4").discardData();
    model.component("comp1").common("lso4").set("filename", "aquifer_characterization_H3.csv");
    model.component("comp1").common("lso4").importData();
    model.component("comp1").common("lso4").setIndex("paramExprs", 3, 0);
    model.component("comp1").common().duplicate("lso5", "lso4");
    model.component("comp1").common("lso5").discardData();
    model.component("comp1").common("lso5").set("filename", "aquifer_characterization_H4.csv");
    model.component("comp1").common("lso5").importData();
    model.component("comp1").common("lso5").setIndex("paramExprs", 4, 0);

    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledvariables", new String[]{"var1"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u538b\u529b (dl)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").feature("str1").set("smooth", "internal");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("data", "parent");
    model.result("pg3").feature("str1").selection().geom("geom1", 1);
    model.result("pg3").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").label("\u57df\u5e38\u5fae\u5206\u548c\u5fae\u5206\u4ee3\u6570\u65b9\u7a0b");
    model.result("pg4").feature("surf1").set("expr", "u");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("colortable", "Darner");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "logKs");
    model.result("pg4").feature("surf1").set("descr", "\u63a7\u5236\u53d8\u91cf\u573a");
    model.result("pg4").feature("surf1").set("rangecoloractive", true);
    model.result("pg4").feature("surf1").set("rangecolormin", -7);
    model.result("pg4").feature("surf1").set("rangecolormax", -3);
    model.result("pg4").feature("surf1").set("smooth", "none");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").label("log Ks\uff0c6 Obs.");
    model.result("pg3").set("windowtitle", "\u56fe\u5f62");
    model.result("pg4").set("window", "window2");
    model.result("pg4").set("windowtitle", "\u7ed8\u56fe\u201c2\u201d");
    model.result("pg4").set("window", "window2");
    model.result("pg4").set("windowtitle", "\u7ed8\u56fe\u201c2\u201d");
    model.result("pg4").run();
    model.result("pg4").set("window", "graphics");
    model.result("pg4").set("windowtitle", "\u56fe\u5f62");

    model.component("comp2").common("cvf1").set("initialValue", "logKs0");

    model.study("std2").create("opt", "Optimization");
    model.study("std2").feature("opt").set("optsolver", "lm");
    model.study("std2").feature("opt").set("opttolinner", 0.002);
    model.study("std2").feature("opt").set("lsqdatamethod", "all");
    model.study("std2").feature("opt").setIndex("objectiveActive", false, 1);
    model.study("std2").feature("opt").setIndex("objectiveActive", false, 2);
    model.study("std2").feature("opt").setIndex("objectiveActive", false, 3);
    model.study("std2").feature("opt").set("plot", true);
    model.study("std2").feature("opt").set("plotgroup", "pg4");
    model.study("std2").feature("opt").set("useobjtable", false);
    model.study("std2").createAutoSequences("all");
    model.study("std2").feature("opt").set("continuecontrolparams", new String[]{});
    model.study("std2").feature("opt").set("continuecontrolvals", new double[]{});
    model.study("std2").feature("opt").set("continuelagrangevals", new double[]{});
    model.study("std2").feature("opt").set("continuelagrangeparams", new String[]{});

    model.sol("sol2").runAll();

    model.result("pg3").run();
    model.result("pg2").run();
    model.result("pg4").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("MSE\uff0c6 obs.");
    model.result().evaluationGroup("eg1").set("data", "dset3");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"MSE"});
    model.result().evaluationGroup("eg1").feature("gev1")
         .set("descr", new String[]{"\u9762\u79ef\u52a0\u6743\u5747\u65b9\u8bef\u5dee"});
    model.result().evaluationGroup("eg1").run();

    model.sol("sol2").copySolution("sol3");

    model.study("std2").feature("opt").setIndex("objectiveActive", true, 1);
    model.study("std2").feature("opt").setIndex("objectiveActive", true, 2);
    model.study("std2").feature("opt").setIndex("objectiveActive", true, 3);

    model.result("pg4").run();
    model.result("pg4").set("data", "dset5");
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("log Ks\uff0c24 Obs.");
    model.result("pg5").set("data", "dset3");

    model.study("std2").feature("opt").set("plotgroup", "pg5");
    model.study("std2").createAutoSequences("all");
    model.study("std2").feature("opt").set("continuecontrolparams", new String[]{});
    model.study("std2").feature("opt").set("continuecontrolvals", new double[]{});
    model.study("std2").feature("opt").set("continuelagrangevals", new double[]{});
    model.study("std2").feature("opt").set("continuelagrangeparams", new String[]{});

    model.sol("sol2").runAll();

    model.result("pg3").run();
    model.result("pg5").run();
    model.result().evaluationGroup("eg1").set("data", "dset5");
    model.result().evaluationGroup().duplicate("eg2", "eg1");
    model.result().evaluationGroup("eg2").label("MSE\uff0c24 obs.");
    model.result().evaluationGroup("eg2").set("data", "dset3");
    model.result().evaluationGroup("eg2").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg2").run();

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledvariables", new String[]{"var4"});

    model.result("pg5").run();

    model.title("\u542b\u6c34\u5c42\u7279\u6027");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u201c\u4f18\u5316\u201d\u63a5\u53e3\u6c42\u89e3\u4e00\u4e2a\u9006\u95ee\u9898\uff0c\u5373\u6839\u636e\u5927\u91cf\u7684\u542b\u6c34\u5c42\u62bd\u6c34\u6d4b\u8bd5\u6765\u786e\u5b9a\u79bb\u6563\u7684\u4e8c\u6b21\u6805\u683c\u4e0a\u7684\u6c34\u529b\u4f20\u5bfc\u7387\u8fd9\u4e00\u7a7a\u95f4\u53d8\u91cf\u3002\u7531\u4e8e\u89c2\u6d4b\u9879\u7684\u6570\u91cf\u5c0f\u4e8e\u672a\u77e5\u53c2\u6570\u7684\u6570\u91cf\uff0c\u56e0\u6b64\u4f7f\u7528\u5730\u8d28\u7edf\u8ba1\u5b66\u7f5a\u9879\u6765\u533a\u5206\u53ef\u6bd4\u8f83\u7684\u62df\u5408\u503c\u7684\u89e3\u3002\u6d4b\u91cf\u6570\u636e\u6839\u636e\u4f7f\u7528\u201c\u8fbe\u897f\u5b9a\u5f8b\u201d\u63a5\u53e3\u5b9e\u73b0\u7684\u7ed9\u5b9a\u6b63\u6f14\u6a21\u578b\u751f\u6210\uff0c\u53ef\u4ee5\u5206\u6790\u53cd\u6f14\u65b9\u6cd5\u7684\u6548\u7387\u548c\u7cbe\u5ea6\uff0c\u4ee5\u53ca\u4f18\u5316\u6c42\u89e3\u5668\u7684\u6027\u80fd\u3002");

    model.label("aquifer_characterization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
