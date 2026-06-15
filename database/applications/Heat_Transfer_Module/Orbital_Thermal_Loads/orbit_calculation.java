/*
 * orbit_calculation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:23 by COMSOL 6.3.0.290. */
public class orbit_calculation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Orbital_Thermal_Loads");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("otl", "OrbitalThermalLoadsEvents", "geom1");

    model.study().create("std1");
    model.study("std1").create("oc", "OrbitCalculation");
    model.study("std1").feature("oc").set("solnum", "auto");
    model.study("std1").feature("oc").set("notsolnum", "auto");
    model.study("std1").feature("oc").set("outputmap", new String[]{});
    model.study("std1").feature("oc").setSolveFor("/physics/otl", true);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"10[cm]", "10[cm]", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "10[cm]", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "2[cm]");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "2[cm]");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "5[cm]"});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "1.8[cm]");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "3[cm]");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"0", "0", "4.5[cm]"});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").selection("domain").set("cyl2", 1);
    model.component("comp1").geom("geom1").feature("pard1").set("partitionwith", "faces");
    model.component("comp1").geom("geom1").feature("pard1").selection("face").set("blk1", 4);
    model.component("comp1").geom("geom1").run("pard1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1", "cyl1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("pard1");
    model.component("comp1").geom("geom1").feature("dif1").set("intbnd", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("otl").feature("plp1").set("planetAlbedoTypeSolAmb", "userdefDistribution");
    model.component("comp1").physics("otl").feature("plp1").set("planetAlbedoSolAmb", 0.3);
    model.component("comp1").physics("otl").feature("plp1").set("planetRadiativeFluxTypeSolAmb", "userdefBand");
    model.component("comp1").physics("otl").feature("plp1")
         .setIndex("planetRadiativeFluxEachBandSolAmb", "225[W/m^2]", 1, 0);
    model.component("comp1").physics("otl").feature("op1").set("orbitType", "circular");
    model.component("comp1").physics("otl").feature("op1").set("R_orbit", "otl.R_planet+400[km]");
    model.component("comp1").physics("otl").feature("op1").set("i_orbit", "50[deg]");
    model.component("comp1").physics("otl").feature("so1").set("primaryRotation", "rate");
    model.component("comp1").physics("otl").feature("so1").set("primaryAngularRate", "2*360[deg]/otl.T_orbit");
    model.component("comp1").physics("otl").feature("dsurf1").set("epsilon_radSolAmb_mat", "userdefBand");
    model.component("comp1").physics("otl").feature("dsurf1").setIndex("epsilon_rad_bandSolAmb", 0.2, 0, 0);
    model.component("comp1").physics("otl").feature("dsurf1").setIndex("epsilon_rad_bandSolAmb", 0.85, 1, 0);
    model.component("comp1").physics("otl").create("dsurf2", "DiffuseSurface", 2);
    model.component("comp1").physics("otl").feature("dsurf2").selection().set(1, 2, 5, 20);
    model.component("comp1").physics("otl").feature("dsurf2").set("epsilon_radSolAmb_mat", "userdefBand");
    model.component("comp1").physics("otl").feature("dsurf2").setIndex("epsilon_rad_bandSolAmb", 0.99, 0, 0);
    model.component("comp1").physics("otl").feature("dsurf2").setIndex("epsilon_rad_bandSolAmb", 0.95, 1, 0);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("ps1", "ParSurface");
    model.result().dataset("ps1").label("\u884c\u661f");
    model.result().dataset("ps1").set("data", "none");
    model.result().dataset("ps1").set("par1", "Longitude");
    model.result().dataset("ps1").set("parmin1", "-pi");
    model.result().dataset("ps1").set("parmax1", "pi");
    model.result().dataset("ps1").set("par2", "Latitude");
    model.result().dataset("ps1").set("parmin2", "-0.5*pi");
    model.result().dataset("ps1").set("parmax2", "0.5*pi");
    model.result().dataset("ps1")
         .set("exprx", "comp1.otl.x_planet(comp1.otl.R_planet,comp1.otl.rot_planet,comp1.otl.A_off,Latitude,Longitude)");
    model.result().dataset("ps1")
         .set("expry", "comp1.otl.y_planet(comp1.otl.R_planet,comp1.otl.rot_planet,comp1.otl.A_off,Latitude,Longitude)");
    model.result().dataset("ps1")
         .set("exprz", "comp1.otl.z_planet(comp1.otl.R_planet,comp1.otl.rot_planet,Latitude,Longitude)");
    model.result().dataset("ps1").set("global", true);
    model.result().dataset("ps1").set("res1", 360);
    model.result().dataset("ps1").set("res2", 180);
    model.result().dataset("ps1").set("data", "none");
    model.result().dataset().create("tran1", "Transformation3D");
    model.result().dataset("tran1").label("\u822a\u5929\u5668\u8f68\u9053");
    model.result().dataset("tran1").set("data", "none");
    model.result().dataset("tran1").set("transtype", "general");
    model.result().dataset("tran1")
         .set("transmatrix", new String[][]{{"comp1.otl.Tviz11", "comp1.otl.Tviz12", "comp1.otl.Tviz13"}, {"comp1.otl.Tviz21", "comp1.otl.Tviz22", "comp1.otl.Tviz23"}, {"comp1.otl.Tviz31", "comp1.otl.Tviz32", "comp1.otl.Tviz33"}});
    model.result().dataset("tran1")
         .set("translation", new String[]{"comp1.otl.X_viz", "comp1.otl.Y_viz", "comp1.otl.Z_viz"});
    model.result().dataset("tran1").set("data", "dset1");
    model.result().dataset("ps1").set("data", "dset1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u8f68\u9053\u53ef\u89c6\u5316 (otl)");
    model.result("pg1").set("data", "ps1");
    model.result("pg1").setIndex("looplevel", 25, 0);
    model.result("pg1").set("titletype", "label");
    model.result("pg1").feature().create("img1", "Image");
    model.result("pg1").feature("img1").label("\u884c\u661f");
    model.result("pg1").feature("img1").set("showsolutionparams", "on");
    model.result("pg1").feature("img1").set("solutionparams", "parent");
    model.result("pg1").feature("img1").set("filename", "data:///physics/images/earth.jpg");
    model.result("pg1").feature("img1").set("mapping", "manual");
    model.result("pg1").feature("img1").set("uexpr", "0.5*(Longitude-pi)/pi");
    model.result("pg1").feature("img1").set("vexpr", "(Latitude+0.5*pi)/pi");
    model.result("pg1").feature("img1").set("resolution", "norefine");
    model.result("pg1").feature("img1").set("showsolutionparams", "on");
    model.result("pg1").feature("img1").set("data", "parent");
    model.result("pg1").feature().create("pttraj1", "PointTrajectories");
    model.result("pg1").feature("pttraj1").label("\u822a\u5929\u5668\u8f68\u9053");
    model.result("pg1").feature("pttraj1").set("showsolutionparams", "on");
    model.result("pg1").feature("pttraj1").set("solutionparams", "parent");
    model.result("pg1").feature("pttraj1").set("expr", new String[]{"otl.X_ECSViz", "otl.Y_ECSViz", "otl.Z_ECSViz"});
    model.result("pg1").feature("pttraj1").set("titletype", "none");
    model.result("pg1").feature("pttraj1").set("linetype", "tube");
    model.result("pg1").feature("pttraj1").set("interpolation", "uniform");
    model.result("pg1").feature("pttraj1").set("interpcount", 500);
    model.result("pg1").feature("pttraj1").set("radiusexpr", "0.01*otl.R_planet");
    model.result("pg1").feature("pttraj1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("pttraj1").set("showsolutionparams", "on");
    model.result("pg1").feature("pttraj1").set("data", "parent");
    model.result("pg1").feature("pttraj1").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28);
    model.result("pg1").feature("pttraj1").feature().create("col1", "Color");
    model.result("pg1").feature("pttraj1").feature("col1").set("expr", "otl.isIlluminated");
    model.result("pg1").feature("pttraj1").feature("col1").set("rangecoloractive", "on");
    model.result("pg1").feature("pttraj1").feature("col1").set("rangecolormax", 1);
    model.result("pg1").feature("pttraj1").feature("col1").set("coloring", "gradient");
    model.result("pg1").feature("pttraj1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("pttraj1").feature("col1").set("topcolor", "yellow");
    model.result("pg1").feature().create("pttraj2", "PointTrajectories");
    model.result("pg1").feature("pttraj2").label("\u592a\u9633\u77e2\u91cf");
    model.result("pg1").feature("pttraj2").set("showsolutionparams", "on");
    model.result("pg1").feature("pttraj2").set("solutionparams", "parent");
    model.result("pg1").feature("pttraj2")
         .set("expr", new String[]{"-1.5*otl.R_planet*otl.SVX_ECS", "-1.5*otl.R_planet*otl.SVY_ECS", "-1.5*otl.R_planet*otl.SVZ_ECS"});
    model.result("pg1").feature("pttraj2").set("titletype", "none");
    model.result("pg1").feature("pttraj2").set("linetype", "tube");
    model.result("pg1").feature("pttraj2").set("radiusexpr", "0.01*otl.R_planet");
    model.result("pg1").feature("pttraj2").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("pttraj2").set("pointtype", "arrow");
    model.result("pg1").feature("pttraj2").set("pointcolor", "yellow");
    model.result("pg1").feature("pttraj2")
         .set("arrowexpr", new String[]{"0.5*otl.SVX_ECS*otl.R_planet", "0.5*otl.SVY_ECS*otl.R_planet", "0.5*otl.SVZ_ECS*otl.R_planet"});
    model.result("pg1").feature("pttraj2").set("arrowbase", "head");
    model.result("pg1").feature("pttraj2").set("arrowscaleactive", true);
    model.result("pg1").feature("pttraj2").set("showsolutionparams", "on");
    model.result("pg1").feature("pttraj2").set("data", "parent");
    model.result("pg1").feature("pttraj2").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj2").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u65e5/\u6708\u98df");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("titletype", "none");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "black");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("filt1", "Filter");
    model.result("pg1").feature("surf1").feature("filt1").set("expr", "otl.SVX_ECS*x+otl.SVY_ECS*y+otl.SVZ_ECS*z>0");
    model.result("pg1").feature("surf1").feature("filt1").set("shownodespec", "on");
    model.result("pg1").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg1").feature("surf1").feature("tran1").set("transparency", 0.7);
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1")
         .set("expr", new String[]{"(-1+otl.scale_eclipse/otl.R_planet)*x", "(-1+otl.scale_eclipse/otl.R_planet)*y", "(-1+otl.scale_eclipse/otl.R_planet)*z"});
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").label("\u8d64\u9053");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("solutionparams", "parent");
    model.result("pg1").feature("con1").set("expr", "Latitude");
    model.result("pg1").feature("con1").set("titletype", "none");
    model.result("pg1").feature("con1").set("levelmethod", "levels");
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("color", "black");
    model.result("pg1").feature("con1").set("smooth", "internal");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature().create("con2", "Contour");
    model.result("pg1").feature("con2").label("\u672c\u521d\u5b50\u5348\u7ebf");
    model.result("pg1").feature("con2").set("showsolutionparams", "on");
    model.result("pg1").feature("con2").set("solutionparams", "parent");
    model.result("pg1").feature("con2").set("expr", "Longitude");
    model.result("pg1").feature("con2").set("titletype", "none");
    model.result("pg1").feature("con2").set("levelmethod", "levels");
    model.result("pg1").feature("con2").set("coloring", "uniform");
    model.result("pg1").feature("con2").set("colorlegend", false);
    model.result("pg1").feature("con2").set("color", "black");
    model.result("pg1").feature("con2").set("smooth", "internal");
    model.result("pg1").feature("con2").set("showsolutionparams", "on");
    model.result("pg1").feature("con2").set("data", "parent");
    model.result("pg1").feature().create("surf2", "Surface");
    model.result("pg1").feature("surf2").label("\u822a\u5929\u5668");
    model.result("pg1").feature("surf2").set("data", "tran1");
    model.result("pg1").feature("surf2").setIndex("looplevel", 25, 0);
    model.result("pg1").feature("surf2").set("solutionparams", "parent");
    model.result("pg1").feature("surf2").set("expr", "1");
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").feature("surf2").set("color", "gray");
    model.result("pg1").feature("surf2").set("resolution", "norefine");
    model.result("pg1").feature("surf2").set("smooth", "internal");
    model.result("pg1").feature("surf2").set("data", "tran1");
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{20});
    model.result("pg1").run();
    model.result("pg1").stepLast(0);
    model.result("pg1").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u8f68\u9053\u53d8\u91cf");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "first", 0);
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "otl.t_inEclipse", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u65e5\u98df\u5f00\u59cb", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "otl.t_outEclipse", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u65e5\u98df\u7ed3\u675f", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "otl.T_orbit", 2);
    model.result().evaluationGroup("eg1").run();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().all();

    model.component("comp1").mesh("mesh1").autoMeshSize(9);

    model.study().create("std2");
    model.study("std2").create("otl", "OrbitThermalLoads");
    model.study("std2").feature("otl").set("plotgroup", "Default");
    model.study("std2").feature("otl").set("solnum", "auto");
    model.study("std2").feature("otl").set("notsolnum", "auto");
    model.study("std2").feature("otl").set("outputmap", new String[]{});
    model.study("std2").feature("otl").setSolveFor("/physics/otl", true);
    model.study("std2").feature("otl").set("orbitlist", "range(0,0.005,1)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("ps2", "ParSurface");
    model.result().dataset("ps2").label("\u884c\u661f 1");
    model.result().dataset("ps2").set("data", "none");
    model.result().dataset("ps2").set("par1", "Longitude");
    model.result().dataset("ps2").set("parmin1", "-pi");
    model.result().dataset("ps2").set("parmax1", "pi");
    model.result().dataset("ps2").set("par2", "Latitude");
    model.result().dataset("ps2").set("parmin2", "-0.5*pi");
    model.result().dataset("ps2").set("parmax2", "0.5*pi");
    model.result().dataset("ps2")
         .set("exprx", "comp1.otl.x_planet(comp1.otl.R_planet,comp1.otl.rot_planet,comp1.otl.A_off,Latitude,Longitude)");
    model.result().dataset("ps2")
         .set("expry", "comp1.otl.y_planet(comp1.otl.R_planet,comp1.otl.rot_planet,comp1.otl.A_off,Latitude,Longitude)");
    model.result().dataset("ps2")
         .set("exprz", "comp1.otl.z_planet(comp1.otl.R_planet,comp1.otl.rot_planet,Latitude,Longitude)");
    model.result().dataset("ps2").set("global", true);
    model.result().dataset("ps2").set("res1", 360);
    model.result().dataset("ps2").set("res2", 180);
    model.result().dataset("ps2").set("data", "none");
    model.result().dataset().create("tran2", "Transformation3D");
    model.result().dataset("tran2").label("\u822a\u5929\u5668\u8f68\u9053 1");
    model.result().dataset("tran2").set("data", "none");
    model.result().dataset("tran2").set("transtype", "general");
    model.result().dataset("tran2")
         .set("transmatrix", new String[][]{{"comp1.otl.Tviz11", "comp1.otl.Tviz12", "comp1.otl.Tviz13"}, {"comp1.otl.Tviz21", "comp1.otl.Tviz22", "comp1.otl.Tviz23"}, {"comp1.otl.Tviz31", "comp1.otl.Tviz32", "comp1.otl.Tviz33"}});
    model.result().dataset("tran2")
         .set("translation", new String[]{"comp1.otl.X_viz", "comp1.otl.Y_viz", "comp1.otl.Z_viz"});
    model.result().dataset("tran2").set("spacevars", new String[]{"tran1x", "tran1y", "tran1z"});
    model.result().dataset("tran2").set("data", "dset2");
    model.result().dataset("ps2").set("data", "dset2");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u8f68\u9053\u53ef\u89c6\u5316 (otl) 1");
    model.result("pg2").set("data", "ps2");
    model.result("pg2").setIndex("looplevel", 205, 0);
    model.result("pg2").feature().create("img1", "Image");
    model.result("pg2").feature("img1").label("\u884c\u661f");
    model.result("pg2").feature("img1").set("showsolutionparams", "on");
    model.result("pg2").feature("img1").set("solutionparams", "parent");
    model.result("pg2").feature("img1").set("filename", "data:///physics/images/earth.jpg");
    model.result("pg2").feature("img1").set("mapping", "manual");
    model.result("pg2").feature("img1").set("uexpr", "0.5*(Longitude-pi)/pi");
    model.result("pg2").feature("img1").set("vexpr", "(Latitude+0.5*pi)/pi");
    model.result("pg2").feature("img1").set("resolution", "norefine");
    model.result("pg2").feature("img1").set("showsolutionparams", "on");
    model.result("pg2").feature("img1").set("data", "parent");
    model.result("pg2").feature().create("pttraj1", "PointTrajectories");
    model.result("pg2").feature("pttraj1").label("\u822a\u5929\u5668\u8f68\u9053");
    model.result("pg2").feature("pttraj1").set("showsolutionparams", "on");
    model.result("pg2").feature("pttraj1").set("solutionparams", "parent");
    model.result("pg2").feature("pttraj1").set("expr", new String[]{"otl.X_ECSViz", "otl.Y_ECSViz", "otl.Z_ECSViz"});
    model.result("pg2").feature("pttraj1").set("titletype", "none");
    model.result("pg2").feature("pttraj1").set("linetype", "tube");
    model.result("pg2").feature("pttraj1").set("interpolation", "uniform");
    model.result("pg2").feature("pttraj1").set("interpcount", 500);
    model.result("pg2").feature("pttraj1").set("radiusexpr", "0.01*otl.R_planet");
    model.result("pg2").feature("pttraj1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("pttraj1").set("showsolutionparams", "on");
    model.result("pg2").feature("pttraj1").set("data", "parent");
    model.result("pg2").feature("pttraj1").selection().geom("geom1", 0);
    model.result("pg2").feature("pttraj1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28);
    model.result("pg2").feature("pttraj1").feature().create("col1", "Color");
    model.result("pg2").feature("pttraj1").feature("col1").set("expr", "otl.isIlluminated");
    model.result("pg2").feature("pttraj1").feature("col1").set("rangecoloractive", "on");
    model.result("pg2").feature("pttraj1").feature("col1").set("rangecolormax", 1);
    model.result("pg2").feature("pttraj1").feature("col1").set("coloring", "gradient");
    model.result("pg2").feature("pttraj1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("pttraj1").feature("col1").set("topcolor", "yellow");
    model.result("pg2").feature().create("pttraj2", "PointTrajectories");
    model.result("pg2").feature("pttraj2").label("\u592a\u9633\u77e2\u91cf");
    model.result("pg2").feature("pttraj2").set("showsolutionparams", "on");
    model.result("pg2").feature("pttraj2").set("solutionparams", "parent");
    model.result("pg2").feature("pttraj2")
         .set("expr", new String[]{"-1.5*otl.R_planet*otl.SVX_ECS", "-1.5*otl.R_planet*otl.SVY_ECS", "-1.5*otl.R_planet*otl.SVZ_ECS"});
    model.result("pg2").feature("pttraj2").set("titletype", "none");
    model.result("pg2").feature("pttraj2").set("linetype", "tube");
    model.result("pg2").feature("pttraj2").set("radiusexpr", "0.01*otl.R_planet");
    model.result("pg2").feature("pttraj2").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("pttraj2").set("pointtype", "arrow");
    model.result("pg2").feature("pttraj2").set("pointcolor", "yellow");
    model.result("pg2").feature("pttraj2")
         .set("arrowexpr", new String[]{"0.5*otl.SVX_ECS*otl.R_planet", "0.5*otl.SVY_ECS*otl.R_planet", "0.5*otl.SVZ_ECS*otl.R_planet"});
    model.result("pg2").feature("pttraj2").set("arrowbase", "head");
    model.result("pg2").feature("pttraj2").set("arrowscaleactive", true);
    model.result("pg2").feature("pttraj2").set("showsolutionparams", "on");
    model.result("pg2").feature("pttraj2").set("data", "parent");
    model.result("pg2").feature("pttraj2").selection().geom("geom1", 0);
    model.result("pg2").feature("pttraj2").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u65e5/\u6708\u98df");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("titletype", "none");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "black");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("filt1", "Filter");
    model.result("pg2").feature("surf1").feature("filt1").set("expr", "otl.SVX_ECS*x+otl.SVY_ECS*y+otl.SVZ_ECS*z>0");
    model.result("pg2").feature("surf1").feature("filt1").set("shownodespec", "on");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg2").feature("surf1").feature("tran1").set("transparency", 0.7);
    model.result("pg2").feature("surf1").feature().create("def1", "Deform");
    model.result("pg2").feature("surf1").feature("def1")
         .set("expr", new String[]{"(-1+otl.scale_eclipse/otl.R_planet)*x", "(-1+otl.scale_eclipse/otl.R_planet)*y", "(-1+otl.scale_eclipse/otl.R_planet)*z"});
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u8d64\u9053");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("solutionparams", "parent");
    model.result("pg2").feature("con1").set("expr", "Latitude");
    model.result("pg2").feature("con1").set("titletype", "none");
    model.result("pg2").feature("con1").set("levelmethod", "levels");
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("color", "black");
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg2").feature().create("con2", "Contour");
    model.result("pg2").feature("con2").label("\u672c\u521d\u5b50\u5348\u7ebf");
    model.result("pg2").feature("con2").set("showsolutionparams", "on");
    model.result("pg2").feature("con2").set("solutionparams", "parent");
    model.result("pg2").feature("con2").set("expr", "Longitude");
    model.result("pg2").feature("con2").set("titletype", "none");
    model.result("pg2").feature("con2").set("levelmethod", "levels");
    model.result("pg2").feature("con2").set("coloring", "uniform");
    model.result("pg2").feature("con2").set("colorlegend", false);
    model.result("pg2").feature("con2").set("color", "black");
    model.result("pg2").feature("con2").set("smooth", "internal");
    model.result("pg2").feature("con2").set("showsolutionparams", "on");
    model.result("pg2").feature("con2").set("data", "parent");
    model.result("pg2").feature().create("surf2", "Surface");
    model.result("pg2").feature("surf2").label("\u822a\u5929\u5668");
    model.result("pg2").feature("surf2").set("data", "tran2");
    model.result("pg2").feature("surf2").setIndex("looplevel", 205, 0);
    model.result("pg2").feature("surf2").set("solutionparams", "parent");
    model.result("pg2").feature("surf2").set("descractive", true);
    model.result("pg2").feature("surf2").set("expr", "otl.Gext1+otl.Gext2");
    model.result("pg2").feature("surf2").set("descr", "\u5916\u8f90\u5c04");
    model.result("pg2").feature("surf2").set("resolution", "norefine");
    model.result("pg2").feature("surf2").set("smooth", "internal");
    model.result("pg2").feature("surf2").set("data", "tran2");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u5916\u8f90\u5c04 (otl)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 205, 0);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("descractive", true);
    model.result("pg3").feature("surf1").set("expr", "otl.Gext1+otl.Gext2");
    model.result("pg3").feature("surf1").set("descr", "\u5916\u8f90\u5c04");
    model.result("pg3").feature("surf1").set("resolution", "norefine");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("pttraj1", "PointTrajectories");
    model.result("pg3").feature("pttraj1").label("\u592a\u9633\u77e2\u91cf");
    model.result("pg3").feature("pttraj1").set("showsolutionparams", "on");
    model.result("pg3").feature("pttraj1").set("solutionparams", "parent");
    model.result("pg3").feature("pttraj1")
         .set("expr", new String[]{"-2*otl.L_geom*otl.SVX_Viz", "-2*otl.L_geom*otl.SVY_Viz", "-2*otl.L_geom*otl.SVZ_Viz"});
    model.result("pg3").feature("pttraj1").set("titletype", "none");
    model.result("pg3").feature("pttraj1").set("linetype", "none");
    model.result("pg3").feature("pttraj1").set("pointtype", "arrow");
    model.result("pg3").feature("pttraj1").set("pointcolor", "yellow");
    model.result("pg3").feature("pttraj1")
         .set("arrowexpr", new String[]{"otl.L_geom*otl.SVX_Viz", "otl.L_geom*otl.SVY_Viz", "otl.L_geom*otl.SVZ_Viz"});
    model.result("pg3").feature("pttraj1").set("arrowbase", "head");
    model.result("pg3").feature("pttraj1").set("arrowscaleactive", true);
    model.result("pg3").feature("pttraj1").set("showsolutionparams", "on");
    model.result("pg3").feature("pttraj1").set("data", "parent");
    model.result("pg3").feature("pttraj1").selection().geom("geom1", 0);
    model.result("pg3").feature("pttraj1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28);
    model.result("pg3").feature("pttraj1").feature().create("col1", "Color");
    model.result("pg3").feature("pttraj1").feature("col1").set("showcolordata", "off");
    model.result("pg3").feature("pttraj1").feature("col1").set("expr", "otl.isIlluminated");
    model.result("pg3").feature("pttraj1").feature("col1").set("rangecoloractive", "on");
    model.result("pg3").feature("pttraj1").feature("col1").set("rangecolormax", 1);
    model.result("pg3").feature("pttraj1").feature("col1").set("coloring", "gradient");
    model.result("pg3").feature("pttraj1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("pttraj1").feature("col1").set("topcolor", "yellow");
    model.result("pg3").feature().create("pttraj2", "PointTrajectories");
    model.result("pg3").feature("pttraj2").label("\u6700\u4f4e\u70b9\u77e2\u91cf");
    model.result("pg3").feature("pttraj2").set("showsolutionparams", "on");
    model.result("pg3").feature("pttraj2").set("solutionparams", "parent");
    model.result("pg3").feature("pttraj2")
         .set("expr", new String[]{"-2*otl.L_geom*otl.ZDX_Viz", "-2*otl.L_geom*otl.ZDY_Viz", "-2*otl.L_geom*otl.ZDZ_Viz"});
    model.result("pg3").feature("pttraj2").set("titletype", "none");
    model.result("pg3").feature("pttraj2").set("linetype", "none");
    model.result("pg3").feature("pttraj2").set("pointtype", "arrow");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").feature("pttraj2").set("pointcolor", "blue");
    model.result("pg3").feature("pttraj2")
         .set("arrowexpr", new String[]{"-otl.L_geom*otl.ZDX_Viz", "-otl.L_geom*otl.ZDY_Viz", "-otl.L_geom*otl.ZDZ_Viz"});
    model.result("pg3").feature("pttraj2").set("arrowscaleactive", true);
    model.result("pg3").feature("pttraj2").set("showsolutionparams", "on");
    model.result("pg3").feature("pttraj2").set("data", "parent");
    model.result("pg3").feature("pttraj2").selection().geom("geom1", 0);
    model.result("pg3").feature("pttraj2").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28);
    model.result("pg2").run();
    model.result("pg1").run();
    model.result().remove("pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u8f68\u9053\u53ef\u89c6\u5316 (otl)");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("pttraj1").set("linetype", "tube");
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{119});
    model.result("pg3").set("view", "new");
    model.result("pg3").run();
    model.result("pg3").feature("pttraj1").setIndex("expr", "-2*otl.L_geom*otl.SVX", 0);
    model.result("pg3").feature("pttraj1").setIndex("expr", "-2*otl.L_geom*otl.SVY", 1);
    model.result("pg3").feature("pttraj1").setIndex("expr", "-2*otl.L_geom*otl.SVZ", 2);
    model.result("pg3").feature("pttraj1").feature("col1").set("bottomcolor", "black");
    model.result("pg3").feature("pttraj2").setIndex("expr", "-2*otl.L_geom*otl.ZDX", 0);
    model.result("pg3").feature("pttraj2").setIndex("expr", "-2*otl.L_geom*otl.ZDY", 1);
    model.result("pg3").feature("pttraj2").setIndex("expr", "-2*otl.L_geom*otl.ZDZ", 2);
    model.result().dataset().create("ps3", "ParSurface");
    model.result().dataset("ps3").label("\u884c\u661f 3");
    model.result().dataset("ps3").set("par1", "Longitude");
    model.result().dataset("ps3").set("parmin1", "-pi");
    model.result().dataset("ps3").set("parmax1", "pi");
    model.result().dataset("ps3").set("par2", "Latitude");
    model.result().dataset("ps3").set("parmin2", "-0.5*pi");
    model.result().dataset("ps3").set("parmax2", "0.5*pi");
    model.result().dataset("ps3")
         .set("exprx", "comp1.otl.x_planet(comp1.otl.R_planet,comp1.otl.rot_planet,comp1.otl.A_off,Latitude,Longitude)");
    model.result().dataset("ps3")
         .set("expry", "comp1.otl.y_planet(comp1.otl.R_planet,comp1.otl.rot_planet,comp1.otl.A_off,Latitude,Longitude)");
    model.result().dataset("ps3")
         .set("exprz", "comp1.otl.z_planet(comp1.otl.R_planet,comp1.otl.rot_planet,Latitude,Longitude)");
    model.result().dataset("ps3").set("global", true);
    model.result().dataset("ps3").set("res1", 360);
    model.result().dataset("ps3").set("res2", 180);
    model.result().dataset("ps3").set("data", "dset2");
    model.result().dataset("ps3")
         .set("defaultPlotIDs", new String[]{"otl/OTL_PhysicsInterfaces/icom2/pdef1/pcond9/pg2|otl|dataset"});
    model.result().dataset("ps3").label("\u884c\u661f 3");
    model.result().dataset().create("filt1", "Filter");
    model.result().dataset("filt1").label("\u53ef\u89c6\u884c\u661f");
    model.result().dataset("filt1").set("expr", "comp1.otl.dist");
    model.result().dataset("filt1").set("bounds", "upper");
    model.result().dataset("filt1").set("upperexpr", "comp1.otl.maxDist");
    model.result().dataset("filt1").set("smooth", "internal");
    model.result().dataset("filt1").set("data", "ps3");
    model.result().dataset("filt1")
         .set("defaultPlotIDs", new String[]{"otl/OTL_PhysicsInterfaces/icom2/pdef1/pcond9/pg2|otl"});
    model.result().dataset("filt1").label("\u53ef\u89c6\u884c\u661f");
    model.result().dataset().create("tran3", "Transformation3D");
    model.result().dataset("tran3").label("\u53ef\u89c6\u5316\u540e\u7684\u8f68\u8ff9");
    model.result().dataset("tran3").set("transtype", "general");
    model.result().dataset("tran3")
         .set("transmatrix", new String[][]{{"comp1.otl.TvelViz11", "comp1.otl.TvelViz12", "comp1.otl.TvelViz13"}, {"comp1.otl.TvelViz21", "comp1.otl.TvelViz22", "comp1.otl.TvelViz23"}, {"comp1.otl.TvelViz31", "comp1.otl.TvelViz32", "comp1.otl.TvelViz33"}});
    model.result().dataset("tran3").set("spacevars", new String[]{"tran1x", "tran1y", "tran1z"});
    model.result().dataset("tran3").set("data", "dset2");
    model.result().dataset("tran3")
         .set("defaultPlotIDs", new String[]{"otl/OTL_PhysicsInterfaces/icom2/pdef1/pcond9/pg2|otl|surf1"});
    model.result().dataset("tran3").label("\u53ef\u89c6\u5316\u540e\u7684\u8f68\u8ff9");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u53ef\u89c6\u5316\u540e\u7684\u8f68\u8ff9 (otl)");
    model.result("pg4").set("data", "filt1");
    model.result("pg4").setIndex("looplevel", 205, 0);
    model.result("pg4").set("edges", "off");
    model.result("pg4").feature().create("img1", "Image");
    model.result("pg4").feature("img1").label("\u884c\u661f");
    model.result("pg4").feature("img1").set("solutionparams", "parent");
    model.result("pg4").feature("img1").set("filename", "data:///physics/images/earth.jpg");
    model.result("pg4").feature("img1").set("mapping", "manual");
    model.result("pg4").feature("img1").set("uexpr", "0.5*(Longitude-pi)/pi");
    model.result("pg4").feature("img1").set("vexpr", "(Latitude+0.5*pi)/pi");
    model.result("pg4").feature("img1").set("resolution", "norefine");
    model.result("pg4").feature("img1").set("showsolutionparams", "on");
    model.result("pg4").feature("img1").set("smoothingsupported", "on");
    model.result("pg4").feature("img1").set("data", "parent");
    model.result("pg4").feature("img1").feature().create("def1", "Deform");
    model.result("pg4").feature("img1").feature("def1")
         .set("expr", new String[]{"otl.defPlanetX_VF", "otl.defPlanetY_VF", "otl.defPlanetZ_VF"});
    model.result("pg4").feature("img1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature().create("pttraj1", "PointTrajectories");
    model.result("pg4").feature("pttraj1").label("\u592a\u9633\u77e2\u91cf");
    model.result("pg4").feature("pttraj1").set("solutionparams", "parent");
    model.result("pg4").feature("pttraj1").set("expr", new String[]{"-otl.SVX_VF", "-otl.SVY_VF", "-otl.SVZ_VF"});
    model.result("pg4").feature("pttraj1").set("titletype", "none");
    model.result("pg4").feature("pttraj1").set("linetype", "none");
    model.result("pg4").feature("pttraj1").set("pointtype", "arrow");
    model.result("pg4").feature("pttraj1").set("pointcolor", "yellow");
    model.result("pg4").feature("pttraj1").set("arrowexpr", new String[]{"otl.SVX_VF", "otl.SVY_VF", "otl.SVZ_VF"});
    model.result("pg4").feature("pttraj1").set("arrowbase", "head");
    model.result("pg4").feature("pttraj1").set("arrowscaleactive", true);
    model.result("pg4").feature("pttraj1").set("showsolutionparams", "on");
    model.result("pg4").feature("pttraj1").set("data", "parent");
    model.result("pg4").feature("pttraj1").selection().geom("geom1", 0);
    model.result("pg4").feature("pttraj1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28);
    model.result("pg4").feature("pttraj1").feature().create("col1", "Color");
    model.result("pg4").feature("pttraj1").feature("col1").set("showcolordata", "off");
    model.result("pg4").feature("pttraj1").feature("col1").set("expr", "otl.isIlluminated");
    model.result("pg4").feature("pttraj1").feature("col1").set("rangecoloractive", "on");
    model.result("pg4").feature("pttraj1").feature("col1").set("rangecolormax", 1);
    model.result("pg4").feature("pttraj1").feature("col1").set("coloring", "gradient");
    model.result("pg4").feature("pttraj1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("pttraj1").feature("col1").set("topcolor", "yellow");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u822a\u5929\u5668");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("descractive", true);
    model.result("pg4").feature("surf1").set("expr", "otl.Gext1+otl.Gext2");
    model.result("pg4").feature("surf1").set("descr", "\u5916\u8f90\u5c04");
    model.result("pg4").feature("surf1").set("resolution", "norefine");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "tran3");
    model.result("pg4").label("\u53ef\u89c6\u5316\u540e\u7684\u8f68\u8ff9 (otl)");
    model.result("pg4").run();
    model.result("pg4").set("looplevel", new int[]{7});
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset2");
    model.result("pg5").label("\u968f\u65f6\u95f4\u53d8\u5316\u7684\u5916\u8f90\u5c04");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "intop1(otl.Gext1+otl.Gext2)", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u5916\u8f90\u5c04", 0);
    model.result("pg5").feature("glob1").set("legend", false);
    model.result("pg5").run();
    model.result().evaluationGroup("eg1").create("av1", "AvSurface");
    model.result().evaluationGroup("eg1").feature("av1").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("av1").set("data", "dset2");
    model.result().evaluationGroup("eg1").feature("av1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").feature("av1").selection().all();
    model.result().evaluationGroup("eg1").feature("av1")
         .setIndex("expr", "timeavg(0,otl.T_orbit,intop1(otl.Gext1+otl.Gext2),'nointerp')", 0);
    model.result().evaluationGroup("eg1").feature("av1")
         .setIndex("descr", "\u8f68\u9053\u5e73\u5747\u8f90\u5c04", 0);
    model.result().evaluationGroup("eg1").run();
    model.result("pg2").run();

    model.title("\u8f68\u9053\u8ba1\u7b97");

    model
         .description("\u672c\u6a21\u578b\u4ecb\u7ecd\u5982\u4f55\u5b9a\u4e49\u548c\u9a8c\u8bc1\u536b\u661f\u8f68\u9053\uff0c\u5e76\u8ba1\u7b97\u592a\u9633\u8f90\u5c04\u3001\u53cd\u7167\u7387\u548c\u5730\u7403\u8f90\u5c04\u70ed\u8d1f\u8377\u3002\u4e00\u9897 1U \u7684\u7acb\u65b9\u4f53\u536b\u661f\u5728 400\u00a0km \u9ad8\u5ea6\u7684\u5706\u5f62\u8f68\u9053\u4e0a\uff0c\u4ee5 50\u00b0\u7684\u503e\u89d2\u548c 0\u00b0\u7684\u5347\u4ea4\u70b9\u7ecf\u5ea6\u8fd0\u884c\uff0c\u536b\u661f\u7ed5\u5176\u6307\u5411\u5929\u5e95\u7684\u8f74\u7f13\u6162\u65cb\u8f6c\u3002\u672c\u4f8b\u5206\u6790\u51ac\u81f3\u671f\u95f4\u7684\u4e00\u4e2a\u8f68\u9053\u5468\u671f\uff0c\u8ba1\u7b97\u4e86\u6765\u81ea\u6240\u6709\u73af\u5883\u6e90\u7684\u603b\u8f90\u5c04\u548c\u8f90\u5c04\u8f7d\u8377\u3002\u8fd9\u79cd\u5206\u6790\u901a\u5e38\u5728\u8ba1\u7b97\u536b\u661f\u5185\u90e8\u7ed3\u6784\u7684\u6e29\u5ea6\u6f14\u53d8\u4e4b\u524d\u8fdb\u884c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("orbit_calculation.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
