/*
 * geothermal_doublet.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:30 by COMSOL 6.3.0.290. */
public class geothermal_doublet {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics().create("ht", "PorousMediaHeatTransfer", "geom1");

    model.component("comp1").geom("geom1").insertFile("geothermal_doublet_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("clf1");

    model.component("comp1").view("view1").set("transparency", false);

    model.param().set("r_bore", "1[m]");
    model.param().descr("r_bore", "\u4e95\u773c\u8868\u5c42\u534a\u5f84");
    model.param().set("pump", "120[l/s]");
    model.param().descr("pump", "\u6cf5\u9001\u7387");
    model.param().set("deltaH", "1[mm/m]");
    model.param().descr("deltaH", "\u6c34\u5934\u68af\u5ea6");
    model.param().set("T_top", "283[K]");
    model.param().descr("T_top", "\u8868\u9762\u6e29\u5ea6");
    model.param().set("T_inj", "278[K]");
    model.param().descr("T_inj", "\u6ce8\u5c04\u6e29\u5ea6");
    model.param().set("delta_Tz", "0.03[K/m]");
    model.param().descr("delta_Tz", "\u5730\u70ed\u68af\u5ea6");
    model.param().set("d_f", "0.2[cm]");
    model.param().descr("d_f", "\u88c2\u9699\u539a\u5ea6");
    model.param().set("f_f", "1.6");
    model.param().descr("f_f", "\u88c2\u9699\u7c97\u7cd9\u5ea6\u7cfb\u6570");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection("sel1").set(19);
    model.component("comp1").selection("sel1").label("\u6ce8\u5c04\u4e95");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(37);
    model.component("comp1").selection("sel2").label("\u91c7\u51fa\u4e95");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").set(3, 6);
    model.component("comp1").selection("sel3").label("\u9876\u5c42");
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").set(2, 5);
    model.component("comp1").selection("sel4").label("\u4e2d\u95f4\u5c42");
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").set(1, 4);
    model.component("comp1").selection("sel5").label("\u5e95\u5c42");
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(21);
    model.component("comp1").selection("sel6").set("groupcontang", true);
    model.component("comp1").selection("sel6").label("\u88c2\u9699");
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u5916\u8fb9\u754c");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7").set("groupcontang", true);
    model.component("comp1").selection("sel7").add(1, 2, 4, 5, 7, 8, 11, 12, 13, 16, 17, 19, 22, 25, 26, 27, 28, 29);

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.material("mat1").label("Water, liquid");
    model.material("mat1").set("family", "water");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.material("mat1").propertyGroup("def").func("cs")
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
    model.material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an1").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.material("mat1").propertyGroup("def").func("an2").set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an2").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an3").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").label("\u4e0a\u90e8\u5f31\u900f\u6c34\u5c42");
    model.component("comp1").material().create("pmat2", "PorousMedia");
    model.component("comp1").material("pmat2").label("\u542b\u6c34\u5c42");
    model.component("comp1").material("pmat2").selection().named("sel4");
    model.component("comp1").material().create("pmat3", "PorousMedia");
    model.component("comp1").material("pmat3").selection().named("sel5");
    model.component("comp1").material("pmat3").label("\u4e0b\u90e8\u5f31\u900f\u6c34\u5c42");
    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").label("\u88c2\u9699");
    model.component("comp1").material("matlnk1").selection().geom("geom1", 2);
    model.component("comp1").material("matlnk1").selection().named("sel6");

    model.component("comp1").physics("dl").create("frac1", "Fracture", 2);
    model.component("comp1").physics("dl").feature("frac1").selection().named("sel6");
    model.component("comp1").physics("dl").feature("frac1").set("df", "d_f");
    model.component("comp1").physics("dl").feature("frac1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("frac1").feature("pm1").set("epsilon", 0.6);
    model.component("comp1").physics("dl").feature("frac1").feature("pm1").set("permeabilityModelType", "cubicLaw");
    model.component("comp1").physics("dl").feature("frac1").feature("pm1").set("ff", "f_f");
    model.component("comp1").physics("dl").create("hh1", "HydraulicHead", 2);
    model.component("comp1").physics("dl").feature("hh1").selection().named("sel7");
    model.component("comp1").physics("dl").feature("hh1").set("H0", "-deltaH*x");
    model.component("comp1").physics("dl").create("well1", "Well", 1);
    model.component("comp1").physics("dl").feature("well1").selection().named("sel1");
    model.component("comp1").physics("dl").feature("well1").set("dw", "2*r_bore");
    model.component("comp1").physics("dl").feature("well1").set("wellInputType", "MassFlow");
    model.component("comp1").physics("dl").feature("well1").set("M0", "pump*dl.rho");
    model.component("comp1").physics("dl").create("well2", "Well", 1);
    model.component("comp1").physics("dl").feature("well2").selection().named("sel2");
    model.component("comp1").physics("dl").feature("well2").set("dw", "2*r_bore");
    model.component("comp1").physics("dl").feature("well2").set("injectionProduction", "Production");
    model.component("comp1").physics("dl").feature("well2").set("wellInputType", "MassFlow");
    model.component("comp1").physics("dl").feature("well2").set("M0", "pump*dl.rho");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").set("u_src", "root.comp1.dl.u");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1")
         .set("porousMatrixPropertiesType", "solidPhaseProperties");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_top-0.03[K/m]*z");
    model.component("comp1").physics("ht").create("open1", "OpenBoundary", 2);
    model.component("comp1").physics("ht").feature("open1").selection().named("sel7");
    model.component("comp1").physics("ht").feature("open1").set("Tustr", "T_top-delta_Tz*z");
    model.component("comp1").physics("ht").create("pmls1", "PorousMediumLayeredShell", 2);
    model.component("comp1").physics("ht").feature("pmls1").selection().named("sel6");
    model.component("comp1").physics("ht").feature("pmls1").set("lth_mat", "userdef");
    model.component("comp1").physics("ht").feature("pmls1").set("lth", "d_f");
    model.component("comp1").physics("ht").feature("pmls1").set("Fluid_material", "mat1");
    model.component("comp1").physics("ht").feature("pmls1").set("u_src", "root.comp1.dl.u");
    model.component("comp1").physics("ht").feature("pmls1").set("theta_p_src", "root.comp1.dl.theta");
    model.component("comp1").physics("ht").feature("pmls1").set("k_p_mat", "userdef");
    model.component("comp1").physics("ht").feature("pmls1").set("k_p", new int[]{3, 0, 0, 0, 3, 0, 0, 0, 3});
    model.component("comp1").physics("ht").feature("pmls1").set("rho_p_mat", "userdef");
    model.component("comp1").physics("ht").feature("pmls1").set("rho_p", "1.2e3");
    model.component("comp1").physics("ht").feature("pmls1").set("Cp_p_mat", "userdef");
    model.component("comp1").physics("ht").feature("pmls1").set("Cp_p", 800);
    model.component("comp1").physics("ht").create("lihs1", "LineHeatSource", 1);
    model.component("comp1").physics("ht").feature("lihs1").selection().named("sel1");
    model.component("comp1").physics("ht").feature("lihs1").set("Ql", "mat1.def.Cp*dl.well1.Ml*(T_inj-T)");
    model.component("comp1").physics("ht").feature("lihs1").set("specifyHeatSourceRadius", true);
    model.component("comp1").physics("ht").feature("lihs1").set("radius", "r_bore");

    model.component("comp1").material("pmat1").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"1e-10[cm^2]"});
    model.component("comp1").material("pmat1").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material("pmat1").feature().create("solid1", "Solid", "comp1");
    model.component("comp1").material("pmat1").feature("solid1").set("vfrac", "0.9");
    model.component("comp1").material("pmat1").feature("solid1").set("link", "none");
    model.component("comp1").material("pmat1").feature("solid1").propertyGroup("def")
         .set("density", new String[]{"1300"});
    model.component("comp1").material("pmat1").feature("solid1").propertyGroup("def")
         .set("heatcapacity", new String[]{"900"});
    model.component("comp1").material("pmat1").feature("solid1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"2"});
    model.component("comp1").material("pmat2").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"1e-6[cm^2]"});
    model.component("comp1").material("pmat2").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material("pmat2").feature().create("solid1", "Solid", "comp1");
    model.component("comp1").material("pmat2").feature("solid1").set("link", "none");
    model.component("comp1").material("pmat2").feature("solid1").set("vfrac", "0.6");
    model.component("comp1").material("pmat2").feature("solid1").propertyGroup("def")
         .set("density", new String[]{"1900"});
    model.component("comp1").material("pmat2").feature("solid1").propertyGroup("def")
         .set("heatcapacity", new String[]{"850"});
    model.component("comp1").material("pmat2").feature("solid1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"3"});
    model.component("comp1").material("pmat3").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"1e-7[cm^2]"});
    model.component("comp1").material("pmat3").feature().create("fluid1", "Fluid", "comp1");
    model.component("comp1").material("pmat3").feature().create("solid1", "Solid", "comp1");
    model.component("comp1").material("pmat3").feature("solid1").set("vfrac", "0.7");
    model.component("comp1").material("pmat3").feature("solid1").set("link", "none");
    model.component("comp1").material("pmat3").feature("solid1").propertyGroup("def")
         .set("density", new String[]{"2300"});
    model.component("comp1").material("pmat3").feature("solid1").propertyGroup("def")
         .set("heatcapacity", new String[]{"850"});
    model.component("comp1").material("pmat3").feature("solid1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"3.5"});

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("dis1").selection().set(19, 37);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"dl/well1", "dl/well2"});
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tunit", "a");
    model.study("std1").feature("time").set("tlist", "range(0,0.2,10)");
    model.study("std1").setGenPlots(false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u538b\u529b (dl)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").label("\u538b\u529b (dl)");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().all();
    model.result("pg1").feature("surf1").feature("sel1").selection()
         .set(3, 6, 9, 11, 12, 13, 14, 15, 16, 18, 20, 21, 23, 25, 26, 27, 28, 29);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u6e29\u5ea6\u548c\u6d41\u573a");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "T");
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("sel7");
    model.result("pg2").feature("surf1").feature("sel1").selection().set(3, 11, 12, 13, 15, 16, 25, 26, 27, 28, 29);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("inheritplot", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("sel1").selection().named("sel6");
    model.result("pg2").run();
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", "T");
    model.result("pg2").feature("vol1").set("inheritplot", "surf1");
    model.result("pg2").feature("vol1").create("sel1", "Selection");
    model.result("pg2").feature("vol1").feature("sel1").selection().named("sel5");
    model.result("pg2").run();
    model.result("pg2").create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("xnumber", "2");
    model.result("pg2").feature("strmsl1").set("ynumber", "0");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "depth_w");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("pointtype", "arrow");
    model.result("pg2").feature("strmsl1").set("recover", "ppr");
    model.result("pg2").run();
    model.result("pg2").feature("strmsl1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "GrayScale");
    model.result("pg2").run();
    model.result("pg2").feature("strmsl1").set("arrowscaleactive", true);
    model.result("pg2").feature("strmsl1").set("arrowscale", 4000000);
    model.result("pg2").run();
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6e29\u5ea6\u5206\u5e03\u548c\u8fbe\u897f\u901f\u5ea6\u573a");
    model.result("pg2").set("edges", false);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").run();
    model.result().numerical().create("av1", "AvLine");
    model.result().numerical("av1").set("intsurface", true);
    model.result().numerical("av1").set("expr", new String[]{"T"});
    model.result().numerical("av1").set("descr", new String[]{"\u6e29\u5ea6"});
    model.result().numerical("av1").set("unit", new String[]{"K"});
    model.result().numerical("av1").selection().set(37);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u7ebf\u5e73\u5747\u503c 1");
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").setResult();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "none");
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("source", "table");
    model.result("pg3").feature("tblp1").set("table", "tbl1");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").label("\u91c7\u51fa\u6e29\u5ea6");
    model.result("pg2").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u6cbf\u88c2\u9699\u7684\u538b\u529b (dl)");
    model.result("pg4").selection().geom("geom1", 3);
    model.result("pg4").selection().set(1, 2, 3, 4, 5, 6);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg4").feature("surf1").feature("sel1").selection().set(14, 18, 21);
    model.result("pg4").feature().create("str1", "StreamlineSurface");
    model.result("pg4").feature("str1").label("Streamline_Surface");
    model.result("pg4").feature("str1").set("posmethod", "magnitude");
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result("pg4").feature("str1").set("smooth", "internal");
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("data", "parent");
    model.result("pg4").feature("str1").selection().geom("geom1", 2);
    model.result("pg4").feature("str1").selection().set(14, 18, 21);
    model.result("pg4").label("\u6cbf\u88c2\u9699\u7684\u538b\u529b (dl)");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("str1").set("mdist", new double[]{0.01, 0.15});
    model.result("pg4").run();

    model.title("\u5730\u70ed\u56de\u704c");

    model
         .description("\u8fd9\u4e00\u5730\u70ed\u56de\u704c\u793a\u4f8b\u6f14\u793a\u5982\u4f55\u901a\u8fc7\u8026\u5408\u4f20\u70ed\u4e0e\u6d41\u4f53\u6d41\u52a8\uff0c\u6765\u9884\u6d4b\u6cbf\u65ad\u88c2\u5e26\u7684\u591a\u5c42\u5730\u4e0b\u5ca9\u77f3\u4e2d\u70ed\u6c34\u56de\u704c\u7cfb\u7edf\u7684\u957f\u671f\u6027\u80fd\uff0c\u6a21\u62df\u4e86\u5177\u6709\u4e0d\u540c\u6c34\u529b\u7279\u6027\u548c\u70ed\u7279\u6027\u7684\u4e95\u3001\u88c2\u9699\u548c\u5730\u8d28\u5c42\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("geothermal_doublet.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
