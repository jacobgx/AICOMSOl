/*
 * acoustics_pipe_system.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:58 by COMSOL 6.3.0.290. */
public class acoustics_pipe_system {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Pipe_Flow_Module\\Pipe_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").insertFile("acoustics_pipe_system_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(3);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 1);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 2);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 3);
    model.component("comp1").view("view1").set("hidestatus", "showonlyhidden");
    model.component("comp1").view("view1").hideObjects().clear();
    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(3);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 4);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 5);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 6);
    model.component("comp1").view("view1").set("hidestatus", "ignore");
    model.component("comp1").view("view1").hideObjects().clear();

    model.param().label("\u51e0\u4f55");
    model.param().create("par2");
    model.param("par2").label("\u6a21\u578b\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("f0", "120[Hz]", "\u4fe1\u53f7\u9891\u7387");
    model.param("par2").set("c0", "1500[m/s]", "\u58f0\u901f");
    model.param("par2").set("lam0", "c0/f0", "f0 \u7684\u6ce2\u957f");
    model.param("par2").set("T0", "1/f0", "f0 \u7684\u5468\u671f");
    model.param("par2").set("Tend", "2.5*lbent/c0", "\u4eff\u771f\u7ed3\u675f\u65f6\u95f4");
    model.param("par2").set("Lprop", "c0*Tend", "Tend \u65f6\u7684\u4f20\u64ad\u957f\u5ea6");
    model.param("par2").set("lbent", "L1+L3", "\u5230\u5f2f\u5934\u7684\u603b\u8ddd\u79bb");

    model.component("comp1").func().create("rect1", "Rectangle");
    model.component("comp1").func("rect1").set("lower", "0.5*T0");
    model.component("comp1").func("rect1").set("upper", "3*T0");
    model.component("comp1").func("rect1").set("smooth", "T0");
    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").label("\u4f53\u79ef\u529b");
    model.component("comp1").func("an1").set("funcname", "volforc");
    model.component("comp1").func("an1").set("expr", "rect1(t[1/s])*sin(2*pi*f0*t)");
    model.component("comp1").func("an1").set("args", "t");
    model.component("comp1").func("an1").setIndex("argunit", "s", 0);
    model.component("comp1").func("an1").set("fununit", "N/m^3");
    model.component("comp1").func("an1").setIndex("plotargs", "3.5*T0", 0, 2);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u4e00\u7ef4\u7ba1\u9053");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(1, 2, 20, 37, 60);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u7ba1\u7aef");
    model.component("comp1").selection("sel2").geom(0);
    model.component("comp1").selection("sel2").set(1, 11, 38);

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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat2").label("Water, liquid 1");
    model.component("comp1").material("mat2").set("family", "water");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
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
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat3").label("Water, liquid 2");
    model.component("comp1").material("mat3").set("family", "water");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
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
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat3").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat3").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").selection().geom("geom1", 1);
    model.component("comp1").material("mat2").selection().named("sel1");
    model.component("comp1").material("mat3").selection().geom("geom1", 2);
    model.component("comp1").material("mat3").selection().set(1);

    model.component("comp1").physics().create("patd", "TransientPipeAcoustics", "geom1");
    model.component("comp1").physics().create("actd", "TransientPressureAcoustics", "geom1");
    model.component("comp1").physics().create("acbm", "BoundaryModeAcoustics", "geom1");
    model.component("comp1").physics().create("pafd", "FrequencyPipeAcoustics", "geom1");
    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics("patd").selection().named("sel1");
    model.component("comp1").physics("patd").prop("TransientSettings").set("fmax", "f0");
    model.component("comp1").physics("patd").feature("pipe1").set("shape", "Round");
    model.component("comp1").physics("patd").feature("pipe1").set("innerd", "Di");
    model.component("comp1").physics("patd").create("pipe2", "PipeProperties", 1);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("patd").feature("pipe2").selection().set(20);
    model.component("comp1").physics("patd").feature("pipe2").set("shape", "Round");
    model.component("comp1").physics("patd").feature("pipe2").set("innerd", "Di*2/3");
    model.component("comp1").physics("patd").create("endimp1", "EndImpedance", 0);
    model.component("comp1").physics("patd").feature("endimp1").selection().named("sel2");
    model.component("comp1").physics("patd").create("vf1", "VolumeForce", 1);
    model.component("comp1").physics("patd").feature("vf1").selection().set(1);
    model.component("comp1").physics("patd").feature("vf1").set("F", new String[]{"volforc(t)", "0", "0"});
    model.component("comp1").physics("actd").prop("TransientSettings").set("fmax", "f0");
    model.component("comp1").physics("acbm").selection().set(1);
    model.component("comp1").physics("pafd").selection().named("sel1");
    model.component("comp1").physics("pafd").feature("pipe1").set("shape", "Round");
    model.component("comp1").physics("pafd").feature("pipe1").set("innerd", "Di");
    model.component("comp1").physics("pafd").create("pipe2", "PipeProperties", 1);
    model.component("comp1").physics("pafd").feature("pipe2").selection().set(20);
    model.component("comp1").physics("pafd").feature("pipe2").set("shape", "Round");
    model.component("comp1").physics("pafd").feature("pipe2").set("innerd", "Di*2/3");
    model.component("comp1").physics("pafd").create("endimp1", "EndImpedance", 0);
    model.component("comp1").physics("pafd").feature("endimp1").selection().named("sel2");
    model.component("comp1").physics("pafd").create("vf1", "VolumeForce", 1);
    model.component("comp1").physics("pafd").feature("vf1").selection().set(1);
    model.component("comp1").physics("pafd").feature("vf1").set("F", new int[]{1, 0, 0});

    model.component("comp1").multiphysics().create("apc1", "AcousticPipeAcousticConnection", -1);
    model.component("comp1").multiphysics().create("apc2", "AcousticPipeAcousticConnection", -1);
    model.component("comp1").multiphysics("apc2").set("Acoustics_physics", "acpr");
    model.component("comp1").multiphysics("apc2").set("Pipe_physics", "pafd");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "min(lam0/12,Di/2)");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "min(lam0/24,Di/6)");
    model.component("comp1").mesh("mesh1").run("size");
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "Di/12");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", "Di/12");
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/patd", true);
    model.study("std1").feature("time").setSolveFor("/physics/actd", true);
    model.study("std1").feature("time").setSolveFor("/physics/acbm", false);
    model.study("std1").feature("time").setSolveFor("/physics/pafd", false);
    model.study("std1").feature("time").setSolveFor("/physics/acpr", false);
    model.study("std1").feature("time").setSolveFor("/multiphysics/apc1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/apc2", false);
    model.study("std1").feature("time").set("tlist", "range(0,T0/24,Tend)");
    model.study("std1").setGenPlots(false);
    model.study("std1").label("\u7814\u7a76 1 - \u7ba1\u9053\u7cfb\u7edf\u77ac\u6001");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("mode", "ModeAnalysis");
    model.study("std2").feature("mode").set("ftplistmethod", "manual");
    model.study("std2").feature("mode").set("chkeigregion", true);
    model.study("std2").feature("mode").set("storefact", false);
    model.study("std2").feature("mode").set("linpsolnum", "auto");
    model.study("std2").feature("mode").set("solnum", "auto");
    model.study("std2").feature("mode").set("notsolnum", "auto");
    model.study("std2").feature("mode").set("outputmap", new String[]{});
    model.study("std2").feature("mode").set("ngenAUX", "1");
    model.study("std2").feature("mode").set("goalngenAUX", "1");
    model.study("std2").feature("mode").set("ngenAUX", "1");
    model.study("std2").feature("mode").set("goalngenAUX", "1");
    model.study("std2").feature("mode").setSolveFor("/physics/patd", false);
    model.study("std2").feature("mode").setSolveFor("/physics/actd", false);
    model.study("std2").feature("mode").setSolveFor("/physics/acbm", true);
    model.study("std2").feature("mode").setSolveFor("/physics/pafd", false);
    model.study("std2").feature("mode").setSolveFor("/physics/acpr", false);
    model.study("std2").feature("mode").setSolveFor("/multiphysics/apc1", false);
    model.study("std2").feature("mode").setSolveFor("/multiphysics/apc2", false);
    model.study("std2").label("\u7814\u7a76 2 - \u6253\u5f00/\u5173\u95ed\u6a21\u5f0f\u5206\u6790");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("mode").set("modeFreq", "f0");
    model.study("std2").feature("mode").set("neigsactive", true);
    model.study("std2").feature("mode").set("neigs", 10);
    model.study("std2").feature("mode").set("shiftactive", true);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/patd", false);
    model.study("std3").feature("freq").setSolveFor("/physics/actd", false);
    model.study("std3").feature("freq").setSolveFor("/physics/acbm", false);
    model.study("std3").feature("freq").setSolveFor("/physics/pafd", true);
    model.study("std3").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std3").feature("freq").setSolveFor("/multiphysics/apc1", false);
    model.study("std3").feature("freq").setSolveFor("/multiphysics/apc2", true);
    model.study("std3").label("\u7814\u7a76 3 - \u7ba1\u9053\u7cfb\u7edf\u9891\u57df");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("freq").set("plist", "f0");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").set("data", "dset2");
    model.result().dataset("surf1").selection().set(1);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u58f0\u538b (patd)");
    model.result("pg1").setIndex("looplevel", 73, 0);
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u538b\u529b (Pa)");
    model.result("pg1").set("paramindicator", "Time= eval(t,s) s");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("rangecoloractive", true);
    model.result("pg1").feature("line1").set("rangecolormin", -0.005);
    model.result("pg1").feature("line1").set("rangecolormax", 0.005);
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "0.5*patd.dh");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("colortable", "Wave");
    model.result("pg1").feature("line1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("line1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("line1").feature("def1").set("expr", new String[]{"0", "0", "p"});
    model.result("pg1").feature("line1").feature("def1").set("descractive", true);
    model.result("pg1").feature("line1").feature("def1").set("descr", "20");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "p2");
    model.result("pg1").feature("surf1").set("inheritplot", "line1");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").set("expr", new String[]{"0", "0", "p2"});
    model.result("pg1").run();
    model.result("pg1").create("tlan1", "TableAnnotation");
    model.result("pg1").feature("tlan1").set("source", "localtable");
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "-L1+Lj/2", 0, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 0, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u70b9 A", 0, 3);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "-Lj/2", 1, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "-L2", 1, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 1, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u70b9 B", 1, 3);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "L3", 2, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "-L4", 2, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 2, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u70b9 C", 2, 3);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 145, 0);
    model.result("pg1").setIndex("looplevel", 217, 0);
    model.result("pg1").setIndex("looplevel", 289, 0);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u70b9\u4e0a\u7684\u58f0\u538b");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u5404\u70b9\u538b\u529b (Pa)");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u538b\u529b (Pa)");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").label("A \u70b9");
    model.result("pg2").feature("ptgr1").selection().set(2);
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("autoplotlabel", true);
    model.result("pg2").feature("ptgr1").set("autopoint", false);
    model.result("pg2").feature("ptgr1").set("autosolution", false);
    model.result("pg2").run();
    model.result("pg2").create("ptgr2", "PointGraph");
    model.result("pg2").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr2").set("linewidth", "preference");
    model.result("pg2").feature("ptgr2").label("B \u70b9");
    model.result("pg2").feature("ptgr2").selection().set(11);
    model.result("pg2").feature("ptgr2").set("expr", "p+1[Pa]");
    model.result("pg2").feature("ptgr2").set("legend", true);
    model.result("pg2").feature("ptgr2").set("autoplotlabel", true);
    model.result("pg2").feature("ptgr2").set("autopoint", false);
    model.result("pg2").feature("ptgr2").set("autosolution", false);
    model.result("pg2").feature("ptgr2").set("legendsuffix", "\uff08\u504f\u79fb 1 Pa\uff09");
    model.result("pg2").run();
    model.result("pg2").create("ptgr3", "PointGraph");
    model.result("pg2").feature("ptgr3").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr3").set("linewidth", "preference");
    model.result("pg2").feature("ptgr3").label("C \u70b9");
    model.result("pg2").feature("ptgr3").selection().set(38);
    model.result("pg2").feature("ptgr3").set("expr", "p+2[Pa]");
    model.result("pg2").feature("ptgr3").set("legend", true);
    model.result("pg2").feature("ptgr3").set("autoplotlabel", true);
    model.result("pg2").feature("ptgr3").set("autopoint", false);
    model.result("pg2").feature("ptgr3").set("autosolution", false);
    model.result("pg2").feature("ptgr3").set("legendsuffix", "\uff08\u504f\u79fb 2 Pa\uff09");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u6a21\u5f0f\u5206\u6790\u538b\u529b (acbm)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "p3");
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u58f0\u538b (pafd)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u538b\u529b (Pa)");
    model.result("pg4").set("paramindicator", "freq =eval(freq,Hz) Hz");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", "p4");
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("radiusexpr", "0.5*pafd.dh");
    model.result("pg4").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg4").feature("line1").set("colortable", "Wave");
    model.result("pg4").feature("line1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").feature("line1").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("line1").feature("def1").set("expr", new String[]{"0", "0", "p4"});
    model.result("pg4").feature("line1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("line1").feature("def1").set("scale", 2);
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "p5");
    model.result("pg4").feature("surf1").set("inheritplot", "line1");
    model.result("pg4").feature("surf1").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def1").set("expr", new String[]{"0", "0", "p5"});
    model.result("pg4").run();
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{6});
    model.result("pg3").run();
    model.result("pg4").run();

    model.title("\u5e26\u5f2f\u5934\u548c\u63a5\u5934\u7684\u7ba1\u9053\u7cfb\u7edf\u58f0\u5b66 - \u4e09\u7ef4");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u901a\u8fc7\u8026\u5408\u7ba1\u9053\u58f0\u5b66 \u63a5\u53e3\u4e0e\u538b\u529b\u58f0\u5b66 \u63a5\u53e3\uff0c\u6a21\u62df\u58f0\u6ce2\u5728\u5927\u578b\u7ba1\u9053\u7cfb\u7edf\u4e2d\u7684\u4f20\u64ad\uff0c\u5728\u65f6\u57df\u548c\u9891\u57df\u4e2d\u5efa\u7acb\u6a21\u578b\u3002\n\n\u7ba1\u9053\u58f0\u5b66\u4e00\u7ef4\u6a21\u578b\u7528\u4e8e\u6a21\u62df\u58f0\u6ce2\u5728\u957f\u76f4\u7ba1\u6bb5\u4e2d\u7684\u4f20\u64ad\u3002\u672c\u4f8b\u901a\u8fc7\u5c06\u7ba1\u9053\u63a5\u5934\u548c\u7ba1\u9053\u5f2f\u5934\u7684\u4e09\u7ef4\u6a21\u578b\u4e0e\u4e00\u7ef4\u7ba1\u6a21\u578b\u76f8\u8026\u5408\uff0c\u5bf9\u8fd9\u4e9b\u96f6\u4ef6\u8fdb\u884c\u8be6\u7ec6\u5efa\u6a21\u3002\n\n\u8fd9\u79cd\u6a21\u578b\u4e0d\u4ec5\u53ef\u7528\u4e8e\u6a21\u62df\u548c\u9884\u6d4b\u7ba1\u9053\u7cfb\u7edf\u7684\u54cd\u5e94\uff08\u4f8b\u5982\u5728\u68c0\u6d4b\u5230\u6cc4\u6f0f\u6216\u53d8\u5f62\u65f6\u7684\u54cd\u5e94\uff09\uff0c\u4e5f\u9002\u7528\u4e8e\u77f3\u6cb9\u548c\u5929\u7136\u6c14\u5de5\u4e1a\u4ee5\u53ca\u7ba1\u9053\u8f93\u6c34\u7cfb\u7edf\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("acoustics_pipe_system.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
