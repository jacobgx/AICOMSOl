/*
 * orbit_thermal_loads.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:24 by COMSOL 6.3.0.290. */
public class orbit_thermal_loads {

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
    model.study("std1").create("otl", "OrbitThermalLoads");
    model.study("std1").feature("otl").set("solnum", "auto");
    model.study("std1").feature("otl").set("notsolnum", "auto");
    model.study("std1").feature("otl").set("outputmap", new String[]{});
    model.study("std1").feature("otl").setSolveFor("/physics/otl", true);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"10[cm]", "10[cm]", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "10[cm]", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");

    model.component("comp1").func().create("im1", "Image");
    model.component("comp1").func("im1")
         .label("\u592a\u9633\u6ce2\u6bb5\u53cd\u7167\u7387\u7684\u56fe\u50cf\u6570\u636e");
    model.component("comp1").func("im1")
         .set("filename", "Z:\\hub\\versions\\comsol63rc4\\comsol63rc4\\locales\\zh_CN\\applications.compact\\Heat_Transfer_Module\\Orbital_Thermal_Loads\\solarBandAlbedoData.png");
    model.component("comp1").func("im1").importData();
    model.component("comp1").func("im1").set("funcname", "imageData");
    model.component("comp1").func("im1").set("xmin", "-pi");
    model.component("comp1").func("im1").set("xmax", "pi");
    model.component("comp1").func("im1").set("ymin", "-pi/2");
    model.component("comp1").func("im1").set("ymax", "pi/2");
    model.component("comp1").func("im1").set("argunit", "rad");
    model.component("comp1").func("im1").set("fununit", "1");
    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1")
         .label("\u57fa\u4e8e\u56fe\u50cf\u7684\u53cd\u7167\u7387\uff0c\u592a\u9633\u6ce2\u6bb5");
    model.component("comp1").func("an1").set("funcname", "ImageBasedAlbedoSolarBand");
    model.component("comp1").func("an1").set("expr", "imageData(Longitude,Latitude)");
    model.component("comp1").func("an1").set("args", "Latitude, Longitude");
    model.component("comp1").func("an1").setIndex("argunit", "rad", 0);
    model.component("comp1").func("an1").setIndex("argunit", "rad", 1);
    model.component("comp1").func("an1").setIndex("plotargs", "-pi/2", 0, 1);
    model.component("comp1").func("an1").setIndex("plotargs", "pi/2", 0, 2);
    model.component("comp1").func("an1").setIndex("plotargs", "-pi", 1, 1);
    model.component("comp1").func("an1").setIndex("plotargs", "pi", 1, 2);
    model.component("comp1").func().create("an2", "Analytic");
    model.component("comp1").func("an2").label("\u6052\u5b9a\u53cd\u7167\u7387\uff0c\u7ea2\u5916\u6ce2\u6bb5");
    model.component("comp1").func("an2").set("funcname", "ConstantAlbedo_IR_Band");
    model.component("comp1").func("an2").set("expr", "0.3");
    model.component("comp1").func("an2").set("args", "Latitude, Longitude");
    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").setIndex("table", 85, 0, 0);
    model.component("comp1").func("int1").setIndex("table", 195, 0, 1);
    model.component("comp1").func("int1").setIndex("table", 75, 1, 0);
    model.component("comp1").func("int1").setIndex("table", 200, 1, 1);
    model.component("comp1").func("int1").setIndex("table", 65, 2, 0);
    model.component("comp1").func("int1").setIndex("table", 205, 2, 1);
    model.component("comp1").func("int1").setIndex("table", 55, 3, 0);
    model.component("comp1").func("int1").setIndex("table", 215, 3, 1);
    model.component("comp1").func("int1").setIndex("table", 45, 4, 0);
    model.component("comp1").func("int1").setIndex("table", 225, 4, 1);
    model.component("comp1").func("int1").setIndex("table", 35, 5, 0);
    model.component("comp1").func("int1").setIndex("table", 245, 5, 1);
    model.component("comp1").func("int1").setIndex("table", 25, 6, 0);
    model.component("comp1").func("int1").setIndex("table", 265, 6, 1);
    model.component("comp1").func("int1").setIndex("table", 15, 7, 0);
    model.component("comp1").func("int1").setIndex("table", 260, 7, 1);
    model.component("comp1").func("int1").setIndex("table", 5, 8, 0);
    model.component("comp1").func("int1").setIndex("table", 245, 8, 1);
    model.component("comp1").func("int1").setIndex("table", -5, 9, 0);
    model.component("comp1").func("int1").setIndex("table", 255, 9, 1);
    model.component("comp1").func("int1").setIndex("table", -15, 10, 0);
    model.component("comp1").func("int1").setIndex("table", 265, 10, 1);
    model.component("comp1").func("int1").setIndex("table", -25, 11, 0);
    model.component("comp1").func("int1").setIndex("table", 265, 11, 1);
    model.component("comp1").func("int1").setIndex("table", -35, 12, 0);
    model.component("comp1").func("int1").setIndex("table", 245, 12, 1);
    model.component("comp1").func("int1").setIndex("table", -45, 13, 0);
    model.component("comp1").func("int1").setIndex("table", 225, 13, 1);
    model.component("comp1").func("int1").setIndex("table", -55, 14, 0);
    model.component("comp1").func("int1").setIndex("table", 210, 14, 1);
    model.component("comp1").func("int1").setIndex("table", -65, 15, 0);
    model.component("comp1").func("int1").setIndex("table", 195, 15, 1);
    model.component("comp1").func("int1").setIndex("table", -75, 16, 0);
    model.component("comp1").func("int1").setIndex("table", 165, 16, 1);
    model.component("comp1").func("int1").setIndex("table", -85, 17, 0);
    model.component("comp1").func("int1").setIndex("table", 155, 17, 1);
    model.component("comp1").func("int1").label("\u968f\u7eac\u5ea6\u53d8\u5316\u7684\u7ea2\u5916\u901a\u91cf");
    model.component("comp1").func("int1").setIndex("fununit", "W/m^2", 0);
    model.component("comp1").func("int1").setIndex("argunit", 1, 0);
    model.component("comp1").func().create("an3", "Analytic");
    model.component("comp1").func("an3").label("\u884c\u661f\u901a\u91cf\uff0c\u7ea2\u5916\u6ce2\u6bb5");
    model.component("comp1").func("an3").set("funcname", "PlanetaryFlux_IR_Band");
    model.component("comp1").func("an3").set("expr", "int1(Latitude*90/pi)");
    model.component("comp1").func("an3").set("args", "Latitude, Longitude");
    model.component("comp1").func("an3").setIndex("argunit", 1, 0);
    model.component("comp1").func("an3").setIndex("argunit", 1, 1);

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intopAll");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().all();

