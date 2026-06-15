/*
 * flow_channel_turbulent_dispersion.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:53 by COMSOL 6.3.0.290. */
public class flow_channel_turbulent_dispersion {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "TurbulentFlowkeps", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("halfWidth", "0.02[m]", "\u69fd\u9053\u534a\u5bbd");
    model.param().set("height", "10[m]", "\u901a\u9053\u9ad8\u5ea6");
    model.param().set("nelemWidth", "20", "\u6cbf\u534a\u5bbd\u65b9\u5411\u7684\u7f51\u683c\u5355\u5143\u6570");
    model.param().set("nelemHeight", "100", "\u6cbf\u9ad8\u5ea6\u65b9\u5411\u7684\u7f51\u683c\u5355\u5143\u6570");
    model.param().set("va", "1.65[m/s]", "\u5e73\u5747\u6d41\u4f53\u901f\u5ea6");
    model.param().set("rhof", "1.3[kg/m^3]", "\u7a7a\u6c14\u5bc6\u5ea6");
    model.param().set("nuf", "15.7e-6[m^2/s]", "\u7a7a\u6c14\u8fd0\u52a8\u9ecf\u5ea6");
    model.param().set("muf", "nuf*rhof", "\u7a7a\u6c14\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("ustar_exp", "0.11775[m/s]", "\u9884\u8ba1\u58c1\u901f\u5ea6");
    model.param().set("rhop", "769*rhof", "\u9897\u7c92\u5bc6\u5ea6");
    model.param().set("St", "125", "\u9897\u7c92\u65af\u6258\u514b\u65af\u6570");
    model.param().set("taup", "St*nuf/ustar_exp^2", "\u9897\u7c92\u5f1b\u8c6b\u65f6\u95f4");
    model.param().set("t1", "675*nuf/ustar_exp^2", "t_plus = 675 \u7684\u65f6\u95f4");
    model.param().set("t2", "1125*nuf/ustar_exp^2", "t_plus = 1125 \u7684\u65f6\u95f4");
    model.param().set("dp", "sqrt(18*taup*nuf*rhof/rhop)", "\u7c92\u5f84");
    model.param().set("Np", "10000", "\u9897\u7c92\u6570");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"halfWidth", "height"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").axis().set("viewscaletype", "automatic");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rhof"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"muf"});

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").selection().set(2);
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "va");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(3);
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("spf").feature("sym1").selection().set(1);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", "nelemHeight");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", "nelemWidth");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 15);
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
    model.result().dataset().create("edg1", "Edge2D");
    model.result().dataset("edg1").label("\u5916\u58c1");
    model.result().dataset("edg1").set("data", "dset1");
    model.result().dataset("edg1").selection().geom("geom1", 1);
    model.result().dataset("edg1").selection().set(4);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("line1", "Line");
    model.result("pg3").feature("line1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("expr", "spf.Delta_wPlus");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("smooth", "internal");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("data", "parent");
    model.result("pg3").feature("line1").feature().create("hght1", "Height");
    model.result("pg3").feature("line1").feature("hght1").label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f");
    model.result("pg3").feature("line1").feature("hght1").set("heightdata", "expr");
    model.result("pg3").feature("line1").feature("hght1").set("expr", "spf.WRHeightExpr");
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").selection().set(2);
    model.result("pg1").feature("str1").set("color", "white");
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u6e4d\u6d41\u65f6\u95f4\u5c3a\u5ea6");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "spf.tauT");
    model.result("pg4").feature("surf1").set("descr", "\u6e4d\u6d41\u65f6\u95f4\u5c3a\u5ea6");
    model.result("pg4").run();

    model.component("comp1").physics().create("fpt", "FluidParticleTracing", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/fpt", false);
    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/spf", false);
    model.study("std2").feature("time").setSolveFor("/physics/fpt", true);

    model.component("comp1").physics("fpt").feature("pp1").set("rhop_mat", "userdef");
    model.component("comp1").physics("fpt").feature("pp1").set("rhop", "rhop");
    model.component("comp1").physics("fpt").feature("pp1").set("dp", "dp");
    model.component("comp1").physics("fpt").create("out1", "Outlet", 1);
    model.component("comp1").physics("fpt").feature("out1").selection().set(2, 3);
    model.component("comp1").physics("fpt").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("fpt").feature("sym1").selection().set(1);
    model.component("comp1").physics("fpt").feature("wall1").set("WallCondition", "Bounce");
    model.component("comp1").physics("fpt").create("df1", "DragForce", 2);
    model.component("comp1").physics("fpt").feature("df1").selection().all();
    model.component("comp1").physics("fpt").feature("df1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("fpt").feature("df1").set("TurbulentDispersionModel", "ContinuousRandomWalk");
    model.component("comp1").physics("fpt").feature("df1").set("k_src", "root.comp1.k");
    model.component("comp1").physics("fpt").feature("df1").set("ep_src", "root.comp1.ep");
    model.component("comp1").physics("fpt").feature("df1").set("IncludeAnisotropicTurbulenceInBoundaryLayers", true);
    model.component("comp1").physics("fpt").feature("df1").set("ustar", "ustar_exp");
    model.component("comp1").physics("fpt").feature("df1").set("MeshSearchMethod", "UseTolerance");
    model.component("comp1").physics("fpt").feature("df1").setIndex("sr", 0.03, 0);
    model.component("comp1").physics("fpt").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("fpt").feature("relg1")
         .setIndex("x0", "range(halfWidth/(2*Np),(halfWidth*(1-1/(2*Np))-(halfWidth/(2*Np)))/(Np-1),halfWidth*(1-1/(2*Np)))", 0);
    model.component("comp1").physics("fpt").feature("relg1").setIndex("x0", "height/2", 1);
    model.component("comp1").physics("fpt").feature("relg1").set("v0", new String[]{"u", "v", "0"});

    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "halfWidth", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "halfWidth", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "St", 0);
    model.study("std2").feature("param").setIndex("plistarr", "0.2 1 5 15 25 125", 0);
    model.study("std2").feature("time").set("tlist", "0 t1 t2");
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol3");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_fpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "fpt");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "part1");
    model.result("pg5").setIndex("looplevel", 3, 0);
    model.result("pg5").setIndex("looplevel", 6, 1);
    model.result("pg5").label("\u7c92\u5b50\u8f68\u8ff9 (fpt)");
    model.result("pg5").create("traj1", "ParticleTrajectories");
    model.result("pg5").feature("traj1").set("pointtype", "point");
    model.result("pg5").feature("traj1").set("linetype", "none");
    model.result("pg5").feature("traj1").create("col1", "Color");
    model.result("pg5").feature("traj1").feature("col1").set("expr", "fpt.V");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "part1");
    model.result("pg6").label("\u6570\u5bc6\u5ea6\uff0cSt = 0.2");
    model.result("pg6").setIndex("looplevelinput", "manual", 1);
    model.result("pg6").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg6").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").setIndex("looplevel", new int[]{2, 3}, 0);
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "St = 0.2");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "y<sup>+</sup>");
    model.result("pg6").create("hist1", "Histogram");
    model.result("pg6").feature("hist1").set("markerpos", "datapoints");
    model.result("pg6").feature("hist1").set("linewidth", "preference");
    model.result("pg6").feature("hist1").set("expr", "fpt.df1.yplus");
    model.result("pg6").feature("hist1").set("number", 50);
    model.result("pg6").feature("hist1").set("legend", true);
    model.result("pg6").feature("hist1").set("legendmethod", "manual");
    model.result("pg6").feature("hist1").setIndex("legends", "t<sub>1</sub>", 0);
    model.result("pg6").feature("hist1").setIndex("legends", "t<sub>2</sub>", 1);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").set("xlog", true);
    model.result("pg6").set("ylog", true);
    model.result("pg6").set("axislimits", true);
    model.result("pg6").set("xmin", 1.4);
    model.result("pg6").set("xmax", 150);
    model.result("pg6").set("ymin", 50);
    model.result("pg6").set("ymax", "1e4");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u6570\u5bc6\u5ea6\uff0cSt = 1");
    model.result("pg7").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg7").set("title", "St = 1");
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u6570\u5bc6\u5ea6\uff0cSt = 5");
    model.result("pg8").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg8").set("title", "St = 5");
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u6570\u5bc6\u5ea6\uff0cSt = 15");
    model.result("pg9").setIndex("looplevel", new int[]{4}, 1);
    model.result("pg9").set("title", "St = 15");
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u6570\u5bc6\u5ea6\uff0cSt = 25");
    model.result("pg10").setIndex("looplevel", new int[]{5}, 1);
    model.result("pg10").set("title", "St = 25");
    model.result("pg10").run();
    model.result().duplicate("pg11", "pg10");
    model.result("pg11").run();
    model.result("pg11").label("\u6570\u5bc6\u5ea6\uff0cSt = 125");
    model.result("pg11").setIndex("looplevel", new int[]{6}, 1);
    model.result("pg11").set("title", "St = 125");
    model.result("pg11").run();

    model.title("\u69fd\u9053\u6e4d\u6d41\u4e2d\u7684\u91cd\u9897\u7c92\u5f25\u6563");

    model
         .description("\u5728\u6b64\u57fa\u51c6\u6a21\u578b\u4e2d\uff0c\u56fa\u4f53\u9897\u7c92\u5728\u5145\u5206\u53d1\u5c55\u7684\u6e4d\u6d41\u901a\u9053\u6d41\u4e2d\u91ca\u653e\uff0c\u5e76\u53d7\u5230\u66f3\u529b\u4f5c\u7528\uff0c\u5176\u4e2d\u5305\u62ec\u6765\u81ea\u6d41\u4f53\u6e4d\u6d41\u7684\u8d21\u732e\u3002\u6d41\u4f53\u6e4d\u6d41\u901a\u8fc7\u8fde\u7eed\u968f\u673a\u6e38\u8d70 (CRW) \u6a21\u578b\u5b9e\u73b0\u3002\u7531\u4e8e\u901a\u9053\u4e2d\u7684\u6e4d\u6d41\u4e3a\u5404\u5411\u5f02\u6027\uff0c\u60ef\u6027\u8f83\u5927\u7684\u9897\u7c92\u5f80\u5f80\u805a\u96c6\u5728\u901a\u9053\u58c1\u9644\u8fd1\uff0c\u800c\u975e\u5e38\u5c0f\u7684\u9897\u7c92\u5728\u6574\u4e2a\u901a\u9053\u6a2a\u622a\u9762\u4e0a\u4fdd\u6301\u5747\u5300\u5206\u5e03\u3002\u6b64\u4f8b\u6bd4\u8f83\u4e86\u591a\u79cd\u4e0d\u540c\u9897\u7c92\u65af\u6258\u514b\u65af\u6570\u7684\u91ca\u653e\u6761\u4ef6\u4e0b\uff0c\u4f4d\u7f6e\u4e0b\u6e38\u7684\u9897\u7c92\u6570\u5bc6\u5ea6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();

    model.label("flow_channel_turbulent_dispersion.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
