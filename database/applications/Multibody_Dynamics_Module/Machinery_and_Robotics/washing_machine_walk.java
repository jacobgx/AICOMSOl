/*
 * washing_machine_walk.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:23 by COMSOL 6.3.0.290. */
public class washing_machine_walk {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Machinery_and_Robotics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("m_w", "15[kg]", "\u6d17\u8863\u673a\u8d28\u91cf");
    model.param().set("m_cl", "3[kg]", "\u4e0d\u5e73\u6574\u8863\u7269\u7684\u8d28\u91cf");
    model.param().set("m_b", "2[kg]", "\u5e73\u8861\u914d\u91cd\u5757\u7684\u8d28\u91cf");
    model.param().set("r_cl", "0.1[m]", "\u4e0d\u5e73\u6574\u8863\u7269\u7684\u5f84\u5411\u4f4d\u7f6e");
    model.param().set("r_b", "0.15[m]", "\u5e73\u8861\u914d\u91cd\u5757\u7684\u5f84\u5411\u4f4d\u7f6e");
    model.param().set("xcx_w", "0.3[m]", "\u6d17\u8863\u673a\u7684 CG\uff0cx \u5750\u6807");
    model.param().set("xcy_w", "0.45[m]", "\u6d17\u8863\u673a\u7684 CG\uff0cy \u5750\u6807");
    model.param().set("xcz_w", "0.4[m]", "\u6d17\u8863\u673a\u7684 CG\uff0cz \u5750\u6807");
    model.param().set("xcx_d", "0.3[m]", "\u6eda\u7b52\u7684 CG\uff0cx \u5750\u6807");
    model.param().set("xcy_d", "0.2[m]", "\u6eda\u7b52\u7684 CG\uff0cy \u5750\u6807");
    model.param().set("xcz_d", "0.45[m]", "\u6eda\u7b52\u7684 CG\uff0cz \u5750\u6807");
    model.param().set("mu", "0.2", "\u6469\u64e6\u7cfb\u6570");
    model.param().set("phi0", "(pi/4)[rad]", "\u5e73\u8861\u914d\u91cd\u5757\u7684\u521d\u59cb\u89d2\u4f4d\u7f6e");
    model.param().set("omegaC_max", "(pi/2)[rad/s]", "\u6700\u5927\u4fee\u6b63\u89d2\u901f\u5ea6");
    model.param().set("k_s", "1e7", "\u652f\u6491\u521a\u5ea6");
    model.param().set("c_s", "1e6", "\u652f\u6491\u7684\u963b\u5c3c\u7cfb\u6570");
    model.param().set("i_c", "0", "\u6d3b\u52a8\u63a7\u5236\u72b6\u6001");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "washing_machine_walk.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("mbd").create("rd1", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd1").label("\u521a\u6027\u6750\u6599\uff1a\u6d17\u8863\u673a");
    model.component("comp1").physics("mbd").feature("rd1").selection().set(1, 2, 3, 7, 8);
    model.component("comp1").physics("mbd").feature("rd1").set("rho_mat", "userdef");
    model.component("comp1").physics("mbd").feature("rd1").create("mmi1", "MassInertia", -1);
    model.component("comp1").physics("mbd").feature("rd1").feature("mmi1").set("CenterOfMassType", "userDefined");
    model.component("comp1").physics("mbd").feature("rd1").feature("mmi1")
         .set("xm", new String[]{"xcx_w", "xcy_w", "xcz_w"});
    model.component("comp1").physics("mbd").feature("rd1").feature("mmi1").set("mt", "m_w");
    model.component("comp1").physics("mbd").feature("rd1").create("af1", "AppliedForce", -1);
    model.component("comp1").physics("mbd").feature("rd1").feature("af1")
         .set("PointOfApplicationType", "userDefined[RD]");
    model.component("comp1").physics("mbd").feature("rd1").feature("af1")
         .set("Xpa", new String[]{"xcx_w", "xcy_w", "xcz_w"});
    model.component("comp1").physics("mbd").feature("rd1").feature("af1")
         .set("Ft", new String[]{"0", "0", "-m_w*g_const"});
    model.component("comp1").physics("mbd").feature().duplicate("rd2", "rd1");
    model.component("comp1").physics("mbd").feature("rd2").label("\u521a\u6027\u6750\u6599\uff1a\u6eda\u7b52");
    model.component("comp1").physics("mbd").feature("rd2").selection().set(4);
    model.component("comp1").physics("mbd").feature("rd2").feature("mmi1")
         .set("xm", new String[]{"xcx_d", "xcy_d", "xcz_d+r_cl"});
    model.component("comp1").physics("mbd").feature("rd2").feature("mmi1").set("mt", "m_cl");
    model.component("comp1").physics("mbd").feature("rd2").feature("af1")
         .set("Xpa", new String[]{"xcx_d", "xcy_d", "xcz_d+r_cl"});
    model.component("comp1").physics("mbd").feature("rd2").feature("af1")
         .set("Ft", new String[]{"0", "0", "-m_cl*g_const"});
    model.component("comp1").physics("mbd").feature().duplicate("rd3", "rd2");
    model.component("comp1").physics("mbd").feature("rd3").label("\u521a\u6027\u6750\u6599\uff1a\u5e73\u8861\u5757");
    model.component("comp1").physics("mbd").feature("rd3").selection().set(6);
    model.component("comp1").physics("mbd").feature("rd3").feature("mmi1")
         .set("xm", new String[]{"xcx_d+r_b*cos(phi0)", "xcy_d", "xcz_d-r_b*sin(phi0)"});
    model.component("comp1").physics("mbd").feature("rd3").feature("mmi1").set("mt", "m_b");
    model.component("comp1").physics("mbd").feature("rd3").feature("af1")
         .set("Xpa", new String[]{"xcx_d+r_b*cos(phi0)", "xcy_d", "xcz_d-r_b*sin(phi0)"});
    model.component("comp1").physics("mbd").feature("rd3").feature("af1")
         .set("Ft", new String[]{"0", "0", "-m_b*g_const"});
    model.component("comp1").physics("mbd").create("rd4", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd4").label("\u521a\u6027\u6750\u6599\uff1a\u69fd");
    model.component("comp1").physics("mbd").feature("rd4").selection().set(5);
    model.component("comp1").physics("mbd").feature("rd4").set("rho_mat", "userdef");
    model.component("comp1").physics("mbd").create("plj1", "PlanarJoint", -1);
    model.component("comp1").physics("mbd").feature("plj1").label("\u5e73\u9762\u5173\u8282\uff1a\u652f\u67b6 1");
    model.component("comp1").physics("mbd").feature("plj1").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("plj1").set("Destination", "rd1");
    model.component("comp1").physics("mbd").feature("plj1").set("e", new int[]{0, 0, 1});
    model.component("comp1").physics("mbd").feature("plj1").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("plj1")
         .set("JointForcesAndMoments", "ComputedUsingWeakConstraints");
    model.component("comp1").physics("mbd").feature("plj1").feature("cjb1").selection().set(18);
    model.component("comp1").physics("mbd").feature("plj1").feature("je1")
         .set("k_u", new String[]{"k_s", "0", "0", "0", "k_s", "0", "0", "0", "k_s"});
    model.component("comp1").physics("mbd").feature("plj1").feature("je1")
         .set("k_th", new String[]{"k_s", "0", "0", "0", "k_s", "0", "0", "0", "k_s"});
    model.component("comp1").physics("mbd").feature("plj1").feature("je1")
         .set("c_u", new String[]{"c_s", "0", "0", "0", "c_s", "0", "0", "0", "c_s"});
    model.component("comp1").physics("mbd").feature("plj1").feature("je1")
         .set("c_th", new String[]{"c_s", "0", "0", "0", "c_s", "0", "0", "0", "c_s"});
    model.component("comp1").physics("mbd").feature("plj1").create("fric1", "Friction", -1);
    model.component("comp1").physics("mbd").feature("plj1").feature("fric1").set("mu", "mu");
    model.component("comp1").physics("mbd").feature().duplicate("plj2", "plj1");
    model.component("comp1").physics("mbd").feature("plj2").label("\u5e73\u9762\u5173\u8282\uff1a\u652f\u67b6 2");
    model.component("comp1").physics("mbd").feature("plj2").feature("cjb1").selection().set(52);
    model.component("comp1").physics("mbd").feature().duplicate("plj3", "plj2");
    model.component("comp1").physics("mbd").feature("plj3").label("\u5e73\u9762\u5173\u8282\uff1a\u652f\u67b6 3");
    model.component("comp1").physics("mbd").feature("plj3").feature("cjb1").selection().set(58);
    model.component("comp1").physics("mbd").feature().duplicate("plj4", "plj3");
    model.component("comp1").physics("mbd").feature("plj4").label("\u5e73\u9762\u5173\u8282\uff1a\u652f\u67b6 4");
    model.component("comp1").physics("mbd").feature("plj4").feature("cjb1").selection().set(24);
    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1")
         .label("\u94f0\u94fe\u5173\u8282\uff1a\u6d17\u8863\u673a-\u6eda\u7b52");
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "rd2");
    model.component("comp1").physics("mbd").feature("hgj1").set("e", new int[]{0, 1, 0});
    model.component("comp1").physics("mbd").feature("hgj1").feature("cjb1").selection().set(34);
    model.component("comp1").physics("mbd").feature().duplicate("hgj2", "hgj1");
    model.component("comp1").physics("mbd").feature("hgj2")
         .label("\u94f0\u94fe\u5173\u8282\uff1a\u6eda\u7b52-\u69fd");
    model.component("comp1").physics("mbd").feature("hgj2").set("Source", "rd2");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "rd4");
    model.component("comp1").physics("mbd").create("prj1", "PrismaticJoint", -1);
    model.component("comp1").physics("mbd").feature("prj1")
         .label("\u68f1\u67f1\u5173\u8282\uff1a\u69fd-\u5e73\u8861\u5757");
    model.component("comp1").physics("mbd").feature("prj1").set("Source", "rd4");
    model.component("comp1").physics("mbd").feature("prj1").set("Destination", "rd3");
    model.component("comp1").physics("mbd").feature("prj1").set("AxisOfJointType", "SelectEdge");
    model.component("comp1").physics("mbd").feature("prj1").feature("cjb1").selection().set(34);
    model.component("comp1").physics("mbd").feature("prj1").feature("ja1").selection().set(86);
    model.component("comp1").physics("mbd").feature("prj1").create("pm1", "PrescribedMotion", -1);

    model.nodeGroup().create("grp1", "Physics", "mbd");
    model.nodeGroup("grp1").placeAfter("init1");
    model.nodeGroup("grp1").add("rd1");
    model.nodeGroup("grp1").add("rd2");
    model.nodeGroup("grp1").add("rd3");
    model.nodeGroup("grp1").add("rd4");
    model.nodeGroup("grp1").label("\u521a\u6027\u6750\u6599");
    model.nodeGroup().create("grp2", "Physics", "mbd");
    model.nodeGroup("grp2").placeAfter("init1");
    model.nodeGroup("grp2").add("plj1");
    model.nodeGroup("grp2").add("plj2");
    model.nodeGroup("grp2").add("plj3");
    model.nodeGroup("grp2").add("plj4");
    model.nodeGroup("grp2").label("\u5e73\u9762\u5173\u8282");
    model.nodeGroup().create("grp3", "Physics", "mbd");
    model.nodeGroup("grp3").placeAfter("init1");
    model.nodeGroup("grp3").add("hgj1");
    model.nodeGroup("grp3").add("hgj2");
    model.nodeGroup("grp3").label("\u94f0\u94fe\u5173\u8282");

    model.func().create("step1", "Step");
    model.func("step1").set("funcname", "step_act");
    model.func("step1").set("location", "7[s]");
    model.func("step1").set("smooth", 0.5);
    model.func().duplicate("step2", "step1");
    model.func("step2").set("funcname", "step_deact");
    model.func("step2").set("location", "5[deg]");
    model.func("step2").set("smooth", "10[deg]");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("omega", "(pi/2)[rad/s^2]*t", "\u6eda\u7b52\u89d2\u901f\u5ea6");
    model.component("comp1").variable("var1")
         .set("Fxl", "m_b*r_b*omega^2*cos(phi0+mbd.hgj2.th)", "\u4e0d\u5e73\u8861\u603b\u91cf\uff0cxl \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("Fzl", "m_cl*r_cl*omega^2-m_b*r_b*omega^2*sin(phi0+mbd.hgj2.th)", "\u4e0d\u5e73\u8861\u603b\u91cf\uff0czl \u5206\u91cf");
    model.component("comp1").variable("var1").set("F_tot", "sqrt(Fxl^2+Fzl^2)", "\u4e0d\u5e73\u8861\u603b\u91cf");
    model.component("comp1").variable("var1")
         .set("th", "atan2(Fzl,Fxl)+phi0+mbd.hgj2.th", "\u4e0d\u5e73\u8861\u603b\u91cf\u4e0e\u5e73\u8861\u914d\u91cd\u5757\u7684\u5939\u89d2");
    model.component("comp1").variable("var1")
         .set("d_th", "atan2(F_tot*sin(th),(m_b*r_b*omega^2-F_tot*cos(th)))", "\u6821\u6b63\u89d2\u5ea6");
    model.component("comp1").variable("var1")
         .set("omegaC", "step_act(t)*nojac(step_deact(abs(d_th))*sign(d_th))*omegaC_max", "\u4fee\u6b63\u89d2\u901f\u5ea6");
    model.component("comp1").variable("var1")
         .set("Fx", "Fxl*cos(mbd.hgj1.th)+Fzl*sin(mbd.hgj1.th)", "\u4e0d\u5e73\u8861\u603b\u91cf\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("Fz", "-Fxl*sin(mbd.hgj1.th)+Fzl*cos(mbd.hgj1.th)", "\u4e0d\u5e73\u8861\u603b\u91cf\uff0cz \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("Msl1", "abs(mu*mbd.plj1.Fz)-sqrt(mbd.plj1.Fx^2+mbd.plj1.Fy^2)", "\u6ed1\u8ddd\uff0c\u652f\u6491 1");
    model.component("comp1").variable("var1")
         .set("Msl2", "abs(mu*mbd.plj2.Fz)-sqrt(mbd.plj2.Fx^2+mbd.plj2.Fy^2)", "\u6ed1\u8ddd\uff0c\u652f\u6491 2");
    model.component("comp1").variable("var1")
         .set("Msl3", "abs(mu*mbd.plj3.Fz)-sqrt(mbd.plj3.Fx^2+mbd.plj3.Fy^2)", "\u6ed1\u8ddd\uff0c\u652f\u6491 3");
    model.component("comp1").variable("var1")
         .set("Msl4", "abs(mu*mbd.plj4.Fz)-sqrt(mbd.plj4.Fx^2+mbd.plj4.Fy^2)", "\u6ed1\u8ddd\uff0c\u652f\u6491 4");
    model.component("comp1").variable("var1").set("Msl_tot", "Msl1+Msl2+Msl3+Msl4", "\u603b\u6ed1\u8ddd");
    model.component("comp1").variable("var1")
         .set("rpm", "omega*60[s]/(2*pi[rad])", "\u6eda\u7b52\u89d2\u901f\u5ea6 (rpm)");
    model.component("comp1").variable("var1")
         .set("rpmC", "omegaC*60[s]/(2*pi[rad])", "\u4fee\u6b63\u89d2\u901f\u5ea6 (rpm)");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection().set(18);
    model.component("comp1").variable("var2").set("Fsx", "mbd.plj1.Fx");
    model.component("comp1").variable("var2").descr("Fsx", "\u6469\u64e6\u529b\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var2").set("Fsy", "mbd.plj1.Fy");
    model.component("comp1").variable("var2").descr("Fsy", "\u6469\u64e6\u529b\uff0cy \u5206\u91cf");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").selection().geom("geom1", 2);
    model.component("comp1").variable("var3").selection().set(52);
    model.component("comp1").variable("var3").set("Fsx", "mbd.plj2.Fx", "\u6469\u64e6\u529b\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var3").set("Fsy", "mbd.plj2.Fy", "\u6469\u64e6\u529b\uff0cy \u5206\u91cf");
    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4").selection().geom("geom1", 2);
    model.component("comp1").variable("var4").selection().set(58);
    model.component("comp1").variable("var4").set("Fsx", "mbd.plj3.Fx", "\u6469\u64e6\u529b\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var4").set("Fsy", "mbd.plj3.Fy", "\u6469\u64e6\u529b\uff0cy \u5206\u91cf");
    model.component("comp1").variable().create("var5");
    model.component("comp1").variable("var5").selection().geom("geom1", 2);
    model.component("comp1").variable("var5").selection().set(24);
    model.component("comp1").variable("var5").set("Fsx", "mbd.plj4.Fx", "\u6469\u64e6\u529b\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var5").set("Fsy", "mbd.plj4.Fy", "\u6469\u64e6\u529b\uff0cy \u5206\u91cf");

    model.component("comp1").physics("mbd").feature("hgj1").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1")
         .set("PrescribedMotionThroughRotational", "AngularVelocity");
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1").set("omegap", "omega");
    model.component("comp1").physics("mbd").feature("hgj2").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("hgj2").feature("pm1")
         .set("PrescribedMotionThroughRotational", "AngularVelocity");
    model.component("comp1").physics("mbd").feature("hgj2").feature("pm1").set("omegap", "i_c*omegaC");

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size5").selection().set(8, 9, 11, 12, 28, 29, 30, 31, 32, 33);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "m_w", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "kg", 0);
    model.study("std1").feature("param").setIndex("pname", "m_w", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "kg", 0);
    model.study("std1").feature("param").setIndex("pname", "i_c", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0 1", 0);
    model.study("std1").feature("time").set("tlist", "range(0,0.01,9)");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 901, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u901f\u5ea6 (mbd)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 901, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").label("\u57df\u7f16\u53f7");
    model.result("pg2").feature("vol1").set("descractive", true);
    model.result("pg2").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg2").feature("vol1").set("descr", "\u57df\u7f16\u53f7");
    model.result("pg2").feature("vol1").set("rangecoloractive", "on");
    model.result("pg2").feature("vol1").set("rangecolormin", -0.5);
    model.result("pg2").feature("vol1").set("rangecolormax", 9.5);
    model.result("pg2").feature("vol1").set("colortable", "Cyclic");
    model.result("pg2").feature("vol1").set("colorlegend", false);
    model.result("pg2").feature("vol1").set("colortabletype", "discrete");
    model.result("pg2").feature("vol1").set("smooth", "none");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature("vol1").feature().create("def1", "Deform");
    model.result("pg2").feature("vol1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg2").feature("arwl1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result("pg1").run();
    model.result().dataset().duplicate("dset3", "dset2");
    model.result().dataset("dset3").selection().geom("geom1", 3);
    model.result().dataset("dset3").selection().geom("geom1", 3);
    model.result().dataset("dset3").selection().set(1);
    model.result().dataset().duplicate("dset4", "dset2");
    model.result().dataset("dset4").selection().geom("geom1", 3);
    model.result().dataset("dset4").selection().geom("geom1", 3);
    model.result().dataset("dset4").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result().dataset("dset4").selection().geom("geom1", 3);
    model.result().dataset("dset4").selection().set(2, 3, 4, 5, 6, 7, 8);
    model.result().dataset("dset4").selection().inherit(false);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u6d17\u8863\u673a\u4f4d\u79fb");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 1, 1);
    model.result("pg3").setIndex("looplevel", 891, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("colortable", "SpectrumLight");
    model.result("pg3").feature("vol1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("vol1").feature("def1").set("scale", 100);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u6469\u64e6\u529b");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").run();
    model.result("pg4").feature("vol1").feature("def1").set("scale", 1);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"Fsx", "Fsy", "0"});
    model.result("pg4").feature("arws1").set("scaleactive", true);
    model.result("pg4").feature("arws1").set("scale", 0.04);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u4e0d\u5e73\u8861\u603b\u91cf\uff08\u5c40\u90e8\uff09");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u4e0d\u5e73\u8861\u603b\u91cf (N)");
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("expr", new String[]{"Fxl"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u4e0d\u5e73\u8861\u603b\u91cf\uff0cxl \u5206\u91cf"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg5").feature("glob1").set("expr", new String[]{"Fxl", "Fzl"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u4e0d\u5e73\u8861\u603b\u91cf\uff0cxl \u5206\u91cf", "\u4e0d\u5e73\u8861\u603b\u91cf\uff0czl \u5206\u91cf"});
    model.result("pg5").feature("glob1").set("linestyle", "cycle");
    model.result("pg5").feature("glob1").set("linewidth", 2);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u4e0d\u5e73\u8861\u603b\u91cf");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").set("expr", new String[]{"Fx"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"\u4e0d\u5e73\u8861\u603b\u91cf\uff0cx \u5206\u91cf"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg6").feature("glob1").set("expr", new String[]{"Fx", "Fz"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"\u4e0d\u5e73\u8861\u603b\u91cf\uff0cx \u5206\u91cf", "\u4e0d\u5e73\u8861\u603b\u91cf\uff0cz \u5206\u91cf"});
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u6ed1\u79fb\u88d5\u5ea6");
    model.result("pg7").setIndex("looplevelinput", "first", 1);
    model.result("pg7").set("ylabel", "\u6ed1\u79fb\u88d5\u5ea6 (N)");
    model.result("pg7").set("legendpos", "lowerleft");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("expr", new String[]{"Msl1"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"\u6ed1\u8ddd\uff0c\u652f\u6491 1"});
    model.result("pg7").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg7").feature("glob1").set("expr", new String[]{"Msl1", "Msl3"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"\u6ed1\u8ddd\uff0c\u652f\u6491 1", "\u6ed1\u8ddd\uff0c\u652f\u6491 3"});
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u603b\u6ed1\u79fb\u88d5\u5ea6");
    model.result("pg8").setIndex("looplevelinput", "all", 1);
    model.result("pg8").set("ylabelactive", false);
    model.result("pg8").run();
    model.result("pg8").feature("glob1").set("expr", new String[]{"Msl_tot"});
    model.result("pg8").feature("glob1").set("descr", new String[]{"\u603b\u6ed1\u8ddd"});
    model.result("pg8").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg8").feature("glob1").set("autodescr", false);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u6d17\u8863\u673a\u8f6c\u52a8");
    model.result("pg9").set("legendpos", "upperleft");
    model.result("pg9").run();
    model.result("pg9").feature("glob1").set("expr", new String[]{"mbd.rd1.thz"});
    model.result("pg9").feature("glob1").set("descr", new String[]{"\u521a\u4f53\u8f6c\u52a8\uff0cz \u5206\u91cf"});
    model.result("pg9").feature("glob1").set("unit", new String[]{"rad"});
    model.result("pg9").feature("glob1").setIndex("unit", "deg", 0);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u89d2\u901f\u5ea6");
    model.result("pg10").setIndex("looplevelinput", "last", 1);
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "\u89d2\u901f\u5ea6 (rpm)");
    model.result("pg10").run();
    model.result("pg10").feature("glob1").setIndex("expr", "rpm", 0);
    model.result("pg10").feature("glob1").setIndex("unit", 1, 0);
    model.result("pg10").feature("glob1").setIndex("expr", "rpmC", 1);
    model.result("pg10").feature("glob1").set("autodescr", true);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result().duplicate("pg11", "pg10");
    model.result("pg11").run();
    model.result("pg11").label("\u6821\u6b63\u89d2\u5ea6");
    model.result("pg11").set("ylabelactive", false);
    model.result("pg11").run();
    model.result("pg11").feature("glob1").set("expr", new String[]{"d_th"});
    model.result("pg11").feature("glob1").set("descr", new String[]{"\u6821\u6b63\u89d2\u5ea6"});
    model.result("pg11").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg11").feature("glob1").setIndex("unit", "deg", 0);
    model.result("pg11").feature("glob1").set("legend", false);
    model.result("pg11").run();
    model.result("pg3").run();

    model.title("\u6d17\u8863\u673a\u7684\u975e\u7a33\u5b9a\u884c\u8d70");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u6c34\u5e73\u8f74\u4fbf\u643a\u5f0f\u6d17\u8863\u673a\u7684\u7b80\u5316\u6a21\u578b\uff0c\u5e76\u9884\u6d4b\u4e86\u5728\u65cb\u8f6c\u5468\u671f\u4e2d\u6d17\u8863\u673a\u51fa\u73b0\u4e0d\u7a33\u5b9a\u884c\u8d70\u7684\u65f6\u95f4\u3002\u6d17\u8863\u673a\u7684\u8f6c\u52a8\u6ed1\u79fb\u662f\u4e00\u4e2a\u5173\u952e\u7684\u8bbe\u8ba1\u95ee\u9898\uff0c\u672c\u4f8b\u5bf9\u6b64\u8fdb\u884c\u4e86\u5206\u6790\u5e76\u63cf\u8ff0\u4e86\u4e00\u79cd\u57fa\u4e8e\u63a7\u5236\u7684\u4e3b\u52a8\u5e73\u8861\u65b9\u6cd5\uff0c\u4ee5\u51cf\u5c11\u7cfb\u7edf\u7684\u4e0d\u7a33\u5b9a\u884c\u8d70\u548c\u632f\u52a8\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("washing_machine_walk.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