    model.component("comp1").physics("otl").feature("sup1").set("sunDirection", "summerSol");
    model.component("comp1").physics("otl").feature("plp1").set("longType", "spacecraft");
    model.component("comp1").physics("otl").feature("plp1").set("geographicPositionDependence", "latLong");
    model.component("comp1").physics("otl").feature("plp1").set("planetAlbedoFunctionSolAmb", "userdefband");
    model.component("comp1").physics("otl").feature("plp1")
         .setIndex("planetAlbedoFunctionEachBandSolAmb", "an1", 0, 0);
    model.component("comp1").physics("otl").feature("plp1")
         .setIndex("planetAlbedoFunctionEachBandSolAmb", "an2", 1, 0);
    model.component("comp1").physics("otl").feature("plp1").set("planetRadiativeFluxFunctionSolAmb", "userdefband");
    model.component("comp1").physics("otl").feature("plp1")
         .setIndex("planetRadiativeFluxFunctionEachBandSolAmb", "an3", 1, 0);
    model.component("comp1").physics("otl").feature("op1").set("orbitType", "circular");
    model.component("comp1").physics("otl").feature("op1").set("R_orbit", "otl.R_planet+400[km]");
    model.component("comp1").physics("otl").feature("op1").set("i_orbit", "50[deg]");
    model.component("comp1").physics("otl").feature("op1").set("omegaType", "ltan");
    model.component("comp1").physics("otl").feature("op1").set("omegaTime_orbit", "15[h]");
    model.component("comp1").physics("otl").feature("so1").set("primaryRotation", "rate");
    model.component("comp1").physics("otl").feature("so1").set("primaryAngularRate", "2*360[deg]/otl.T_orbit");
    model.component("comp1").physics("otl").feature("dsurf1").set("epsilon_radSolAmb_mat", "userdefBand");
    model.component("comp1").physics("otl").feature("dsurf1").setIndex("epsilon_rad_bandSolAmb", 0.3, 0, 0);
    model.component("comp1").physics("otl").feature("dsurf1").setIndex("epsilon_rad_bandSolAmb", 0.85, 1, 0);
    model.component("comp1").physics("otl").create("dsurf2", "DiffuseSurface", 2);
    model.component("comp1").physics("otl").feature("dsurf2").selection().set(1, 2, 5, 6);
    model.component("comp1").physics("otl").feature("dsurf2").set("epsilon_radSolAmb_mat", "userdef");
    model.component("comp1").physics("otl").feature("dsurf2").set("epsilon_radSolAmb", 0.95);

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("otl").set("orbitlist", "range(0,0.01,5)");
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
    model.result("pg1").setIndex("looplevel", 521, 0);
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
    model.result("pg1").feature("pttraj1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
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
    model.result("pg1").feature("pttraj2").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
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
    model.result("pg1").feature("surf2").setIndex("looplevel", 521, 0);
    model.result("pg1").feature("surf2").set("solutionparams", "parent");
    model.result("pg1").feature("surf2").set("descractive", true);
    model.result("pg1").feature("surf2").set("expr", "otl.Gext1+otl.Gext2");
    model.result("pg1").feature("surf2").set("descr", "\u5916\u8f90\u5c04");
    model.result("pg1").feature("surf2").set("resolution", "norefine");
    model.result("pg1").feature("surf2").set("smooth", "internal");
    model.result("pg1").feature("surf2").set("data", "tran1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u5916\u8f90\u5c04 (otl)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("expr", "otl.Gext1+otl.Gext2");
    model.result("pg2").feature("surf1").set("descr", "\u5916\u8f90\u5c04");
    model.result("pg2").feature("surf1").set("resolution", "norefine");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("pttraj1", "PointTrajectories");
    model.result("pg2").feature("pttraj1").label("\u592a\u9633\u77e2\u91cf");
    model.result("pg2").feature("pttraj1").set("showsolutionparams", "on");
    model.result("pg2").feature("pttraj1").set("solutionparams", "parent");
    model.result("pg2").feature("pttraj1")
         .set("expr", new String[]{"-2*otl.L_geom*otl.SVX_Viz", "-2*otl.L_geom*otl.SVY_Viz", "-2*otl.L_geom*otl.SVZ_Viz"});
    model.result("pg2").feature("pttraj1").set("titletype", "none");
    model.result("pg2").feature("pttraj1").set("linetype", "none");
    model.result("pg2").feature("pttraj1").set("pointtype", "arrow");
    model.result("pg2").feature("pttraj1").set("pointcolor", "yellow");
    model.result("pg2").feature("pttraj1")
         .set("arrowexpr", new String[]{"otl.L_geom*otl.SVX_Viz", "otl.L_geom*otl.SVY_Viz", "otl.L_geom*otl.SVZ_Viz"});
    model.result("pg2").feature("pttraj1").set("arrowbase", "head");
    model.result("pg2").feature("pttraj1").set("arrowscaleactive", true);
    model.result("pg2").feature("pttraj1").set("showsolutionparams", "on");
    model.result("pg2").feature("pttraj1").set("data", "parent");
    model.result("pg2").feature("pttraj1").selection().geom("geom1", 0);
    model.result("pg2").feature("pttraj1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg2").feature("pttraj1").feature().create("col1", "Color");
    model.result("pg2").feature("pttraj1").feature("col1").set("showcolordata", "off");
    model.result("pg2").feature("pttraj1").feature("col1").set("expr", "otl.isIlluminated");
    model.result("pg2").feature("pttraj1").feature("col1").set("rangecoloractive", "on");
    model.result("pg2").feature("pttraj1").feature("col1").set("rangecolormax", 1);
    model.result("pg2").feature("pttraj1").feature("col1").set("coloring", "gradient");
    model.result("pg2").feature("pttraj1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("pttraj1").feature("col1").set("topcolor", "yellow");
    model.result("pg2").feature().create("pttraj2", "PointTrajectories");
    model.result("pg2").feature("pttraj2").label("\u6700\u4f4e\u70b9\u77e2\u91cf");
    model.result("pg2").feature("pttraj2").set("showsolutionparams", "on");
    model.result("pg2").feature("pttraj2").set("solutionparams", "parent");
    model.result("pg2").feature("pttraj2")
         .set("expr", new String[]{"-2*otl.L_geom*otl.ZDX_Viz", "-2*otl.L_geom*otl.ZDY_Viz", "-2*otl.L_geom*otl.ZDZ_Viz"});
    model.result("pg2").feature("pttraj2").set("titletype", "none");
    model.result("pg2").feature("pttraj2").set("linetype", "none");
    model.result("pg2").feature("pttraj2").set("pointtype", "arrow");
    model.result("pg2").feature("pttraj2").set("pointcolor", "blue");
    model.result("pg2").feature("pttraj2")
         .set("arrowexpr", new String[]{"-otl.L_geom*otl.ZDX_Viz", "-otl.L_geom*otl.ZDY_Viz", "-otl.L_geom*otl.ZDZ_Viz"});
    model.result("pg2").feature("pttraj2").set("arrowscaleactive", true);
    model.result("pg2").feature("pttraj2").set("showsolutionparams", "on");
    model.result("pg2").feature("pttraj2").set("data", "parent");
    model.result("pg2").feature("pttraj2").selection().geom("geom1", 0);
    model.result("pg2").feature("pttraj2").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("color", "custom");
    model.result("pg1").feature("surf1").set("customcolor", new double[]{0, 0, 0.250980406999588});
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("tran1").set("transparency", 0.2);
    model.result("pg1").run();
    model.result("pg1").create("surf3", "Surface");
    model.result("pg1").feature("surf3").label("\u53cd\u7167\u7387\u901a\u91cf");
    model.result("pg1").feature("surf3")
         .set("expr", "-ImageBasedAlbedoSolarBand(Latitude, Longitude)*otl.sup1.q0s*(x*otl.SVX_ECS+y*otl.SVY_ECS+z*otl.SVZ_ECS)");
    model.result("pg1").feature("surf3").set("colortable", "RainbowDark");
    model.result("pg1").feature("surf3").create("filt1", "Filter");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").feature("filt1").set("expr", "x*otl.SVX_ECS+y*otl.SVY_ECS+z*otl.SVZ_ECS<0");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").feature("tran1").set("transparency", 0.2);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3")
         .label("\u603b\u8f90\u5c04\u548c\u6bcf\u4e2a\u6ce2\u6bb5\u8f90\u5c04\u968f\u65f6\u95f4\u53d8\u5316\u7684\u60c5\u51b5");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u8f90\u5c04 (W)");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "intopAll(otl.Gext1+otl.Gext2)", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "\u603b\u8f90\u5c04", 0);
    model.result("pg3").feature("glob1").setIndex("expr", "intopAll(otl.Gext1)", 1);
    model.result("pg3").feature("glob1").setIndex("descr", "\u592a\u9633\u6ce2\u6bb5\u8f90\u5c04", 1);
    model.result("pg3").feature("glob1").setIndex("expr", "intopAll(otl.Gext2)", 2);
    model.result("pg3").feature("glob1").setIndex("descr", "\u73af\u5883\u6ce2\u6bb5\u8f90\u5c04", 2);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u603b\u8f90\u5c04\uff0c\u5468\u671f\u6027\u6bd4\u8f83");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u8f68\u9053\u5468\u671f (s)");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "intopAll(otl.Gext1+otl.Gext2)", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u603b\u8f90\u5c04", 0);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "mod(t,otl.T_orbit)");
    model.result("pg4").feature("glob1").create("filt1", "GlobalFilter");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").feature("filt1").set("xdec", false);
    model.result("pg4").run();
    model.result("pg4").feature("glob1").create("col1", "Color");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").feature("col1").set("expr", "ceil(t/otl.T_orbit)");
    model.result("pg4").feature("glob1").feature("col1").set("descractive", true);
    model.result("pg4").feature("glob1").feature("col1").set("descr", "\u8f68\u9053\u6570");
    model.result("pg4").feature("glob1").feature("col1").set("colorlegend", false);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("showlegends", false);
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1")
         .label("\u603b\u8f90\u5c04\u7684\u5e73\u5747\u503c\u968f\u65f6\u95f4\u53d8\u5316\u7684\u60c5\u51b5");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "first", 0);
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "timeavg(0, at('last',t),intopAll(otl.Gext1+otl.Gext2), 'nointerp')", 0);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u603b\u8f90\u5c04\u7684\u65f6\u95f4\u5e73\u5747\u503c", 0);
    model.result().evaluationGroup("eg1").run();
    model.result("pg1").run();

    model.title("\u8f68\u9053\u70ed\u8f7d\u8377");

    model
         .description("\u4e00\u9897\u5728\u8f68\u536b\u661f\u4f1a\u627f\u53d7\u592a\u9633\u3001\u53cd\u7167\u7387\u548c\u884c\u661f\u7ea2\u5916 (IR) \u8f7d\u8377\uff0c\u5176\u4e2d\u53cd\u7167\u7387\u548c\u884c\u661f\u7ea2\u5916\u4f1a\u968f\u7eac\u5ea6\u548c\u7ecf\u5ea6\u53d1\u751f\u53d8\u5316\u3002\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u5b9a\u4e49\u5728\u7a7a\u95f4\u53d8\u5316\u7684\u5730\u7403\u5c5e\u6027\uff0c\u5e76\u5c55\u793a\u5982\u4f55\u8bfb\u53d6\u7535\u5b50\u8868\u683c\u548c\u56fe\u50cf\u6570\u636e\uff0c\u4ee5\u53ca\u5982\u4f55\u5b9a\u4e49\u548c\u4f7f\u7528\u4e0d\u540c\u7684\u51fd\u6570\u3002\u672c\u4f8b\u8ba1\u7b97\u536b\u661f\u5728\u591a\u4e2a\u8f68\u9053\u4e0a\u7684\u603b\u8f90\u5c04\u548c\u901a\u91cf\uff0c\u5e76\u7ed8\u5236\u5730\u7403\u8868\u9762\u7684\u53cd\u7167\u7387\u901a\u91cf\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("orbit_thermal_loads.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
