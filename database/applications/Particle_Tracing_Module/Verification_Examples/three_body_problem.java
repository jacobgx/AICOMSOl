/*
 * three_body_problem.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:56 by COMSOL 6.3.0.290. */
public class three_body_problem {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("pt", "MathParticle", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/pt", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("mass", "1.989e30[kg]", "\u661f\u4f53\u8d28\u91cf");
    model.param().set("gravity", "G_const", "\u5f15\u529b\u5e38\u6570");
    model.param().set("au", "149597870700[m]", "\u5929\u6587\u5355\u4f4d");
    model.param().set("distance", "30*au", "\u661f\u4f53\u4e4b\u95f4\u7684\u521d\u59cb\u8ddd\u79bb");
    model.param().set("tilt", "-14.06884013142[deg]", "8 \u5b57\u5f62\u6784\u578b\u7684\u503e\u89d2");
    model.param().set("x1", "distance*cos(tilt)", "\u661f\u4f53 1 \u7684\u521d\u59cb x \u5750\u6807");
    model.param().set("y1", "distance*sin(tilt)", "\u661f\u4f53 1 \u7684\u521d\u59cb y \u5750\u6807");
    model.param().set("x2", "-x1", "\u661f\u4f53 2 \u7684\u521d\u59cb x \u5750\u6807");
    model.param().set("y2", "-y1", "\u661f\u4f53 2 \u7684\u521d\u59cb y \u5750\u6807");
    model.param().set("x3", "0", "\u661f\u4f53 3 \u7684\u521d\u59cb x \u5750\u6807");
    model.param().set("y3", "0", "\u661f\u4f53 3 \u7684\u521d\u59cb y \u5750\u6807");
    model.param().set("u1", "-u3/2", "\u661f\u4f53 1 \u7684\u521d\u59cb\u901f\u5ea6\uff0cx \u5206\u91cf");
    model.param().set("v1", "-v3/2", "\u661f\u4f53 1 \u7684\u521d\u59cb\u901f\u5ea6\uff0cy \u5206\u91cf");
    model.param().set("u2", "-u3/2", "\u661f\u4f53 2 \u7684\u521d\u59cb\u901f\u5ea6\uff0cx \u5206\u91cf");
    model.param().set("v2", "-v3/2", "\u661f\u4f53 2 \u7684\u521d\u59cb\u901f\u5ea6\uff0cy \u5206\u91cf");
    model.param()
         .set("u3", "-0.93240737*sqrt(mass*gravity/distance)", "\u661f\u4f53 3 \u7684\u521d\u59cb\u901f\u5ea6\uff0cx \u5206\u91cf");
    model.param()
         .set("v3", "-0.86473146*sqrt(mass*gravity/distance)", "\u661f\u4f53 3 \u7684\u521d\u59cb\u901f\u5ea6\uff0cy \u5206\u91cf");
    model.param()
         .set("T1", "6.32591398*sqrt(distance^3/(mass*gravity))", "\u8f68\u9053\u5468\u671f\uff0c8 \u5b57\u5f62\u6784\u578b");
    model.param().set("x4", "distance*cos(0[deg])", "\u661f\u4f53 4 \u7684\u521d\u59cb x \u5750\u6807");
    model.param().set("y4", "distance*sin(0[deg])", "\u661f\u4f53 4 \u7684\u521d\u59cb y \u5750\u6807");
    model.param().set("x5", "distance*cos(120[deg])", "\u661f\u4f53 5 \u7684\u521d\u59cb x \u5750\u6807");
    model.param().set("y5", "distance*sin(120[deg])", "\u661f\u4f53 5 \u7684\u521d\u59cb y \u5750\u6807");
    model.param().set("x6", "distance*cos(240[deg])", "\u661f\u4f53 6 \u7684\u521d\u59cb x \u5750\u6807");
    model.param().set("y6", "distance*sin(240[deg])", "\u661f\u4f53 6 \u7684\u521d\u59cb y \u5750\u6807");
    model.param()
         .set("V_Lagrange", "sqrt(gravity*mass/distance)/(3^0.25)", "\u521d\u59cb\u901f\u5ea6\uff0c\u62c9\u683c\u6717\u65e5\u914d\u7f6e");
    model.param()
         .set("u4", "-V_Lagrange*sin(0[deg])", "\u661f\u4f53 4 \u7684\u521d\u59cb\u901f\u5ea6\uff0cx \u5206\u91cf");
    model.param()
         .set("v4", "V_Lagrange*cos(0[deg])", "\u661f\u4f53 4 \u7684\u521d\u59cb\u901f\u5ea6\uff0cy \u5206\u91cf");
    model.param()
         .set("u5", "-V_Lagrange*sin(120[deg])", "\u661f\u4f53 5 \u7684\u521d\u59cb\u901f\u5ea6\uff0cx \u5206\u91cf");
    model.param()
         .set("v5", "V_Lagrange*cos(120[deg])", "\u661f\u4f53 5 \u7684\u521d\u59cb\u901f\u5ea6\uff0cy \u5206\u91cf");
    model.param()
         .set("u6", "-V_Lagrange*sin(240[deg])", "\u661f\u4f53 6 \u7684\u521d\u59cb\u901f\u5ea6\uff0cx \u5206\u91cf");
    model.param()
         .set("v6", "V_Lagrange*cos(240[deg])", "\u661f\u4f53 6 \u7684\u521d\u59cb\u901f\u5ea6\uff0cy \u5206\u91cf");
    model.param()
         .set("T2", "2*pi*distance/V_Lagrange", "\u8f68\u9053\u5468\u671f\uff0c\u62c9\u683c\u6717\u65e5\u914d\u7f6e");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("KE", "pt.Epsum");
    model.component("comp1").variable("var1").descr("KE", "\u52a8\u80fd");
    model.component("comp1").variable("var1").set("arg1", "pt.pidx>dest(pt.pidx)");
    model.component("comp1").variable("var1").descr("arg1", "\u53d8\u5143 1");
    model.component("comp1").variable("var1").set("arg2", "-gravity*pt.mp*dest(pt.mp)/pt.r");
    model.component("comp1").variable("var1").descr("arg2", "\u53d8\u5143 2");
    model.component("comp1").variable("var1").set("ifExpr", "if(arg1,arg2,0)");
    model.component("comp1").variable("var1").descr("ifExpr", "\u6761\u4ef6\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var1").set("PE", "pt.sum(pt.sum(ifExpr))");
    model.component("comp1").variable("var1").descr("PE", "\u52bf\u80fd");
    model.component("comp1").variable("var1").set("TE", "KE+PE");
    model.component("comp1").variable("var1").descr("TE", "\u603b\u80fd\u91cf");
    model.component("comp1").variable("var1").set("TE_err", "(TE-at(0,TE))/at(0,TE)");
    model.component("comp1").variable("var1").descr("TE_err", "\u603b\u80fd\u91cf\uff0c\u76f8\u5bf9\u8bef\u5dee");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "2*distance");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("pt").prop("Formulation").setIndex("Formulation", "NewtonianFirstOrder", 0);
    model.component("comp1").physics("pt").feature("pp1").set("mp", "mass");
    model.component("comp1").physics("pt").create("ppi1", "ParticleParticleInteraction", 2);
    model.component("comp1").physics("pt").feature("ppi1").selection().all();
    model.component("comp1").physics("pt").feature("ppi1").set("InteractionForce", "UserDefined");
    model.component("comp1").physics("pt").feature("ppi1")
         .set("Fu", new String[]{"gravity*pt.mp*dest(pt.mp)*(qx-dest(qx))/pt.r^3", "gravity*pt.mp*dest(pt.mp)*(qy-dest(qy))/pt.r^3", "0"});
    model.component("comp1").physics("pt").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("pt").feature("relg1").setIndex("x0", "x1", 0);
    model.component("comp1").physics("pt").feature("relg1").setIndex("x0", "y1", 1);
    model.component("comp1").physics("pt").feature("relg1").set("v0", new String[]{"u1", "v1", "0"});
    model.component("comp1").physics("pt").create("relg2", "ReleaseGrid", -1);
    model.component("comp1").physics("pt").feature("relg2").setIndex("x0", "x2", 0);
    model.component("comp1").physics("pt").feature("relg2").setIndex("x0", "y2", 1);
    model.component("comp1").physics("pt").feature("relg2").set("v0", new String[]{"u2", "v2", "0"});
    model.component("comp1").physics("pt").create("relg3", "ReleaseGrid", -1);
    model.component("comp1").physics("pt").feature("relg3").setIndex("x0", "x3", 0);
    model.component("comp1").physics("pt").feature("relg3").setIndex("x0", "y3", 1);
    model.component("comp1").physics("pt").feature("relg3").set("v0", new String[]{"u3", "v3", "0"});
    model.component("comp1").physics("pt").create("relg4", "ReleaseGrid", -1);
    model.component("comp1").physics("pt").feature("relg4").setIndex("x0", "x4", 0);
    model.component("comp1").physics("pt").feature("relg4").setIndex("x0", "y4", 1);
    model.component("comp1").physics("pt").feature("relg4").set("v0", new String[]{"u4", "v4", "0"});
    model.component("comp1").physics("pt").create("relg5", "ReleaseGrid", -1);
    model.component("comp1").physics("pt").feature("relg5").setIndex("x0", "x5", 0);
    model.component("comp1").physics("pt").feature("relg5").setIndex("x0", "y5", 1);
    model.component("comp1").physics("pt").feature("relg5").set("v0", new String[]{"u5", "v5", "0"});
    model.component("comp1").physics("pt").create("relg6", "ReleaseGrid", -1);
    model.component("comp1").physics("pt").feature("relg6").setIndex("x0", "x6", 0);
    model.component("comp1").physics("pt").feature("relg6").setIndex("x0", "y6", 1);
    model.component("comp1").physics("pt").feature("relg6").set("v0", new String[]{"u6", "v6", "0"});

