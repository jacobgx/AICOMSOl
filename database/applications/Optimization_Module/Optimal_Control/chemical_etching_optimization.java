/*
 * chemical_etching_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:41 by COMSOL 6.3.0.290. */
public class chemical_etching_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Optimal_Control");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp1").physics("tds").field("concentration").field("cCuCl2");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"cCuCl2"});
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("cCuCl2_bulk", "1[mol/dm^3]", "Bulk concentration, cCuCl2");
    model.param().set("kf", "100[m/s]", "Forward rate constant");
    model.param().set("M_Cu", "65[g/mol]", "Molar mass, Cu");
    model.param().set("rho_Cu", "9000[kg/m^3]", "Density, Cu");
    model.param().set("h_mask", "0.1[mm]", "Height mask");
    model.param().set("h_seed_cavity", "0.1[mm]", "Seed cavity height");
    model.param().set("d_mask", "1[mm]", "Mask length");
    model.param().set("h_boundary_layer", "1[mm]+2*h_seed_cavity", "Boundary layer width");
    model.param().set("D", "1e-9[m^2/s]", "Diffusion coefficient of reactant");
    model.param().set("tmax", "3[h]", "Etching time");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("v_surface", "-r_surface*M_Cu/rho_Cu");
    model.component("comp1").variable("var1").descr("v_surface", "Surface normal velocity");
    model.component("comp1").variable("var1").set("r_surface", "-kf*cCuCl2");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"d_mask", "h_mask"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-0.5*d_mask", "0"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"h_boundary_layer", "1"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("size", "h_seed_cavity", 1);
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"-0.5*h_boundary_layer", "0"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("pos", "-h_seed_cavity", 1);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r2", 1, 2);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "h_seed_cavity/2");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"d_mask*2", "3*h_mask"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"-d_mask", "h_mask"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("box1", "Box");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("box1").set("entitydim", 1);
    model.component("comp1").selection("box1").label("Moving Boundary");
    model.component("comp1").selection("box1").set("ymax", "-h_seed_cavity*0.001");
    model.component("comp1").selection().duplicate("box2", "box1");
    model.component("comp1").selection("box2").label("Bottom");
    model.component("comp1").selection("box2").set("ymax", "-h_seed_cavity*0.999");
    model.component("comp1").selection("box2").set("condition", "inside");

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
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
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
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
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

    model.component("comp1").physics("tds").feature("cdm1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_cCuCl2", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tds").feature("conc1").selection().set(1, 3);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "cCuCl2_bulk", 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 1);
    model.component("comp1").physics("tds").feature("out1").selection().set(14);
    model.component("comp1").physics("tds").create("fl1", "FluxBoundary", 1);
    model.component("comp1").physics("tds").feature("fl1").selection().named("box1");
    model.component("comp1").physics("tds").feature("fl1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("fl1").setIndex("J0", "r_surface", 0);
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(3);
    model.component("comp1").physics("spf").feature("wallbc2").set("TranslationalVelocityOption", "Manual");
    model.component("comp1").physics("spf").feature("wallbc2").set("utr", new String[]{"1[mm/s]", "0", "0"});
    model.component("comp1").physics("spf").create("bs1", "BoundaryStress", 1);
    model.component("comp1").physics("spf").feature("bs1").selection().set(1, 14);
    model.component("comp1").physics("spf").feature("bs1").set("BoundaryCondition", "NormalStressNormalFlow");

    model.component("comp1").common().create("free1", "DeformingDomainDeformedGeometry");
    model.component("comp1").common("free1").selection().all();
    model.component("comp1").common("free1").selection().set(2);
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common().create("pnmv1", "PrescribedNormalMeshVelocityDeformedGeometry");
    model.component("comp1").common("pnmv1").selection().named("box1");
    model.component("comp1").common("pnmv1").set("prescribedNormalVelocity", "v_surface");
    model.component("comp1").common().create("pnmd1", "PrescribedNormalMeshDisplacementDeformedGeometry");
    model.component("comp1").common("pnmd1").selection().set(5, 11);
    model.component("comp1").common().create("disp1", "PrescribedMeshDisplacementDeformedGeometry");
    model.component("comp1").common("disp1").selection().set(8);

    model.study("std1").feature("stat").setSolveFor("/frame/material1", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,0.05*tmax,tmax)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 21, 0);
    model.result("pg1").label("Concentration (tds)");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("prefixintitle", "");
    model.result("pg1").set("expressionintitle", false);
    model.result("pg1").set("typeintitle", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"cCuCl2"});
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").set("typeintitle", true);
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"tds.tflux_cCuCl2x", "tds.tflux_cCuCl2y"});
    model.result("pg1").feature("arws1").set("xnumber", 10);
    model.result("pg1").feature("arws1").set("ynumber", 10);
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").feature("arws1").create("sel1", "Selection");
    model.result("pg1").feature("arws1").feature("sel1").selection().set(1, 2, 3);
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("Velocity (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("defaultPlotID", "ResultDefaults_SinglePhaseFlow/icom1/pdef1/pcond1/pg1");
    model.result("pg2").set("weight", 0);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "spf.U");
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("Pressure (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("defaultPlotID", "ResultDefaults_SinglePhaseFlow/icom1/pdef1/pcond1/pg2");
    model.result("pg3").set("weight", 0);
    model.result("pg3").feature().create("con1", "Contour");
    model.result("pg3").feature("con1").label("Contour");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("expr", "p");
    model.result("pg3").feature("con1").set("number", 40);
    model.result("pg3").feature("con1").set("levelrounding", false);
    model.result("pg3").feature("con1").set("colortable", "Rainbow");
    model.result("pg3").feature("con1").set("smooth", "internal");
    model.result("pg3").feature("con1").set("showsolutionparams", "on");
    model.result("pg3").feature("con1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 21, 0);
    model.result("pg4").label("Deformed Geometry");
    model.result("pg4").create("mesh1", "Mesh");
    model.result("pg4").feature("mesh1").set("meshdomain", "surface");
    model.result("pg4").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg4").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg4").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg4").feature("mesh1").feature("sel1").selection().set(2);
    model.result("pg4").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg4").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg4").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("arws1").set("color", "white");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("Mesh");
    model.result("pg5").create("mesh1", "Mesh");
    model.result("pg5").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg5").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg5").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "15");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "off");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "off");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "off");
    model.result().export("anim1").set("legend2d", "off");
    model.result().export("anim1").set("logo2d", "off");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "off");
    model.result().export("anim1").set("legend3d", "off");
    model.result().export("anim1").set("logo3d", "off");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "off");
    model.result().export("anim1").set("grid", "off");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("maxframes", 21);
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").set("showframe", 101);
    model.result().export("anim1").run();

    model.title("\u5316\u5b66\u8680\u523b");

    model
         .description("\u672c\u4f8b\u4ee5\u4e8c\u7ef4\u51e0\u4f55\u6a21\u578b\u4ecb\u7ecd\u5c42\u6d41\u72b6\u6001\u4e0b\u7684\u6e7f\u6cd5\u5316\u5b66\u8680\u523b\u539f\u7406\u3002\u6e7f\u6cd5\u5316\u5b66\u8680\u523b\u5728\u5fae\u7535\u5b50\u884c\u4e1a\u4e2d\u5c24\u5176\u91cd\u8981\uff0c\u7528\u4e8e\u96c6\u6210\u7535\u8def\u3001MEMS \u5668\u4ef6\u3001\u5149\u7535\u5b50\u548c\u538b\u529b\u4f20\u611f\u5668\u7684\u56fe\u6837\u3002\u6b64 App \u4ecb\u7ecd\u94dc\u7684\u8154\u4f53\u5728\u6c2f\u5316\u94dc\u8680\u523b\u6db2\u4f5c\u7528\u4e0b\u7684\u6e7f\u6cd5\u5316\u5b66\u8680\u523b\u8fc7\u7a0b\uff0c\u5176\u4e2d\u5047\u8bbe\u8680\u523b\u53cd\u5e94\u9075\u5faa\u7ebf\u6027\u52a8\u529b\u5b66\u3002\n\n\u7814\u7a76\u8680\u523b\u8fc7\u7a0b\u4e2d\u5b8c\u5168\u88f8\u9732\u7684\u94dc\u7684\u8154\u4f53\u4e2d\u7684\u6d53\u5ea6\u5206\u5e03\u3001\u901f\u5ea6\u5206\u5e03\u548c\u5f62\u72b6\u53d8\u5316\u3002\u53ef\u4ee5\u53d1\u73b0\uff0c\u94dc\u57fa\u5e95\u7684\u53d8\u5f62\u51e0\u4f55\u7ed3\u6784\u53d6\u51b3\u4e8e\u5c40\u90e8\u6d41\u4f53\u901f\u5ea6\u5206\u5e03\u548c\u8680\u523b\u6db2\u7269\u8d28\u7684\u901a\u91cf\u3002\u6a21\u578b\u9884\u6d4b\u5c42\u6d41\u72b6\u6001\u4e0b\u94dc\u57fa\u5e95\u662f\u5982\u4f55\u88ab\u8680\u523b\u7684\uff0c\u5e76\u8ddf\u8e2a\u4e8c\u7ef4\u8154\u4f53\u5728\u6e7f\u6cd5\u8680\u523b\u8fc7\u7a0b\u4e2d\u7684\u5f62\u72b6\u53d8\u5316\u3002");

    model.label("chemical_etching.mph");

    model.result().export("anim1").showFrame();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").create("av1", "AvLine");
    model.result().evaluationGroup("eg1").feature("av1").set("intsurface", true);
    model.result().evaluationGroup("eg1").feature("av1").selection().named("box2");
    model.result().evaluationGroup("eg1").feature("av1").setIndex("expr", "y", 0);
    model.result().evaluationGroup("eg1").run();

    model.param().set("yavg", "-319[um]");
    model.param().descr("yavg", "Initial etching depth");
    model.param().set("t", "0[s]");
    model.param().descr("t", "Time");

    model.component("comp1").probe().create("bnd1", "Boundary");
    model.component("comp1").probe("bnd1").set("intsurface", true);
    model.component("comp1").probe("bnd1").set("probename", "constr");
    model.component("comp1").probe("bnd1").selection().set();
    model.component("comp1").probe("bnd1").selection().named("box2");
    model.component("comp1").probe("bnd1").set("expr", "y/yavg");

    model.component("comp1").common().create("cfunc1", "ControlFunction");
    model.component("comp1").common("cfunc1").set("xend", "tmax");
    model.component("comp1").common("cfunc1").set("fmin", "0.1");
    model.component("comp1").common("cfunc1").set("fmax", "1.5");
    model.component("comp1").common("cfunc1").set("fleftType", "Dirichlet");
    model.component("comp1").common("cfunc1").set("fstart", "1");
    model.component("comp1").common("cfunc1").set("c_0", "1");
    model.component("comp1").common("cfunc1").set("controlType", "piecewiseBernsteinPolynomial");
    model.component("comp1").common("cfunc1").set("order", "3");
    model.component("comp1").common().duplicate("cfunc2", "cfunc1");

    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "cCuCl2_bulk*cfunc1(t)", 0);
    model.component("comp1").physics("spf").feature("wallbc2")
         .set("utr", new String[]{"1[mm/s]*cfunc2(t)", "0", "0"});

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").selection().geom("geom1", 1);
    model.component("comp1").cpl("genext1").selection().named("box2");
    model.component("comp1").cpl("genext1").set("dstmap", new String[]{"-x", "Yg"});
    model.component("comp1").cpl("genext1").set("usesrcmap", true);
    model.component("comp1").cpl("genext1").set("srcmap", new String[]{"x", "Yg"});

    model.component("comp1").probe().create("bnd2", "Boundary");
    model.component("comp1").probe("bnd2").set("intsurface", true);
    model.component("comp1").probe("bnd2").set("probename", "obj");
    model.component("comp1").probe("bnd2").selection().set();
    model.component("comp1").probe("bnd2").selection().named("box2");
    model.component("comp1").probe("bnd2").set("expr", "if(isnan(genext1(y)),0, (y-genext1(y))^2)");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature().copy("time", "std1/time");
    model.study("std2").feature("stat").setSolveFor("/frame/material1", false);
    model.study("std2").create("sho", "ShapeOptimization");
    model.study("std2").feature("sho").set("gradientStep", "time");
    model.study("std2").feature("sho").set("movelimit", 0.2);
    model.study("std2").feature("sho").set("mmamaxiter", 25);
    model.study("std2").feature("sho").set("optobj", new String[]{"comp1.obj"});
    model.study("std2").feature("sho").set("descr", new String[]{"\u8fb9\u754c\u63a2\u9488 2"});
    model.study("std2").feature("sho").set("objectivescaling", "init");
    model.study("std2").feature("sho").set("constraintExpression", new String[]{"comp1.constr"});
    model.study("std2").feature("sho").setIndex("constraintLbound", 1, 0);
    model.study("std2").feature("sho").setIndex("constraintUbound", "", 0);
    model.study("std2").feature("sho").set("probesel", "none");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol3").runFromTo("st1", "v1");

    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").add("plotgroup", "pg5");
    model.nodeGroup("grp1").add("evaluationgroup", "eg1");

    model.result("pg1").run();
    model.result().duplicate("pg6", "pg1");

    model.nodeGroup("grp1").add("plotgroup", "pg6");

    model.result("pg6").run();

    model.nodeGroup("grp1").remove("plotgroup", "pg6", false);

    model.result("pg6").run();
    model.result("pg6").set("data", "dset3");

    model.study("std2").feature("sho").set("plot", true);
    model.study("std2").feature("sho").set("plotgroup", "pg6");

    model.sol("sol3").feature("o1").set("stationaryhidesens", "off");
    model.sol("sol3").feature("o1").set("stationaryhideadj", "off");
    model.sol("sol3").feature("o1").set("gcmma", false);
    model.sol("sol3").feature("o1").set("nojacmethod", false);
    model.sol("sol3").feature("o1").feature("t1").feature("aDef").set("cachepattern", false);

    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result("pg6").run();

    model.study("std2").feature("sho").set("probewindow", "");

    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").set("data", "dset3");
    model.result("pg7").set("legendpos", "center");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "cfunc1(t)", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "Concentration", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "cfunc2(t)", 1);
    model.result("pg7").feature("glob1").setIndex("descr", "Wall movement", 1);
    model.result("pg7").feature("glob1").set("xdataparamunit", "h");
    model.result("pg7").feature("glob1").set("autosolution", false);
    model.result("pg7").feature("glob1").set("autoexpr", true);
    model.result("pg7").run();
    model.result().dataset().create("tran1", "Transformation2D");
    model.result().dataset("tran1").set("enablemove", true);
    model.result().dataset("tran1").set("move", new String[]{"0", "10*h_mask"});
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("surf2", "surf1");
    model.result("pg6").feature().duplicate("arws2", "arws1");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("data", "tran1");
    model.result("pg6").feature("surf2").set("inheritplot", "surf1");
    model.result("pg6").run();
    model.result("pg6").feature("arws2").set("data", "tran1");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").set("titletype", "none");
    model.result("pg6").create("line1", "Line");
    model.result("pg6").feature("line1").set("data", "tran1");
    model.result("pg6").feature("line1").set("expr", "1");
    model.result("pg6").feature("line1").set("coloring", "uniform");
    model.result("pg6").feature("line1").set("color", "black");
    model.result("pg6").run();
    model.result("pg1").run();
    model.result("pg1").run();

    model.title("\u5316\u5b66\u8680\u523b\u7684\u4f18\u5316");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u901a\u8fc7\u6539\u53d8\u5bf9\u6d41\u548c\u5316\u5b66\u6d53\u5ea6\u968f\u65f6\u95f4\u7684\u53d8\u5316\uff0c\u4ee5\u63d0\u9ad8\u6e7f\u5316\u5b66\u8680\u523b\u8fc7\u7a0b\u7684\u5bf9\u79f0\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("chemical_etching_optimization.mph");

    return model;
  }

  public static Model run2(Model model) {

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
