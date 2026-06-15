/*
 * falling_sand.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:34 by COMSOL 6.3.0.290. */
public class falling_sand {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Fluid_Dynamics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rho_water", "1000[kg/m^3]", "\u5bc6\u5ea6\uff0c\u6c34");
    model.param().set("mu_water", "1.51e-3[Pa*s]", "\u52a8\u529b\u9ecf\u5ea6\uff0c\u6c34");
    model.param().set("r_grain", "1[mm]", "\u534a\u5f84\uff0c\u7802\u7c92");
    model.param().set("V_grain", "4/3*pi*r_grain^3", "\u7802\u7c92\u4f53\u79ef");
    model.param().set("rho_grain", "2900[kg/m^3]", "\u7802\u7c92\u5bc6\u5ea6");
    model.param().set("m_grain", "V_grain*rho_grain", "\u7802\u7c92\u8d28\u91cf");
    model.param().set("F_g", "-m_grain*g_const", "\u7802\u7c92\u53d7\u5230\u7684\u91cd\u529b");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"6e-3", "14e-3"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-6e-3"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "1e-3");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").run("dif1");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(6, 7);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("F_z", "intop1(-w_lm[Pa])");
    model.component("comp1").variable("var1").descr("F_z", "\u66f3\u529b");

    model.component("comp1").physics("spf").feature("fp1").set("rho_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("rho", "rho_water");
    model.component("comp1").physics("spf").feature("fp1").set("mu_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("mu", "mu_water");
    model.component("comp1").physics("spf").create("vf1", "VolumeForce", 2);
    model.component("comp1").physics("spf").feature("vf1").selection().all();
    model.component("comp1").physics("spf").feature("vf1")
         .set("F", new String[]{"0", "0", "-rho_water*(Xdott+g_const)"});
    model.component("comp1").physics("spf").feature("wallbc1").set("constraintOptions", "weakConstraints");
    model.component("comp1").physics("spf").create("constr1", "PointwiseConstraint", 0);
    model.component("comp1").physics("spf").feature("constr1").selection().set(2, 3);
    model.component("comp1").physics("spf").feature("constr1").set("constraintExpression", "u_lm");
    model.component("comp1").physics("spf").feature("constr1").set("order", 1);
    model.component("comp1").physics("spf").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("spf").feature("ge1").setIndex("name", "X", 0, 0);
    model.component("comp1").physics("spf").feature("ge1").setIndex("equation", "Xt-Xdot", 0, 0);
    model.component("comp1").physics("spf").feature("ge1").set("DependentVariableQuantity", "displacement");
    model.component("comp1").physics("spf").feature("ge1").set("SourceTermQuantity", "velocity");
    model.component("comp1").physics("spf").create("ge2", "GlobalEquations", -1);
    model.component("comp1").physics("spf").feature("ge2").setIndex("name", "Xdot", 0, 0);
    model.component("comp1").physics("spf").feature("ge2").setIndex("equation", "Xdott-(F_z+F_g)/m_grain", 0, 0);
    model.component("comp1").physics("spf").feature("ge2").set("DependentVariableQuantity", "velocity");
    model.component("comp1").physics("spf").feature("ge2").set("SourceTermQuantity", "acceleration");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "-Xdot");
    model.component("comp1").physics("spf").create("open1", "OpenBoundary", 1);
    model.component("comp1").physics("spf").feature("open1").selection().set(4);
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf").feature("wallbc2").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(5);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "1.5e-4");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(6, 7);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "0.75e-4");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.05);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.025,1)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", 0.001);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("dataisaxisym", "off");
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
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("posmethod", "magnitude");
    model.result("pg1").feature("str1").set("mdist", new double[]{0.01, 0.04});
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 4, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 5, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 7, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 9, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 13, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 21, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 31, 0);
    model.result("pg1").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg1");
    model.result().export("anim1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u7802\u7c92\u901f\u5ea6 (m/s)");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(1);
    model.result("pg4").feature("ptgr1").set("expr", "-Xdot");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "F<sub>z</sub>, F<sub>g</sub>, and F<sub>g</sub>+F<sub>z</sub> (N)");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(1);
    model.result("pg5").feature("ptgr1").set("expr", "F_z");
    model.result("pg5").feature("ptgr1").set("descr", "\u66f3\u529b");
    model.result("pg5").feature("ptgr1").set("linecolor", "blue");
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "F_z", 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("ptgr2", "PointGraph");
    model.result("pg5").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr2").set("linewidth", "preference");
    model.result("pg5").feature("ptgr2").selection().set(1);
    model.result("pg5").feature("ptgr2").set("expr", "F_g");
    model.result("pg5").feature("ptgr2").set("linecolor", "red");
    model.result("pg5").feature("ptgr2").set("legend", true);
    model.result("pg5").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr2").setIndex("legends", "F_g", 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("ptgr3", "PointGraph");
    model.result("pg5").feature("ptgr3").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr3").set("linewidth", "preference");
    model.result("pg5").feature("ptgr3").selection().set(1);
    model.result("pg5").feature("ptgr3").set("expr", "F_z+F_g");
    model.result("pg5").feature("ptgr3").set("linecolor", "black");
    model.result("pg5").feature("ptgr3").set("legend", true);
    model.result("pg5").feature("ptgr3").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr3").setIndex("legends", "F_z+F_g", 0);
    model.result("pg5").run();
    model.result("pg3").run();

    model.title("\u7802\u7c92\u7684\u81ea\u7531\u6c89\u964d\u901f\u5ea6");

    model
         .description("\u4e00\u4e2a\u7403\u5f62\u7802\u7c92\u843d\u5728\u6c34\u7ba1\u4e2d\uff0c\u7531\u9759\u6b62\u5f00\u59cb\uff0c\u52a0\u901f\u4e0b\u843d\uff0c\u5e76\u8fbe\u5230\u81ea\u7531\u6c89\u964d\u901f\u5ea6\u3002\u672c\u4f8b\u4f7f\u7528\u79fb\u52a8\u5750\u6807\u7cfb\u6a21\u62df\u6d41\u4f53\u6d41\u52a8\uff0c\u5e76\u8026\u5408\u4e86\u4e00\u4e2a\u5e38\u5fae\u5206\u65b9\u7a0b\u6765\u63cf\u8ff0\u7802\u7c92\u7684\u53d7\u529b\u5e73\u8861\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("falling_sand.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
