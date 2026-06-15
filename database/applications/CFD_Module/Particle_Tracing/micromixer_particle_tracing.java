/*
 * micromixer_particle_tracing.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class micromixer_particle_tracing {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Particle_Tracing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("fpt", "FluidParticleTracing", "geom1");

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("rotationalVelocityExpression", "generalRevolutionsPerTime");
    model.component("comp1").common("rot1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/fpt", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 3);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", 2.75);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c2");
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.2, 5.25});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{5.25, 0.2});
    model.component("comp1").geom("geom1").feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new double[]{1, 0.5});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{-3.4, 0});
    model.component("comp1").geom("geom1").feature("r3").set("base", "center");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("r3");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "90 180 270");
    model.component("comp1").geom("geom1").feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("dif1", "r3", "rot1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("c2");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("r1", "r2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5bf9\u8fb9\u754c");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(15, 16, 17, 18, 33, 34, 35, 36);

    model.component("comp1").func().create("rm1", "Ramp");
    model.component("comp1").func("rm1").set("slope", 100);
    model.component("comp1").func("rm1").set("cutoffactive", true);
    model.component("comp1").func("rm1").set("smoothzonecutoffactive", true);
    model.component("comp1").func("rm1").set("smoothzonecutoff", 0.001);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("uin", "0.02[m/s]*rm1(t[1/s])");
    model.component("comp1").variable("var1").descr("uin", "\u5165\u53e3\u901f\u5ea6");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1E3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"1E-3"});

    model.component("comp1").common("rot1").selection().set(2);
    model.component("comp1").common("rot1").set("revolutionsPerTime", 1);

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1, 5, 12);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "uin");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(7);
    model.component("comp1").physics("fpt").feature("wall1").set("WallCondition", "Bounce");
    model.component("comp1").physics("fpt").create("df1", "DragForce", 2);
    model.component("comp1").physics("fpt").feature("df1").selection().all();
    model.component("comp1").physics("fpt").feature("df1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("fpt").feature("df1").set("mu_mat", "root.comp1.spf.mu");
    model.component("comp1").physics("fpt").feature("df1").set("IncludeVirtualMassAndPressureGradientForces", true);
    model.component("comp1").physics("fpt").create("inl1", "Inlet", 1);
    model.component("comp1").physics("fpt").feature("inl1").selection().set(1);
    model.component("comp1").physics("fpt").feature("inl1").set("InitialPosition", "UniformDistribution");
    model.component("comp1").physics("fpt").feature("inl1").setIndex("N", 50, 0);
    model.component("comp1").physics("fpt").feature("inl1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("fpt").feature("inl1").setIndex("rt", "range(0,0.05,1)", 0);
    model.component("comp1").physics("fpt").feature().duplicate("inl2", "inl1");
    model.component("comp1").physics("fpt").feature("inl2").selection().set(5);
    model.component("comp1").physics("fpt").feature().duplicate("inl3", "inl2");
    model.component("comp1").physics("fpt").feature("inl3").selection().set(12);
    model.component("comp1").physics("fpt").create("out1", "Outlet", 1);
    model.component("comp1").physics("fpt").feature("out1").selection().set(7);
    model.component("comp1").physics("fpt").feature("pp1").set("rhop_mat", "userdef");
    model.component("comp1").physics("fpt").feature("pp1").set("dp", "10[um]");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().all();
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.02,2)");
    model.study("std1").feature("time").set("usertol", true);
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
    model.result("pg3").setIndex("looplevel", 101, 0);
    model.result("pg3").label("\u7c92\u5b50\u8f68\u8ff9 (fpt)");
    model.result("pg3").create("traj1", "ParticleTrajectories");
    model.result("pg3").feature("traj1").set("pointtype", "point");
    model.result("pg3").feature("traj1").set("linetype", "none");
    model.result("pg3").feature("traj1").create("col1", "Color");
    model.result("pg3").feature("traj1").feature("col1").set("expr", "fpt.V");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("traj1").feature("col1").set("expr", "fpt.prf");
    model.result("pg3").feature("traj1").feature("col1").set("descr", "\u7c92\u5b50\u91ca\u653e\u7279\u5f81");
    model.result("pg3").run();

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 1);
    model.component("comp1").view("view1").hideEntities("hide1").named("sel1");

    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 21, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 31, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 41, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 51, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 101, 0);
    model.result("pg3").run();

    model.title("\u5fae\u6df7\u5408\u5668\u4e2d\u7684\u9897\u7c92\u8ddf\u8e2a");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u65cb\u8f6c\u5fae\u6df7\u5408\u5668\u4e2d\u9897\u7c92\u7684\u6df7\u5408\u8fc7\u7a0b\u3002\u6df7\u5408\u5668\u5305\u542b\u4e09\u4e2a\u4e0d\u540c\u7684\u5165\u53e3\u548c\u4e00\u4e2a\u51fa\u53e3\u3002\u5176\u4e2d\u4f7f\u7528\u201c\u65cb\u8f6c\u673a\u68b0\u201d\u63a5\u53e3\u5bf9\u6d41\u4f53\u6d41\u52a8\u5efa\u6a21\uff0c\u4f7f\u7528\u201c\u6d41\u4f53\u6d41\u52a8\u9897\u7c92\u8ddf\u8e2a\u201d\u63a5\u53e3\u8ba1\u7b97\u9897\u7c92\u8f68\u8ff9\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("micromixer_particle_tracing.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
