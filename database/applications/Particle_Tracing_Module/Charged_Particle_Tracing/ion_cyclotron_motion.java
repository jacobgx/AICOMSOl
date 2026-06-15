/*
 * ion_cyclotron_motion.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:50 by COMSOL 6.3.0.290. */
public class ion_cyclotron_motion {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Charged_Particle_Tracing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("pt", "MathParticle", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/pt", true);

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "2e-3");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "2e-3");
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("mp", "0.04[kg/mol]/N_A_const");
    model.param().descr("mp", "\u79bb\u5b50\u8d28\u91cf");
    model.param().set("B", "2[T]");
    model.param().descr("B", "\u78c1\u901a\u5bc6\u5ea6");
    model.param().set("v0", "2E3[m/s]");
    model.param().descr("v0", "\u5782\u76f4\u4e8e\u78c1\u573a\u7684\u7c92\u5b50\u901f\u5ea6");
    model.param().set("rL", "mp*v0/(e_const*B)");
    model.param().descr("rL", "\u62c9\u83ab\u5c14\u534a\u5f84");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Ax", "1[Wb/m]*y[1/m]");
    model.component("comp1").variable("var1").descr("Ax", "\u78c1\u77e2\u52bf\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1").set("Ay", "-1[Wb/m]*x[1/m]");
    model.component("comp1").variable("var1").descr("Ay", "\u78c1\u77e2\u52bf\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var1").set("Az", "0[Wb/m]");
    model.component("comp1").variable("var1").descr("Az", "\u78c1\u77e2\u52bf\uff0cz \u5206\u91cf");
    model.component("comp1").variable("var1").set("Bx", "d(Az,y)-d(Ay,z)");
    model.component("comp1").variable("var1").descr("Bx", "\u78c1\u901a\u5bc6\u5ea6\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1").set("By", "d(Ax,z)-d(Az,x)");
    model.component("comp1").variable("var1").descr("By", "\u78c1\u901a\u5bc6\u5ea6\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var1").set("Bz", "d(Ay,x)-d(Ax,y)");
    model.component("comp1").variable("var1").descr("Bz", "\u78c1\u901a\u5bc6\u5ea6\uff0cz \u5206\u91cf");

    model.component("comp1").physics("pt").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("pt").feature("relg1").set("v0", new String[]{"v0", "0", "1e2"});
    model.component("comp1").physics("pt").prop("Formulation").setIndex("Formulation", "Lagrangian", 0);
    model.component("comp1").physics("pt").feature("pp1").set("mp", "mp");
    model.component("comp1").physics("pt").feature("pp1").set("L", "pt.Ep+e_const*(pt.vx*Ax+pt.vy*Ay+pt.vz*Az)");

    model.component("comp1").mesh("mesh1").autoMeshSize(8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,5.0e-8,2.0e-5)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", 1.0E-6);
    model.study("std1").label("\u62c9\u683c\u6717\u65e5\u7814\u7a76");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol1");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_pt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "pt");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "part1");
    model.result("pg1").setIndex("looplevel", 401, 0);
    model.result("pg1").label("\u7c92\u5b50\u8f68\u8ff9 (pt)");
    model.result("pg1").create("traj1", "ParticleTrajectories");
    model.result("pg1").feature("traj1").set("pointtype", "point");
    model.result("pg1").feature("traj1").set("linetype", "none");
    model.result("pg1").feature("traj1").create("col1", "Color");
    model.result("pg1").feature("traj1").feature("col1").set("expr", "pt.V");
    model.result("pg1").run();
    model.result("pg1").label("\u62c9\u683c\u6717\u65e5\u7ed3\u679c");
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("traj1").set("linetype", "ribbon");
    model.result("pg1").feature("traj1").set("widthscaleactive", true);
    model.result("pg1").feature("traj1").set("widthscale", "4E-5");
    model.result("pg1").feature("traj1").set("pointtype", "none");
    model.result("pg1").run();
    model.result("pg1").feature("traj1").feature("col1").set("expr", "qy/2");
    model.result("pg1").feature("traj1").feature("col1").set("colortable", "Inferno");
    model.result("pg1").run();
    model.result().numerical().create("par1", "Particle");
    model.result().numerical("par1").setIndex("looplevelinput", "first", 0);
    model.result().numerical("par1").set("expr", "rL");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u7c92\u5b50\u8ba1\u7b97 1");
    model.result().numerical("par1").set("table", "tbl1");
    model.result().numerical("par1").setResult();
    model.result().numerical().create("par2", "Particle");
    model.result().numerical("par2").setIndex("looplevelinput", "first", 0);
    model.result().numerical("par2").set("expr", "timemax(0,2E-5,qy)/2");
    model.result().numerical("par2").set("table", "tbl1");
    model.result().numerical("par2").appendResult();

    model.component("comp1").physics("pt").prop("Formulation").setIndex("Formulation", "Hamiltonian", 0);
    model.component("comp1").physics("pt").feature("pp1")
         .set("H", "((px-e_const*Ax)^2+(py-e_const*Ay)^2+(pz-e_const*Az)^2)/(2*pt.mp)");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/pt", true);
    model.study("std2").feature("time").set("tlist", "range(0,5.0e-8,2.0e-5)");
    model.study("std2").feature("time").set("usertol", true);
    model.study("std2").feature("time").set("rtol", 1.0E-6);
    model.study("std2").label("\u54c8\u5bc6\u987f\u7814\u7a76");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("part2", "Particle");
    model.result().dataset("part2").set("solution", "sol2");
    model.result().dataset("part2").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part2").set("geom", "geom1");
    model.result().dataset("part2").set("pgeom", "pgeom_pt");
    model.result().dataset("part2").set("pgeomspec", "fromphysics");
    model.result().dataset("part2").set("physicsinterface", "pt");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "part2");
    model.result("pg2").setIndex("looplevel", 401, 0);
    model.result("pg2").label("\u7c92\u5b50\u8f68\u8ff9 (pt)");
    model.result("pg2").create("traj1", "ParticleTrajectories");
    model.result("pg2").feature("traj1").set("pointtype", "point");
    model.result("pg2").feature("traj1").set("linetype", "none");
    model.result("pg2").feature("traj1").create("col1", "Color");
    model.result("pg2").feature("traj1").feature("col1").set("expr", "pt.V");
    model.result("pg2").run();
    model.result("pg2").label("\u54c8\u5bc6\u987f\u7ed3\u679c");
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result("pg2").feature("traj1").set("linetype", "ribbon");
    model.result("pg2").feature("traj1").set("widthscaleactive", true);
    model.result("pg2").feature("traj1").set("widthscale", "4E-5");
    model.result("pg2").feature("traj1").set("pointtype", "none");
    model.result("pg2").run();
    model.result("pg2").feature("traj1").feature("col1").set("expr", "qy/2");
    model.result("pg2").feature("traj1").feature("col1").set("colortable", "Inferno");
    model.result("pg2").run();
    model.result().numerical().create("par3", "Particle");
    model.result().numerical("par3").set("data", "part2");
    model.result().numerical("par3").setIndex("looplevelinput", "first", 0);
    model.result().numerical("par3").set("expr", "timemax(0,2E-5,qy)/2");
    model.result().numerical("par3").set("table", "tbl1");
    model.result().numerical("par3").appendResult();

    model.component("comp1").physics("pt").prop("Formulation").setIndex("Formulation", "Newtonian", 0);
    model.component("comp1").physics("pt").create("for1", "Force", 3);
    model.component("comp1").physics("pt").feature("for1").selection().all();
    model.component("comp1").physics("pt").feature("for1")
         .set("F", new String[]{"e_const*(Bz*pt.vy-By*pt.vz)", "e_const*(-Bz*pt.vx+Bx*pt.vz)", "e_const*(By*pt.vx-Bx*pt.vy)"});

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/pt", true);
    model.study("std3").feature("time").set("tlist", "range(0,5.0e-8,2.0e-5)");
    model.study("std3").feature("time").set("usertol", true);
    model.study("std3").feature("time").set("rtol", 1.0E-6);
    model.study("std3").label("\u725b\u987f\u7814\u7a76");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().create("part3", "Particle");
    model.result().dataset("part3").set("solution", "sol3");
    model.result().dataset("part3").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part3").set("geom", "geom1");
    model.result().dataset("part3").set("pgeom", "pgeom_pt");
    model.result().dataset("part3").set("pgeomspec", "fromphysics");
    model.result().dataset("part3").set("physicsinterface", "pt");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "part3");
    model.result("pg3").setIndex("looplevel", 401, 0);
    model.result("pg3").label("\u7c92\u5b50\u8f68\u8ff9 (pt)");
    model.result("pg3").create("traj1", "ParticleTrajectories");
    model.result("pg3").feature("traj1").set("pointtype", "point");
    model.result("pg3").feature("traj1").set("linetype", "none");
    model.result("pg3").feature("traj1").create("col1", "Color");
    model.result("pg3").feature("traj1").feature("col1").set("expr", "pt.V");
    model.result("pg3").run();
    model.result("pg3").label("\u725b\u987f\u7ed3\u679c");
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result("pg3").feature("traj1").set("linetype", "ribbon");
    model.result("pg3").feature("traj1").set("widthscaleactive", true);
    model.result("pg3").feature("traj1").set("widthscale", "4E-5");
    model.result("pg3").feature("traj1").set("pointtype", "none");
    model.result("pg3").run();
    model.result("pg3").feature("traj1").feature("col1").set("expr", "qy/2");
    model.result("pg3").feature("traj1").feature("col1").set("colortable", "Inferno");
    model.result("pg3").run();
    model.result().numerical().create("par4", "Particle");
    model.result().numerical("par4").set("data", "part3");
    model.result().numerical("par4").setIndex("looplevelinput", "first", 0);
    model.result().numerical("par4").set("expr", "timemax(0,2E-5,qy)/2");
    model.result().numerical("par4").set("table", "tbl1");
    model.result().numerical("par4").appendResult();

    model.component("comp1").physics("pt").prop("Formulation").setIndex("Formulation", "NewtonianFirstOrder", 0);

    model.study().create("std4");
    model.study("std4").create("time", "Transient");
    model.study("std4").feature("time").setSolveFor("/physics/pt", true);
    model.study("std4").feature("time").set("tlist", "range(0,5.0e-8,2.0e-5)");
    model.study("std4").feature("time").set("usertol", true);
    model.study("std4").feature("time").set("rtol", 1.0E-6);
    model.study("std4").label("\u725b\u987f\uff0c\u4e00\u9636\u7814\u7a76");
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().dataset().create("part4", "Particle");
    model.result().dataset("part4").set("solution", "sol4");
    model.result().dataset("part4").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part4").set("geom", "geom1");
    model.result().dataset("part4").set("pgeom", "pgeom_pt");
    model.result().dataset("part4").set("pgeomspec", "fromphysics");
    model.result().dataset("part4").set("physicsinterface", "pt");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "part4");
    model.result("pg4").setIndex("looplevel", 401, 0);
    model.result("pg4").label("\u7c92\u5b50\u8f68\u8ff9 (pt)");
    model.result("pg4").create("traj1", "ParticleTrajectories");
    model.result("pg4").feature("traj1").set("pointtype", "point");
    model.result("pg4").feature("traj1").set("linetype", "none");
    model.result("pg4").feature("traj1").create("col1", "Color");
    model.result("pg4").feature("traj1").feature("col1").set("expr", "pt.V");
    model.result("pg4").run();
    model.result("pg4").label("\u725b\u987f\uff0c\u4e00\u9636\u7ed3\u679c");
    model.result("pg4").set("edges", false);
    model.result("pg4").run();
    model.result("pg4").feature("traj1").set("linetype", "ribbon");
    model.result("pg4").feature("traj1").set("widthscaleactive", true);
    model.result("pg4").feature("traj1").set("widthscale", "4E-5");
    model.result("pg4").feature("traj1").set("pointtype", "none");
    model.result("pg4").run();
    model.result("pg4").feature("traj1").feature("col1").set("expr", "qy/2");
    model.result("pg4").feature("traj1").feature("col1").set("colortable", "Inferno");
    model.result("pg4").run();
    model.result().numerical().create("par5", "Particle");
    model.result().numerical("par5").set("data", "part4");
    model.result().numerical("par5").setIndex("looplevelinput", "first", 0);
    model.result().numerical("par5").set("expr", "timemax(0,2E-5,qy)/2");
    model.result().numerical("par5").set("table", "tbl1");
    model.result().numerical("par5").appendResult();
    model.result("pg3").run();

    model.title("\u79bb\u5b50\u56de\u65cb\u8fd0\u52a8");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u725b\u987f\uff08\u4e00\u9636\u548c\u4e8c\u9636\uff09\u3001\u62c9\u683c\u6717\u65e5\u548c\u54c8\u5bc6\u987f\u516c\u5f0f\u6765\u8ba1\u7b97\u5e26\u7535\u7c92\u5b50\u5728\u5747\u5300\u78c1\u573a\u4e2d\u7684\u8fd0\u52a8\u8f68\u8ff9\u3002\u8be5\u57fa\u51c6\u6a21\u578b\u8ba1\u7b97\u62c9\u83ab\u5c14\u534a\u5f84\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u89e3\u6790\u89e3\u8fdb\u884c\u6bd4\u8f83\u3002\u6240\u6709\u516c\u5f0f\u7684\u8ba1\u7b97\u7ed3\u679c\u90fd\u76f8\u540c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("ion_cyclotron_motion.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
