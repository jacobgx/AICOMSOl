/*
 * pb_flow_battery.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:19 by COMSOL 6.3.0.290. */
public class pb_flow_battery {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Flow_Batteries");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"12[mm]", "10[cm]"});
    model.component("comp1").geom("geom1").runPre("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param()
         .set("D_PbII", "0.7e-9[m^2/s]", "\u7535\u89e3\u8d28\u4e2d\u7684 Pb \u79bb\u5b50\u6269\u6563\u7cfb\u6570");
    model.param().set("D_H", "9.3e-9[m^2/s]", "\u7535\u89e3\u8d28\u4e2d\u7684\u8d28\u5b50\u6269\u6563\u7cfb\u6570");
    model.param().set("D_HSO4", "1.33e-9[m^2/s]", "\u7535\u89e3\u8d28\u4e2d\u7684 HSO4- \u6269\u6563\u7cfb\u6570");
    model.param().set("T", "300[K]", "\u6e29\u5ea6");
    model.param().set("c0_PbII", "500[mol/m^3]", "\u521d\u59cb Pb \u79bb\u5b50\u6d53\u5ea6");
    model.param().set("c0_H", "500[mol/m^3]", "\u521d\u59cb\u8d28\u5b50\u6d53\u5ea6");
    model.param().set("p_out", "300[kPa]", "\u51fa\u53e3\u538b\u529b");
    model.param().set("U_in", "0.023[m/s]", "\u5165\u53e3\u901f\u5ea6");
    model.param().set("i_app", "200[A/m^2]", "\u5916\u52a0\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("V", "1.5e-3[m^3]", "\u69fd\u4e2d\u7535\u89e3\u8d28\u7684\u6d41\u52a8\u4f53\u79ef");
    model.param().set("k0_Pb", "2.1e-7[m/s]", "\u8d1f\u6781\u53cd\u5e94\u7684\u901f\u7387\u5e38\u6570");
    model.param().set("k0_PbO2", "2.5e-7[m/s]", "\u6b63\u6781\u4e3b\u53cd\u5e94\u7684\u901f\u7387\u5e38\u6570");
    model.param().set("K0f", "K0b*100000", "\u6b63\u53cd\u5e94\u901f\u7387\u5e38\u6570\uff0cPbO \u53cd\u5e94");
    model.param()
         .set("K0b", "4.5e-7[mol/m^2/s]/10", "\u9006\u53cd\u5e94\u901f\u7387\u5e38\u6570\uff0cPbO \u53cd\u5e94");
    model.param().set("t_charge", "3600[s]", "\u5145\u7535\u65f6\u95f4");
    model.param().set("t_discharge", "3600[s]", "\u653e\u7535\u65f6\u95f4");
    model.param().set("t_rest", "60[s]", "\u9759\u7f6e\u65f6\u95f4");
    model.param().set("L", "0.1[m]", "\u7535\u6c60\u539a\u5ea6");
    model.param()
         .set("E0_pos", "1.8-R_const*T/(2*F_const)*(log(max(eps^2,c0_PbII/(1000[mol/m^3])))-4*log(max(eps^2,c0_H/(1000[mol/m^3]))))", "\u521d\u59cb\u6b63\u6781\u7535\u4f4d");
    model.param()
         .set("E0_neg", "0+R_const*T/(2*F_const)*log(max(eps^2,c0_PbII/(1000[mol/m^3])))", "\u521d\u59cb\u8d1f\u6781\u7535\u4f4d");
    model.param()
         .set("i0ref_neg", "F_const*k0_Pb*1[mol/l]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u8d1f\u6781\u53cd\u5e94");
    model.param()
         .set("i0ref_pos", "F_const*k0_PbO2*1[mol/l]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6b63\u4e3b\u7535\u6781\u53cd\u5e94");
    model.param().set("Eeq_pos", "1.8[V]", "\u5e73\u8861\u7535\u4f4d\uff0c\u6b63\u4e3b\u7535\u6781\u53cd\u5e94");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u5165\u53e3");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(2);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u51fa\u53e3");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(3);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u6b63\u6781");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(4);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u8d1f\u6781");
    model.component("comp1").selection("sel4").geom(1);
    model.component("comp1").selection("sel4").set(1);

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

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().named("sel1");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "U_in");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().named("sel2");
    model.component("comp1").physics("spf").feature("out1").set("NormalFlow", true);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 30);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").create("edg2", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg2").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("edg2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "5e-5");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
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
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("xnumber", 5);
    model.result("pg2").feature("arws1").set("color", "black");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").label("\u6d41\u52a8");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/tcd", false);

    model.component("comp1").physics("tcd").field("concentration").field("cPbII");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"cPbII", "cH", "cHSO4"});
    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ge", false);

    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/spf", true);
    model.study("std2").feature("time").setSolveFor("/physics/tcd", true);
    model.study("std2").feature("time").setSolveFor("/physics/ge", true);

    model.component("comp1").func().create("rect1", "Rectangle");
    model.component("comp1").func("rect1").set("funcname", "charge1");
    model.component("comp1").func("rect1").set("lower", 0);
    model.component("comp1").func("rect1").set("upper", "t_charge");
    model.component("comp1").func("rect1").set("smooth", 1);
    model.component("comp1").func().create("rect2", "Rectangle");
    model.component("comp1").func("rect2").set("funcname", "discharge1");
    model.component("comp1").func("rect2").set("lower", "t_charge+t_rest");
    model.component("comp1").func("rect2").set("upper", "t_charge+t_rest+t_discharge");
    model.component("comp1").func("rect2").set("smooth", 1);
    model.component("comp1").func().create("rect3", "Rectangle");
    model.component("comp1").func("rect3").set("funcname", "charge2");
    model.component("comp1").func("rect3").set("lower", "t_charge+t_rest+t_discharge+t_rest");
    model.component("comp1").func("rect3").set("upper", "2*t_charge+t_rest+t_discharge+t_rest");
    model.component("comp1").func("rect3").set("smooth", 1);

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("i_cycle", "i_app*(charge1(t[1/s])-discharge1(t[1/s])+charge2(t[1/s]))", "\u5916\u52a0\u8d1f\u8f7d\u5faa\u73af\u7535\u6d41");
    model.component("comp1").variable("var1")
         .set("i_PbO", "F_const*(K0f*max(eps,tcd.c_es2_PbO/1[mol/m^2])^2*exp(F_const*(tcd.phisext-phil-tcd.Eeq_er1)/(R_const*T))-cH[mol^-1*m^3]*max(eps,tcd.c_es2_PbO2/1[mol/m^2])*K0b*exp(-F_const*(tcd.phisext-phil-tcd.Eeq_er1)/(R_const*T)))", "\u7535\u6d41\u5bc6\u5ea6\u8868\u8fbe\u5f0f\uff0cPbO \u53cd\u5e94");

    model.component("comp1").physics("tcd").prop("SpeciesProperties").set("FromElectroneutrality", 3);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 2, 0);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 1, 1);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", -1, 2);
    model.component("comp1").physics("tcd").feature("ice1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cPbII", new String[]{"D_PbII", "0", "0", "0", "D_PbII", "0", "0", "0", "D_PbII"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cH", new String[]{"D_H", "0", "0", "0", "D_H", "0", "0", "0", "D_H"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cHSO4", new String[]{"D_HSO4", "0", "0", "0", "D_HSO4", "0", "0", "0", "D_HSO4"});
    model.component("comp1").physics("tcd").create("in1", "Inflow", 1);
    model.component("comp1").physics("tcd").feature("in1").selection().named("sel1");
    model.component("comp1").physics("tcd").feature("in1").setIndex("c0", "cPbII_in", 0);
    model.component("comp1").physics("tcd").feature("in1").setIndex("c0", "cH_in", 1);
    model.component("comp1").physics("tcd").feature("in1").set("BoundaryConditionType", "FluxDanckwerts");
    model.component("comp1").physics("tcd").create("out1", "Outflow", 1);
    model.component("comp1").physics("tcd").feature("out1").selection().named("sel2");
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 1);
    model.component("comp1").physics("tcd").feature("es1").label("\u8d1f\u6781");
    model.component("comp1").physics("tcd").feature("es1").selection().named("sel4");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("nm", 2);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0Type", "LumpedMultistep");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0_ref", "i0ref_neg");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("rgeneric", 1, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("alphaa", 1);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("alphac", 1);
    model.component("comp1").physics("tcd").create("es2", "ElectrodeSurface", 1);
    model.component("comp1").physics("tcd").feature("es2").label("\u6b63\u6781");
    model.component("comp1").physics("tcd").feature("es2").selection().named("sel3");
    model.component("comp1").physics("tcd").feature("es2").set("BoundaryCondition", "AverageCurrentDensity");
    model.component("comp1").physics("tcd").feature("es2").set("Ial", "i_cycle");
    model.component("comp1").physics("tcd").feature("es2").set("phisext0init", "E0_pos");
    model.component("comp1").physics("tcd").feature("es2").feature("er1").set("nm", 2);
    model.component("comp1").physics("tcd").feature("es2").feature("er1").setIndex("Vi0", 1, 0);
    model.component("comp1").physics("tcd").feature("es2").feature("er1").setIndex("Vi0", -4, 1);
    model.component("comp1").physics("tcd").feature("es2").feature("er1").set("Eeq_ref", "Eeq_pos");
    model.component("comp1").physics("tcd").feature("es2").feature("er1").setIndex("cref", "0.5[M]", 1, 0);
    model.component("comp1").physics("tcd").feature("es2").feature("er1").set("i0Type", "LumpedMultistep");
    model.component("comp1").physics("tcd").feature("es2").feature("er1").set("i0_ref", "i0ref_pos");
    model.component("comp1").physics("tcd").feature("es2").feature("er1").setIndex("rgeneric", 1, 0, 0);
    model.component("comp1").physics("tcd").feature("es2").feature("er1").setIndex("rgeneric", 1, 1, 0);
    model.component("comp1").physics("tcd").feature("es2").create("er2", "ElectrodeReaction", 1);
    model.component("comp1").physics("tcd").feature("es2").feature("er1").label("\u4e3b\u53cd\u5e94");
    model.component("comp1").physics("tcd").feature("es2").feature("er2").label("\u526f\u53cd\u5e94");
    model.component("comp1").physics("tcd").feature("es2").feature("er2").set("nm", 2);
    model.component("comp1").physics("tcd").feature("es2").feature("er2").setIndex("Vi0", -2, 1);
    model.component("comp1").physics("tcd").feature("es2").feature("er2").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd").feature("es2").feature("er2").set("ilocmat_mat", "userdef");
    model.component("comp1").physics("tcd").feature("es2").feature("er2").set("ilocmat", "i_PbO");
    model.component("comp1").physics("tcd").feature("es2").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("Species", "s2", 1, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("Species", "s2", 1, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("Species", "PbO2", 0, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("rhos", "9.38[g/cm^3]", 0, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("Ms", 0.2392, 0, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("Species", "PbO", 1, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("rhos", "9.53[g/cm^3]", 1, 0);
    model.component("comp1").physics("tcd").feature("es2").setIndex("Ms", 0.2232, 1, 0);
    model.component("comp1").physics("tcd").feature("es2").feature("er1").setIndex("Vib", -1, 0, 0);
    model.component("comp1").physics("tcd").feature("es2").feature("er2").setIndex("Vib", -1, 0, 0);
    model.component("comp1").physics("tcd").feature("es2").feature("er2").setIndex("Vib", 1, 1, 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c0_PbII", 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c0_H", 1);
    model.component("comp1").physics("tcd").feature("init1").set("initphil", "-E0_neg");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "int_inlet");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().named("sel1");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "int_outlet");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().named("sel2");

    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "cPbII_in", 0, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "cPbII_int-L/V*(comp1.int_outlet(comp1.tcd.tflux_cPbIIy)-comp1.int_inlet(comp1.tcd.tflux_cPbIIy))", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", "c0_PbII", 0, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "Pb \u79bb\u5b50\u5165\u53e3\u6d53\u5ea6", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "cH_in", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", 0, 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueUt", 0, 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "", 1, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "cH_int-L/V*(comp1.int_outlet(comp1.tcd.tflux_cHy)-comp1.int_inlet(comp1.tcd.tflux_cHy))", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", "c0_H", 1, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("description", "\u8d28\u5b50\u5165\u53e3\u6d53\u5ea6", 1, 0);
    model.component("comp1").physics("ge").feature("ge1").set("DependentVariableQuantity", "concentration");
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "reactionrate");

    model.component("comp1").probe().create("bnd1", "Boundary");
    model.component("comp1").probe("bnd1").set("intsurface", true);
    model.component("comp1").probe("bnd1").selection().named("sel3");
    model.component("comp1").probe("bnd1").set("expr", "tcd.phisext");
    model.component("comp1").probe().create("bnd2", "Boundary");
    model.component("comp1").probe("bnd2").set("intsurface", true);
    model.component("comp1").probe("bnd2").selection().named("sel3");
    model.component("comp1").probe("bnd2").set("expr", "tcd.iloc_er1");
    model.component("comp1").probe().create("bnd3", "Boundary");
    model.component("comp1").probe("bnd3").set("intsurface", true);
    model.component("comp1").probe("bnd3").selection().named("sel3");
    model.component("comp1").probe("bnd3").set("expr", "tcd.iloc_er2");
    model.component("comp1").probe().create("bnd4", "Boundary");
    model.component("comp1").probe("bnd4").set("intsurface", true);
    model.component("comp1").probe("bnd4").selection().named("sel4");
    model.component("comp1").probe("bnd4").set("expr", "tcd.iloc_er1");
    model.component("comp1").probe().create("bnd5", "Boundary");
    model.component("comp1").probe("bnd5").set("intsurface", true);
    model.component("comp1").probe("bnd5").selection().named("sel3");
    model.component("comp1").probe("bnd5").set("expr", "tcd.c_es2_PbO2");
    model.component("comp1").probe().create("bnd6", "Boundary");
    model.component("comp1").probe("bnd6").set("intsurface", true);
    model.component("comp1").probe("bnd6").selection().named("sel3");
    model.component("comp1").probe("bnd6").set("expr", "tcd.c_es2_PbO");

    model.study("std2").feature("time").set("tlist", "range(0,600,3600) range(3660,600,7260) range(7320,600,10900)");
    model.study("std2").feature("time").setSolveFor("/physics/spf", false);
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").feature("time").set("notsolnum", 1);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").feature("comp1_cH").set("scalemethod", "init");

    return model;
  }

  public static Model run2(Model model) {
    model.sol("sol2").feature("v1").feature("comp1_cPbII").set("scalemethod", "init");
    model.sol("sol2").feature("v1").feature("comp1_tcd_es2_c").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_tcd_es2_c").set("scaleval", "c0_PbII*V/(L*1[m^2])");
    model.sol("sol2").feature("v1").feature("comp1_ODE1").set("scalemethod", "init");
    model.sol("sol2").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol2").feature("t1").set("initialstepbdf", 0.01);

    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("bnd1").genResult("none");
    model.component("comp1").probe("bnd2").genResult("none");
    model.component("comp1").probe("bnd3").genResult("none");
    model.component("comp1").probe("bnd4").genResult("none");
    model.component("comp1").probe("bnd5").genResult("none");
    model.component("comp1").probe("bnd6").genResult("none");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("unit", new String[]{""});
    model.result("pg4").feature("glob1").set("expr", new String[]{"tcd.phisext_es2"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u5916\u90e8\u7535\u4f4d"});
    model.result("pg4").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (tcd)");
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 20, 0);
    model.result("pg5").label("\u7535\u89e3\u8d28\u7535\u4f4d (tcd)");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"phil"});
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("recover", "pprint");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 20, 0);
    model.result("pg6").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (tcd)");
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("recover", "pprint");
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("str1").set("color", "gray");
    model.result("pg6").feature("str1").create("col1", "Color");
    model.result("pg6").feature("str1").feature("col1").set("expr", "root.comp1.tcd.IlMag");
    model.result("pg6").create("line1", "Line");
    model.result("pg6").feature("line1").set("expr", new String[]{"abs(tcd.itot)"});
    model.result("pg6").feature("line1").set("linetype", "tube");
    model.result("pg6").feature("line1").set("inherittubescale", false);
    model.result("pg6").feature("line1").set("inheritplot", "str1");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 20, 0);
    model.result("pg7").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (tcd)");
    model.result("pg7").create("line1", "Line");
    model.result("pg7").feature("line1").set("expr", new String[]{"tcd.phisext"});
    model.result("pg7").feature("line1").set("linetype", "tube");
    model.result("pg7").feature("line1").set("inherittubescale", false);
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").setIndex("looplevel", 20, 0);
    model.result("pg8").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (tcd)");
    model.result("pg8").create("str1", "Streamline");
    model.result("pg8").feature("str1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg8").feature("str1").set("posmethod", "uniform");
    model.result("pg8").feature("str1").set("recover", "pprint");
    model.result("pg8").feature("str1").set("pointtype", "arrow");
    model.result("pg8").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg8").feature("str1").set("color", "gray");
    model.result("pg8").create("line1", "Line");
    model.result("pg8").feature("line1").set("expr", new String[]{"tcd.Evsref"});
    model.result("pg8").feature("line1").set("linetype", "tube");
    model.result("pg8").feature("line1").set("inherittubescale", false);
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").setIndex("looplevel", 20, 0);
    model.result("pg9").label("\u7535\u6781\u603b\u539a\u5ea6\u53d8\u5316 (tcd)");
    model.result("pg9").create("line1", "Line");
    model.result("pg9").feature("line1").set("expr", new String[]{"tcd.sbtot"});
    model.result("pg9").feature("line1").set("linetype", "tube");
    model.result("pg9").feature("line1").set("inherittubescale", false);
    model.result("pg9").feature("line1").set("unit", "\u00b5m");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").setIndex("looplevel", 20, 0);
    model.result("pg10").label("\u6d53\u5ea6, PbII (tcd)");
    model.result("pg10").set("titletype", "custom");
    model.result("pg10").set("prefixintitle", "");
    model.result("pg10").set("expressionintitle", false);
    model.result("pg10").set("typeintitle", false);
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", new String[]{"cPbII"});
    model.result("pg10").feature("surf1").set("colortable", "Rainbow");
    model.result("pg10").set("typeintitle", true);
    model.result("pg10").create("str1", "Streamline");
    model.result("pg10").feature("str1").set("expr", new String[]{"tcd.tflux_cPbIIx", "tcd.tflux_cPbIIy"});
    model.result("pg10").feature("str1").set("posmethod", "uniform");
    model.result("pg10").feature("str1").set("recover", "pprint");
    model.result("pg10").feature("str1").set("pointtype", "arrow");
    model.result("pg10").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg10").feature("str1").set("color", "gray");
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").set("data", "dset2");
    model.result("pg11").setIndex("looplevel", 20, 0);
    model.result("pg11").label("\u6d53\u5ea6, H (tcd)");
    model.result("pg11").set("titletype", "custom");
    model.result("pg11").set("prefixintitle", "");
    model.result("pg11").set("expressionintitle", false);
    model.result("pg11").set("typeintitle", false);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", new String[]{"cH"});
    model.result("pg11").feature("surf1").set("colortable", "Rainbow");
    model.result("pg11").set("typeintitle", true);
    model.result("pg11").create("str1", "Streamline");
    model.result("pg11").feature("str1").set("expr", new String[]{"tcd.tflux_cHx", "tcd.tflux_cHy"});
    model.result("pg11").feature("str1").set("posmethod", "uniform");
    model.result("pg11").feature("str1").set("recover", "pprint");
    model.result("pg11").feature("str1").set("pointtype", "arrow");
    model.result("pg11").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg11").feature("str1").set("color", "gray");
    model.result().create("pg12", "PlotGroup2D");
    model.result("pg12").set("data", "dset2");
    model.result("pg12").setIndex("looplevel", 20, 0);
    model.result("pg12").label("\u6d53\u5ea6, HSO4 (tcd)");
    model.result("pg12").set("titletype", "custom");
    model.result("pg12").set("prefixintitle", "");
    model.result("pg12").set("expressionintitle", false);
    model.result("pg12").set("typeintitle", false);
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", new String[]{"cHSO4"});
    model.result("pg12").feature("surf1").set("colortable", "Rainbow");
    model.result("pg12").set("typeintitle", true);
    model.result("pg12").create("str1", "Streamline");
    model.result("pg12").feature("str1").set("expr", new String[]{"tcd.tflux_cHSO4x", "tcd.tflux_cHSO4y"});
    model.result("pg12").feature("str1").set("posmethod", "uniform");
    model.result("pg12").feature("str1").set("recover", "pprint");
    model.result("pg12").feature("str1").set("pointtype", "arrow");
    model.result("pg12").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg12").feature("str1").set("color", "gray");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").set("expr", new String[]{"cPbII_in", "cH_in"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"Pb \u79bb\u5b50\u5165\u53e3\u6d53\u5ea6", "\u8d28\u5b50\u5165\u53e3\u6d53\u5ea6"});
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").set("data", "dset2");
    model.result("pg13").create("glob1", "Global");
    model.result("pg13").feature("glob1").set("expr", new String[]{"cPbII_in", "cH_in"});
    model.result("pg13").feature("glob1")
         .set("descr", new String[]{"Pb \u79bb\u5b50\u5165\u53e3\u6d53\u5ea6", "\u8d28\u5b50\u5165\u53e3\u6d53\u5ea6"});
    model.result("pg4").run();
    model.result("pg3").set("window", "window1");
    model.result("pg3").run();
    model.result("pg3").label("\u63a2\u9488\u503c");
    model.result().create("pg14", "PlotGroup1D");
    model.result("pg14").run();
    model.result("pg14").set("titletype", "manual");
    model.result("pg14").set("title", "\u7535\u6c60\u7535\u4f4d vs. \u65f6\u95f4");
    model.result("pg14").set("ylabelactive", true);
    model.result("pg14").set("ylabel", "\u7535\u538b (V)");
    model.result("pg14").create("tblp1", "Table");
    model.result("pg14").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg14").feature("tblp1").set("linewidth", "preference");
    model.result("pg14").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg14").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg14").run();
    model.result("pg14").label("\u7535\u6c60\u7535\u4f4d");
    model.result("pg14").run();
    model.result().create("pg15", "PlotGroup1D");
    model.result("pg15").run();
    model.result("pg15").set("titletype", "manual");
    model.result("pg15").set("title", "\u5e73\u5747\u5c40\u90e8\u53cd\u5e94\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg15").set("ylabelactive", true);
    model.result("pg15").set("ylabel", "\u7535\u6d41\u5bc6\u5ea6 (A/m<sup>2</sup>)");
    model.result("pg15").set("legendpos", "lowerright");
    model.result("pg15").create("tblp1", "Table");
    model.result("pg15").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg15").feature("tblp1").set("linewidth", "preference");
    model.result("pg15").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg15").feature("tblp1").set("plotcolumns", new int[]{3, 4, 5});
    model.result("pg15").feature("tblp1").set("legend", true);
    model.result("pg15").feature("tblp1").set("legendmethod", "manual");
    model.result("pg15").feature("tblp1").setIndex("legends", "PbO2 \u5f62\u6210", 0);
    model.result("pg15").feature("tblp1").setIndex("legends", "PbO \u5f62\u6210", 1);
    model.result("pg15").feature("tblp1").setIndex("legends", "Pb \u5f62\u6210", 2);
    model.result("pg15").run();
    model.result("pg15").run();
    model.result("pg15").label("\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result().create("pg16", "PlotGroup1D");
    model.result("pg16").run();
    model.result("pg16").set("titletype", "manual");
    model.result("pg16").set("title", "\u5e73\u5747\u8868\u9762\u6d53\u5ea6");
    model.result("pg16").set("xlabelactive", true);
    model.result("pg16").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg16").set("ylabelactive", true);
    model.result("pg16").set("ylabel", "\u7535\u6781\u8868\u9762\u6d53\u5ea6 (mol/m<sup>2</sup>)");
    model.result("pg16").create("tblp1", "Table");
    model.result("pg16").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg16").feature("tblp1").set("linewidth", "preference");
    model.result("pg16").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg16").feature("tblp1").set("plotcolumns", new int[]{6, 7});
    model.result("pg16").feature("tblp1").set("legend", true);
    model.result("pg16").feature("tblp1").set("legendmethod", "manual");
    model.result("pg16").feature("tblp1").setIndex("legends", "PbO2", 0);
    model.result("pg16").feature("tblp1").setIndex("legends", "PbO", 1);
    model.result("pg16").run();
    model.result("pg16").run();
    model.result("pg16").label("\u8868\u9762\u6d53\u5ea6");
    model.result("pg13").run();
    model.result("pg13").label("\u7535\u89e3\u8d28\u6d53\u5ea6");
    model.result("pg13").set("titletype", "manual");
    model.result("pg13").set("title", "\u7535\u6c60\u5165\u53e3\u6d53\u5ea6");
    model.result("pg13").set("xlabelactive", true);
    model.result("pg13").set("ylabelactive", true);
    model.result("pg13").set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result().create("pg17", "PlotGroup2D");
    model.result("pg17").run();
    model.result("pg17").set("data", "dset2");
    model.result("pg17").setIndex("looplevel", 7, 0);
    model.result("pg17").create("surf1", "Surface");
    model.result("pg17").feature("surf1").set("expr", "cH");
    model.result("pg17").feature("surf1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0ccH");
    model.result("pg17").feature("surf1").create("hght1", "Height");
    model.result("pg17").run();
    model.result("pg17").run();
    model.result("pg17").run();
    model.result("pg17").label("H+ \u6d53\u5ea6\u5206\u5e03");
    model.result("pg17").setIndex("looplevel", 14, 0);
    model.result("pg17").run();
    model.result().duplicate("pg18", "pg17");
    model.result("pg18").run();
    model.result("pg18").setIndex("looplevel", 7, 0);
    model.result("pg18").run();
    model.result("pg18").feature("surf1").set("expr", "cPbII");
    model.result("pg18").feature("surf1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0ccPbII");
    model.result("pg18").run();
    model.result("pg18").run();
    model.result("pg18").label("PbII \u6d53\u5ea6\u5206\u5e03");
    model.result("pg18").setIndex("looplevel", 14, 0);
    model.result("pg18").run();
    model.result().create("pg19", "PlotGroup1D");
    model.result("pg19").run();
    model.result("pg19").label("PbO \u8868\u9762\u6d53\u5ea6");
    model.result("pg19").create("lngr1", "LineGraph");
    model.result("pg19").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg19").feature("lngr1").set("linewidth", "preference");
    model.result("pg19").feature("lngr1").set("expr", "tcd.c_es2_PbO");
    model.result("pg19").feature("lngr1").set("data", "dset2");
    model.result("pg19").feature("lngr1").selection().named("sel3");

    model.title("\u53ef\u6eb6\u6027\u94c5\u9178\u6c27\u5316\u8fd8\u539f\u6db2\u6d41\u7535\u6c60");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u6a21\u62df\u8d1f\u8f7d\u5faa\u73af\u671f\u95f4\u7684\u5145\u653e\u7535\u7684\u6c27\u5316\u8fd8\u539f\u6db2\u6d41\u7535\u6c60\u793a\u4f8b\uff0c\u5176\u4e2d\u7535\u5316\u5b66\u6c60\u91c7\u7528\u4e8c\u7ef4\u6a21\u578b\uff0c\u5305\u542b\u8d28\u91cf\u3001\u7535\u8377\u548c\u52a8\u91cf\u4f20\u9012\u3002\u7535\u6c60\u69fd\u91c7\u7528\u96f6\u7ef4\u6a21\u578b\uff0c\u7528\u6765\u6a21\u62df\u7535\u6c60\u5916\u90e8\u7535\u89e3\u8d28\u4e2d\u7684\u53cd\u5e94\u7269\u6d53\u5ea6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("pb_flow_battery.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
