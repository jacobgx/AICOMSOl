/*
 * inertial_focusing.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:54 by COMSOL 6.3.0.290. */
public class inertial_focusing {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("dp", "1.21[mm]", "\u7c92\u5f84");
    model.param().set("muf", "2.98[P]", "\u6d41\u4f53\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("Vav", "44.87[cm/s]", "\u5e73\u5747\u901a\u9053\u901f\u5ea6");
    model.param().set("d", "11.2[mm]", "\u901a\u9053\u5bbd\u5ea6");
    model.param().set("L", "d*1000", "\u901a\u9053\u957f\u5ea6");
    model.param().set("rhof", "1.178[g/cm^3]", "\u6d41\u4f53\u548c\u9897\u7c92\u5bc6\u5ea6");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("dn", "abs(qy/d-0.5)");
    model.component("comp1").variable("var1").descr("dn", "\u8ddd\u4e2d\u5fc3\u7684\u5f52\u4e00\u5316\u8ddd\u79bb");
    model.component("comp1").variable("var1").set("dnave", "fpt.ave(dn)");
    model.component("comp1").variable("var1")
         .descr("dnave", "\u5f52\u4e00\u5316\u8ddd\u79bb\uff0c\u5e73\u5747\u503c");
    model.component("comp1").variable("var1").set("dnstd", "sqrt(fpt.ave(dn^2)-fpt.ave(dn)^2)");
    model.component("comp1").variable("var1")
         .descr("dnstd", "\u5f52\u4e00\u5316\u8ddd\u79bb\uff0c\u6807\u51c6\u5dee");

    model.component("comp1").view("view1").axis().set("viewscaletype", "automatic");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "d"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rhof"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"muf"});

    model.component("comp1").physics("spf").prop("ShapeProperty").set("order_fluid", 2);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "Vav");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(4);
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(3);
    model.component("comp1").physics().create("fpt", "FluidParticleTracing", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/fpt", false);

    model.component("comp1").physics("fpt").feature("pp1").set("rhop_mat", "userdef");
    model.component("comp1").physics("fpt").feature("pp1").set("rhop", "rhof");
    model.component("comp1").physics("fpt").feature("pp1").set("dp", "dp");
    model.component("comp1").physics("fpt").create("inl1", "Inlet", 1);
    model.component("comp1").physics("fpt").feature("inl1").selection().set(1);
    model.component("comp1").physics("fpt").feature("inl1").set("InitialPosition", "Density");
    model.component("comp1").physics("fpt").feature("inl1").setIndex("N", 200, 0);
    model.component("comp1").physics("fpt").feature("inl1").set("dpro", "y>0.1*d&&y<0.9*d");
    model.component("comp1").physics("fpt").feature("inl1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("fpt").create("out1", "Outlet", 1);
    model.component("comp1").physics("fpt").feature("out1").selection().set(4);
    model.component("comp1").physics("fpt").create("lf1", "LiftForce", 2);
    model.component("comp1").physics("fpt").feature("lf1").selection().all();
    model.component("comp1").physics("fpt").feature("lf1").set("LiftLaw", "WallInduced");
    model.component("comp1").physics("fpt").feature("lf1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("fpt").feature("lf1").set("mu_mat", "root.comp1.spf.mu");
    model.component("comp1").physics("fpt").feature("lf1").selection("ParallelBoundary1").set(2);
    model.component("comp1").physics("fpt").feature("lf1").selection("ParallelBoundary2").set(3);
    model.component("comp1").physics("fpt").create("df1", "DragForce", 2);
    model.component("comp1").physics("fpt").feature("df1").selection().all();
    model.component("comp1").physics("fpt").feature("df1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("fpt").feature("df1").set("mu_mat", "root.comp1.spf.mu");
    model.component("comp1").physics("fpt").feature("df1").set("IncludeWallCorrections", true);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("symmetric", true);

    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", false);
    model.study("std1").feature("time").set("tlist", "range(0,0.2,30)");
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
    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol1");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_fpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "fpt");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "part1");
    model.result("pg3").setIndex("looplevel", 151, 0);
    model.result("pg3").label("\u7c92\u5b50\u8f68\u8ff9 (fpt)");
    model.result("pg3").create("traj1", "ParticleTrajectories");
    model.result("pg3").feature("traj1").set("pointtype", "point");
    model.result("pg3").feature("traj1").set("linetype", "none");
    model.result("pg3").feature("traj1").create("col1", "Color");
    model.result("pg3").feature("traj1").feature("col1").set("expr", "fpt.V");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("traj1").set("linetype", "line");
    model.result("pg3").run();
    model.result("pg3").feature("traj1").feature("col1").set("expr", "fpt.vy");
    model.result("pg3").feature("traj1").feature("col1").set("unit", "mm/s");
    model.result("pg3").feature("traj1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5e73\u5747\u503c");
    model.result("pg4").set("data", "part1");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "dnave", 0);
    model.result("pg4").feature("glob1")
         .setIndex("descr", "\u8ddd\u4e2d\u5fc3\u7684\u5f52\u4e00\u5316\u8ddd\u79bb\uff0c\u5e73\u5747\u503c", 0);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u6807\u51c6\u5dee");
    model.result("pg5").set("data", "part1");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("showlegends", false);
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "dnstd", 0);
    model.result("pg5").feature("glob1")
         .setIndex("descr", "\u8ddd\u4e2d\u5fc3\u7684\u5f52\u4e00\u5316\u8ddd\u79bb\uff0c\u6807\u51c6\u5dee", 0);
    model.result("pg5").run();
    model.result("pg3").run();
    model.result("pg3").run();

    model.title("\u4e24\u4e2a\u5e73\u884c\u58c1\u4e4b\u95f4\u7684\u60ef\u6027\u805a\u7126");

    model
         .description("50\u00a0\u591a\u5e74\u6765\uff0c\u4f17\u6240\u5468\u77e5\uff0c\u6d41\u9053\u4e2d\u7684\u4e2d\u6027\u6d6e\u529b\u9897\u7c92\u503e\u5411\u4e8e\u5728\u6d41\u9053\u6a2a\u622a\u9762\u7684\u7279\u5b9a\u4f4d\u7f6e\u805a\u96c6\u3002\u5bf9\u4e8e\u5706\u67f1\u5f62\u7ba1\u6216\u627f\u8f7d\u6cca\u8083\u53f6\u6d41\u7684\u4e24\u4e2a\u5e73\u884c\u5e73\u9762\uff0c\u5e73\u8861\u4f4d\u7f6e\u5206\u522b\u7ea6\u4e3a\u7ba1\u534a\u5f84\u7684 0.6\u00a0\u500d\u5904\uff0c\u6216\u4e0e\u5e73\u884c\u58c1\u7684\u8ddd\u79bb\u7ea6\u4e3a\u6d41\u9053\u5bbd\u5ea6\u7684 0.2\u00a0\u500d\u5904\u3002\u8fd9\u79cd\u73b0\u8c61\u79f0\u4e3a Segre-Silberberg \u6548\u5e94\uff0c\u800c\u534a\u5f84\u4e3a\u7ba1\u534a\u5f84 0.6\u00a0\u500d\u7684\u9897\u7c92\u73af\u6709\u65f6\u88ab\u79f0\u4e3a Segre-Silberberg \u73af\u3002\n\n\u5728\u8fd9\u4e2a\u57fa\u51c6\u6a21\u578b\u4e2d\uff0c\u6211\u4eec\u91cd\u73b0\u4e86\u7531\u4e24\u4e2a\u5e73\u884c\u58c1\u754c\u5b9a\u7684\u6d41\u9053\u7684\u60c5\u51b5\u3002\u5f53\u4e2d\u6027\u6d6e\u529b\u9897\u7c92\u53d7\u5230\u629b\u7269\u7ebf\u6d41\u4f53\u901f\u5ea6\u5206\u5e03\u7684\u4f5c\u7528\u6cbf\u7740\u6d41\u9053\u8fd0\u52a8\u65f6\uff0c\u8fd9\u4e9b\u9897\u7c92\u4f1a\u53d7\u5230\u4e0e\u58c1\u76f8\u5173\u7684\u5347\u529b\u548c\u66f3\u529b\u3002\u5f53\u9897\u7c92\u901a\u8fc7\u6d41\u9053\u65f6\uff0c\u60ef\u6027\u5347\u529b\u4f7f\u5b83\u4eec\u5230\u8fbe\u8ddd\u4e2d\u5fc3 0.3 D \u7684\u5e73\u8861\u4f4d\u7f6e\uff0c\u5176\u4e2d\uff0cD \u662f\u58c1\u95f4\u8ddd\u3002\u8fd9\u4e9b\u5e73\u8861\u4f4d\u7f6e\u4e0e Segre-Silberberg \u6548\u5e94 \u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("inertial_focusing.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