    model.study("std1").label("\u7814\u7a76 1\uff1a\u516b\u5b57\u6784\u578b");
    model.study("std1").feature("time").set("tlist", "range(0,T1/100,10*T1)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "1E-10");
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"pt/relg4", "pt/relg5", "pt/relg6"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol1");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_pt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "pt");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "part1");
    model.result("pg1").setIndex("looplevel", 1001, 0);
    model.result("pg1").label("\u7c92\u5b50\u8f68\u8ff9 (pt)");
    model.result("pg1").create("traj1", "ParticleTrajectories");
    model.result("pg1").feature("traj1").set("pointtype", "point");
    model.result("pg1").feature("traj1").set("linetype", "none");
    model.result("pg1").feature("traj1").create("col1", "Color");
    model.result("pg1").feature("traj1").feature("col1").set("expr", "pt.V");
    model.result("pg1").run();
    model.result("pg1").label("\u516b\u5b57\u6784\u578b");
    model.result("pg1").run();
    model.result("pg1").feature("traj1").set("pointtype", "comettail");
    model.result("pg1").feature("traj1").set("linetype", "line");
    model.result("pg1").run();
    model.result("pg1").feature("traj1").feature("col1").set("colortable", "RainbowLight");
    model.result("pg1").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg1");
    model.result().export("anim1").run();
    model.result().export("anim1").set("maxframes", 300);
    model.result().export("anim1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u68c0\u67e5\u80fd\u91cf\u5b88\u6052");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "TE_err", 0);
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/pt", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u62c9\u683c\u6717\u65e5\u6784\u578b");
    model.study("std2").feature("time").set("tlist", "range(0,T2/100,10*T2)");
    model.study("std2").feature("time").set("usertol", true);
    model.study("std2").feature("time").set("rtol", "1E-10");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"pt/relg1", "pt/relg2", "pt/relg3"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("part2", "Particle");
    model.result().dataset("part2").set("solution", "sol2");
    model.result().dataset("part2").set("posdof", new String[]{"comp1.qx", "comp1.qy"});
    model.result().dataset("part2").set("geom", "geom1");
    model.result().dataset("part2").set("pgeom", "pgeom_pt");
    model.result().dataset("part2").set("pgeomspec", "fromphysics");
    model.result().dataset("part2").set("physicsinterface", "pt");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "part2");
    model.result("pg3").setIndex("looplevel", 1001, 0);
    model.result("pg3").label("\u7c92\u5b50\u8f68\u8ff9 (pt)");
    model.result("pg3").create("traj1", "ParticleTrajectories");
    model.result("pg3").feature("traj1").set("pointtype", "point");
    model.result("pg3").feature("traj1").set("linetype", "none");
    model.result("pg3").feature("traj1").create("col1", "Color");
    model.result("pg3").feature("traj1").feature("col1").set("expr", "pt.V");
    model.result("pg3").run();
    model.result("pg3").label("\u62c9\u683c\u6717\u65e5\u6784\u578b");
    model.result("pg3").run();
    model.result("pg3").feature("traj1").set("linetype", "line");
    model.result("pg3").feature("traj1").set("pointtype", "comettail");
    model.result("pg3").run();
    model.result("pg3").feature("traj1").feature("col1").set("colortable", "RainbowLight");
    model.result("pg3").run();
    model.result().export().create("anim2", "Animation");
    model.result().export("anim2").set("target", "player");
    model.result().export("anim2").set("plotgroup", "pg3");
    model.result().export("anim2").run();
    model.result().export("anim2").set("maxframes", 300);
    model.result().export("anim2").run();

    model.title("\u4e09\u4f53\u95ee\u9898");

    model
         .description("\u7ecf\u5178\u7684\u4e09\u4f53\u95ee\u9898\u6d89\u53ca\u9884\u6d4b\u4e09\u70b9\u5904\u8d28\u91cf\u4f53\u5728\u76f8\u4e92\u5f15\u529b\u4f5c\u7528\u4e0b\u7684\u8f68\u8ff9\u3002\u901a\u5e38\uff0c\u4e09\u4f53\u95ee\u9898\u6ca1\u6709\u5c01\u95ed\u7684\u89e3\u6790\u89e3\uff0c\u4f46\u5728\u521d\u59cb\u6761\u4ef6\u5df2\u77e5\u7684\u60c5\u51b5\u4e0b\uff0c\u53ef\u4ee5\u7528\u6570\u503c\u65b9\u6cd5\u8fdb\u884c\u6c42\u89e3\u3002\u672c\u4f8b\u6a21\u62df\u4e09\u4f53\u95ee\u9898\u7684\u4e24\u4e2a\u5468\u671f\u89e3\uff1a\u516b\u5b57\u5f62\u6784\u578b\u548c\u62c9\u683c\u6717\u65e5\u6784\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("three_body_problem.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
