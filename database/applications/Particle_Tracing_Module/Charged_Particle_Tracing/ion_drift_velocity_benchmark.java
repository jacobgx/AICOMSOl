/*
 * ion_drift_velocity_benchmark.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:50 by COMSOL 6.3.0.290. */
public class ion_drift_velocity_benchmark {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Charged_Particle_Tracing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cpt", "ChargedParticleTracing", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/cpt", true);

    model.param().set("mAr", "0.04[kg/mol]/N_A_const");
    model.param().descr("mAr", "\u6c29\u79bb\u5b50\u8d28\u91cf");
    model.param().set("ND", "3.2956e22[1/m^3]");
    model.param().descr("ND", "\u80cc\u666f\u6c14\u4f53\u6570\u5bc6\u5ea6");
    model.param().set("EoverN", "500");
    model.param().descr("EoverN", "\u6c64\u68ee\u7ea6\u5316\u7535\u573a");
    model.param().set("Ez", "1e-21[V*m^2]*EoverN*ND");
    model.param().descr("Ez", "\u7535\u573a\u5927\u5c0f");
    model.param().set("T0", "300[K]");
    model.param().descr("T0", "\u6c14\u4f53\u6e29\u5ea6");
    model.param().set("maxCol", "1e5");
    model.param().descr("maxCol", "\u7ec8\u6b62\u7684\u6700\u5927\u78b0\u649e\u6570");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "ion_drift_velocity_benchmark_velocity.txt");
    model.component("comp1").func("int1").importData();
    model.component("comp1").func("int1").setIndex("fununit", "m/s", 0);
    model.component("comp1").func("int1").set("funcname", "Vdrift");
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").set("source", "file");
    model.component("comp1").func("int2").set("filename", "ion_drift_velocity_benchmark_temperature.txt");
    model.component("comp1").func("int2").importData();
    model.component("comp1").func("int2").setIndex("fununit", "eV", 0);
    model.component("comp1").func("int2").set("funcname", "ionTemp");
    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "Qm");
    model.component("comp1").func("an1").set("expr", "1.15e-18*x^(-0.1)*(1+0.015/x)^0.6");
    model.component("comp1").func("an1").setIndex("argunit", "eV", 0);
    model.component("comp1").func("an1").set("fununit", "m^2");
    model.component("comp1").func().create("an2", "Analytic");
    model.component("comp1").func("an2").set("funcname", "Qi");
    model.component("comp1").func("an2").set("expr", "2e-19/(x^(0.5)*(1+x))+3e-19*x/(1+x/3)^(2.3)");
    model.component("comp1").func("an2").setIndex("argunit", "eV", 0);
    model.component("comp1").func("an2").set("fununit", "m^2");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 2);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 3);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("cpt").feature("pp1").set("mp", "mAr");
    model.component("comp1").physics("cpt").feature("pp1").set("Z", 1);
    model.component("comp1").physics("cpt").feature("wall1").set("WallCondition", "Bounce");
    model.component("comp1").physics("cpt").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("cpt").feature("relg1").setIndex("x0", 1, 2);
    model.component("comp1").physics("cpt").feature("relg1").set("VelocitySpecification", "Maxwellian");
    model.component("comp1").physics("cpt").feature("relg1").setIndex("M", 30, 0);
    model.component("comp1").physics("cpt").feature("relg1").set("T0", "T0");
    model.component("comp1").physics("cpt").create("ef1", "ElectricForce", 3);
    model.component("comp1").physics("cpt").feature("ef1").selection().all();
    model.component("comp1").physics("cpt").feature("ef1").set("E", new String[]{"0", "0", "Ez"});
    model.component("comp1").physics("cpt").create("col1", "Collisions", 3);
    model.component("comp1").physics("cpt").feature("col1").selection().all();
    model.component("comp1").physics("cpt").feature("col1").set("Nd", "ND");
    model.component("comp1").physics("cpt").feature("col1").set("T", "T0");
    model.component("comp1").physics("cpt").feature("col1")
         .set("CollisionDetection", "NullCollisionMethodColdGasApproximation");
    model.component("comp1").physics("cpt").feature("col1").set("CountAllCollisions", true);
    model.component("comp1").physics("cpt").feature("col1").create("ela1", "Elastic", 3);
    model.component("comp1").physics("cpt").feature("col1").feature("ela1").set("xsec", "Qi(cpt.Ep)");
    model.component("comp1").physics("cpt").feature("col1").feature("ela1").set("CountCollisions", true);
    model.component("comp1").physics("cpt").feature("col1").create("cex1", "ResonantChargeExchange", 3);
    model.component("comp1").physics("cpt").feature("col1").feature("cex1").set("xsec", "(Qm(cpt.Ep)-Qi(cpt.Ep))/2");
    model.component("comp1").physics("cpt").feature("col1").feature("cex1").set("CountCollisions", true);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "mAr", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "kg", 0);
    model.study("std1").feature("param").setIndex("pname", "mAr", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "kg", 0);
    model.study("std1").feature("param").setIndex("pname", "EoverN", 0);
    model.study("std1").feature("param").setIndex("plistarr", "5e2, 1e3, 2e3, 3e3, 5e3, 1e4, 2e4, 3e4, 5e4, 1e5", 0);
    model.study("std1").feature("time").set("tlist", "0,5.0e-8*sqrt(maxCol/EoverN)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol1").feature("t1").set("timestepgenalpha", "2.5e-9*sqrt(maxCol/EoverN)");
    model.sol("sol1").feature("t1").set("rhoinf", 1);
    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1")
         .setIndex("stopcondarr", "comp1.cpt.sum(comp1.cpt.col1.cex1.Nc)>maxCol", 0);
    model.sol("sol1").feature("t1").feature("st1").set("storestopcondsol", "stepbefore_stepafter");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol2");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_cpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "cpt");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "part1");
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").setIndex("looplevel", 10, 1);
    model.result("pg1").label("\u7c92\u5b50\u8f68\u8ff9 (cpt)");
    model.result("pg1").create("traj1", "ParticleTrajectories");
    model.result("pg1").feature("traj1").set("pointtype", "point");
    model.result("pg1").feature("traj1").set("linetype", "none");
    model.result("pg1").feature("traj1").create("col1", "Color");
    model.result("pg1").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u6f02\u79fb\u901f\u5ea6");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "cpt.ave(cpt.vz)", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "Vdrift(EoverN)", 1);
    model.result("pg2").feature("glob1").setIndex("descr", "", 1);
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "EoverN");
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "COMSOL", 0);
    model.result("pg2").feature("glob1").setIndex("legends", "\u53c2\u8003\u8d44\u6599", 1);
    model.result("pg2").run();
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u6f02\u79fb\u901f\u5ea6 (m/s)");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6f02\u79fb\u901f\u5ea6\u6bd4\u8f83");
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u79bb\u5b50\u6e29\u5ea6");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").setIndex("expr", "cpt.ave(2*cpt.Ep)", 0);
    model.result("pg3").feature("glob1").setIndex("unit", "eV", 0);
    model.result("pg3").feature("glob1").setIndex("expr", "ionTemp(EoverN)", 1);
    model.result("pg3").feature("glob1").setIndex("unit", "eV", 1);
    model.result("pg3").run();
    model.result("pg3").set("ylabel", "\u79bb\u5b50\u6e29\u5ea6 (eV)");
    model.result("pg3").set("title", "\u79bb\u5b50\u6e29\u5ea6\u6bd4\u8f83");
    model.result("pg3").run();

    model.title("\u79bb\u5b50\u6f02\u79fb\u901f\u5ea6\u57fa\u51c6\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u901a\u8fc7\u8499\u7279\u5361\u7f57\u4eff\u771f\u8ba1\u7b97\u4e86 Ar+ \u79bb\u5b50\u7684\u6f02\u79fb\u901f\u5ea6\uff0c\u5176\u4e2d\u901a\u8fc7\u663e\u5f0f\u65b9\u6cd5\u5bf9\u6c29\u79bb\u5b50\u4e0e\u4e2d\u6027\u7f13\u51b2\u6c14\u4f53\u4e4b\u95f4\u7684\u5f39\u6027\u78b0\u649e\u548c\u7535\u8377\u4ea4\u6362\u53cd\u5e94\u8fdb\u884c\u5efa\u6a21\u3002\u672c\u4f8b\u4f7f\u7528\u4e86\u6765\u81ea\u5b9e\u9a8c\u7684\u968f\u80fd\u91cf\u53d8\u5316\u7684\u78b0\u649e\u622a\u9762\u6570\u636e\u3002\u79bb\u5b50\u5e73\u5747\u901f\u5ea6\u503c\u4e0e\u5b9e\u9a8c\u6570\u636e\u5728\u5f88\u5927\u7684\u7ea6\u5316\u7535\u573a\u8303\u56f4\u5185\u543b\u5408\u5f88\u597d\u3002");

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
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();

    model.label("ion_drift_velocity_benchmark.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
