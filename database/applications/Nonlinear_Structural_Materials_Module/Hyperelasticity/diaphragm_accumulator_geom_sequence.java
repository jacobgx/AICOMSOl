/*
 * diaphragm_accumulator_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:31 by COMSOL 6.3.0.290. */
public class diaphragm_accumulator_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Hyperelasticity");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("shell.th", "3[mm]", "\u539a\u5ea6\uff0c\u58f3");
    model.param().set("shell.Do", "75[mm]", "\u5916\u76f4\u5f84\uff0c\u58f3");
    model.param().set("shell.Ro", "shell.Do/2", "\u5916\u534a\u5f84\uff0c\u58f3");
    model.param().set("shell.Ri", "shell.Ro-shell.th", "\u5185\u534a\u5f84\uff0c\u58f3");
    model.param().set("shell.hsf", "3.5*shell.th", "\u76f4\u6cd5\u5170\u9ad8\u5ea6\uff0c\u58f3");
    model.param().set("shell.Rk", "0.2*shell.Do", "\u5185\u5173\u8282\u534a\u5f84\uff0c\u58f3");
    model.param().set("shell.Rc", "1.4*shell.Ri", "\u5185\u51a0\u534a\u5f84\uff0c\u58f3");
    model.param()
         .set("shell.hi", "shell.Rc-sqrt((shell.Rc-shell.Ri)*(shell.Rc+shell.Ri-2*shell.Rk))", "\u5185\u5934\u9ad8\u5ea6\uff0c\u58f3");
    model.param()
         .set("shell.alpha_k", "atan((shell.Ri-shell.Rk)/(shell.Rc-shell.hi))", "\u51a0\u72b6\u8282\u4ea4\u89d2\uff0c\u58f3");
    model.param().set("shell.alpha_bp", "20[deg]", "\u5e95\u677f\u4ea4\u89d2");
    model.param().set("dia.th", "shell.th/2", "\u539a\u5ea6\uff0c\u819c\u7247");
    model.param().set("dia.thb", "dia.th*1.8", "\u6eda\u73e0\u539a\u5ea6\uff0c\u819c\u7247");
    model.param().set("dia.Zb", "dia.th/2", "\u6eda\u73e0\u4f4d\u7f6e\uff0c\u819c\u7247");
    model.param().set("dia.Ro", "shell.Ri-shell.th*2", "\u5916\u534a\u5f84\uff0c\u819c\u7247");
    model.param().set("dia.Ri", "dia.Ro-dia.th", "\u5185\u534a\u5f84\uff0c\u819c\u7247");
    model.param().set("dia.alpha", "12[deg]", "\u89d2\u53c2\u6570\uff0c\u819c\u7247");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "shell.Rc+shell.th");
    model.component("comp1").geom("geom1").feature("c1").set("angle", "shell.alpha_k");
    model.component("comp1").geom("geom1").feature("c1")
         .set("pos", new String[]{"0", "shell.hsf-(shell.Rc-shell.hi)"});
    model.component("comp1").geom("geom1").feature("c1").set("rot", "90[deg]-shell.alpha_k");
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "shell.th", 0);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "shell.Rk+shell.th");
    model.component("comp1").geom("geom1").feature("c2").set("angle", "90[deg]-shell.alpha_k");
    model.component("comp1").geom("geom1").feature("c2").set("pos", new String[]{"shell.Ri-shell.Rk", "0"});
    model.component("comp1").geom("geom1").feature("c2").setIndex("pos", "shell.hsf", 1);
    model.component("comp1").geom("geom1").feature("c2").setIndex("layer", "shell.th", 0);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"shell.th", "shell.hsf"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"shell.Ri", "0"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("c3").set("r", "shell.Ro");
    model.component("comp1").geom("geom1").feature("c3").set("angle", "90[deg]");
    model.component("comp1").geom("geom1").feature("c3").set("rot", "-90[deg]");
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "shell.th", 0);
    model.component("comp1").geom("geom1").run("c3");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"shell.Ro", "shell.th"});
    model.component("comp1").geom("geom1").feature("r2")
         .set("pos", new String[]{"0", "-shell.Ro*cos(shell.alpha_bp)"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "4.5[mm]", 0);
    model.component("comp1").geom("geom1").feature("r2").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r2").set("layerbottom", false);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("c1", "c2", "c3", "r1", "r2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 1, 2, 3, 4, 5, 6, 11, 12);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("del1", 6, 14);
    model.component("comp1").geom("geom1").run("del2");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("del2", 6);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "shell.Ri/5");
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("c1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("c2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("r1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("c3");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("r2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("uni1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("del1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("del2");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("fil1");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").nodeGroup("grp1").remove("sq1", false);
    model.component("comp1").geom("geom1").feature("sq1").set("size", "dia.thb");
    model.component("comp1").geom("geom1").feature("sq1").set("pos", new String[]{"shell.Ri-dia.thb", "0"});
    model.component("comp1").geom("geom1").feature("sq1").setIndex("pos", "dia.Zb", 1);
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"dia.th", "dia.Zb"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"shell.Ri-dia.th", "0"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("c4", "Circle");
    model.component("comp1").geom("geom1").feature("c4").set("r", "dia.Ro");
    model.component("comp1").geom("geom1").feature("c4").set("angle", "90[deg]-dia.alpha");
    model.component("comp1").geom("geom1").feature("c4").set("pos", new String[]{"shell.Ri-dia.Ro", "0"});
    model.component("comp1").geom("geom1").feature("c4").set("rot", "-(90[deg]-dia.alpha)");
    model.component("comp1").geom("geom1").feature("c4").setIndex("layer", "dia.th", 0);
    model.component("comp1").geom("geom1").run("c4");
    model.component("comp1").geom("geom1").create("del3", "Delete");
    model.component("comp1").geom("geom1").feature("del3").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del3").selection("input").set("c4", 1);
    model.component("comp1").geom("geom1").run("del3");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-dia.Ro*cos(dia.alpha)", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-dia.Ro*cos(dia.alpha)+5[mm]", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "5[mm]", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-dia.Ro*cos(dia.alpha)+5[mm]", 2, 1);
    model.component("comp1").geom("geom1").feature("pol1")
         .setIndex("table", "(shell.Ri-dia.Ro)+dia.Ri*sin(dia.alpha)", 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-dia.Ri*cos(dia.alpha)", 3, 1);
    model.component("comp1").geom("geom1").feature("pol1")
         .setIndex("table", "(shell.Ri-dia.Ro)+dia.Ro*sin(dia.alpha)", 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-dia.Ro*cos(dia.alpha)", 4, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("del3", "pol1", "r3", "sq1");
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("fil2").selection("point").set("uni2", 3, 4, 5);
    model.component("comp1").geom("geom1").feature("fil2").set("radius", "5[mm]");
    model.component("comp1").geom("geom1").run("fil2");
    model.component("comp1").geom("geom1").create("fil3", "Fillet");
    model.component("comp1").geom("geom1").feature("fil3").selection("point").set("fil2", 9, 10, 12);
    model.component("comp1").geom("geom1").feature("fil3").set("radius", "dia.th/4");
    model.component("comp1").geom("geom1").nodeGroup().create("grp2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("sq1");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("r3");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("c4");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("del3");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("pol1");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("uni2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("fil2");
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("fil3");
    model.component("comp1").geom("geom1").nodeGroup("grp2").label("\u7ec4 2");
    model.component("comp1").geom("geom1").run("fil3");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").nodeGroup("grp2").remove("pol2", false);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "-dia.Ro*cos(dia.alpha)", 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "-dia.Ro*cos(dia.alpha)+4[mm]", 1, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "4[mm]", 2, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "-dia.Ro*cos(dia.alpha)+4[mm]", 2, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "4[mm]", 3, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "-dia.Ro*cos(dia.alpha)+3[mm]", 3, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "2[mm]", 4, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "-dia.Ro*cos(dia.alpha)+3[mm]", 4, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "2[mm]", 5, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "-dia.Ro*cos(dia.alpha)+2[mm]", 5, 1);
    model.component("comp1").geom("geom1").feature("pol2")
         .setIndex("table", "((shell.Ri-dia.Ro)+dia.Ro*sin(dia.alpha))*0.8", 6, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "-dia.Ro*cos(dia.alpha)+1[mm]", 6, 1);
    model.component("comp1").geom("geom1").feature("pol2")
         .setIndex("table", "((shell.Ri-dia.Ro)+dia.Ro*sin(dia.alpha))*0.8", 7, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "-dia.Ro*cos(dia.alpha)", 7, 1);
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("fil4", "Fillet");
    model.component("comp1").geom("geom1").feature("fil4").selection("point").set("pol2", 3, 4, 5, 6, 8);
    model.component("comp1").geom("geom1").feature("fil4").set("radius", "0.5[mm]");
    model.component("comp1").geom("geom1").run("fil4");
    model.component("comp1").geom("geom1").create("uni3", "Union");
    model.component("comp1").geom("geom1").feature("uni3").selection("input").set("fil3", "fil4");
    model.component("comp1").geom("geom1").nodeGroup().create("grp3");
    model.component("comp1").geom("geom1").nodeGroup("grp3").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("pol2");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("fil4");
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("uni3");
    model.component("comp1").geom("geom1").run("uni3");
    model.component("comp1").geom("geom1").create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").nodeGroup("grp3").remove("pol3", false);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "shell.Ri", 0, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "shell.hsf*0.75", 0, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "shell.Ri-dia.th/2", 1, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "shell.hsf*0.75", 1, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "shell.Ri-dia.th/2", 2, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "dia.Zb+dia.thb+dia.th/2", 2, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "shell.Ri-dia.thb-dia.th/2", 3, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "dia.Zb+dia.thb+dia.th/2", 3, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "shell.Ri-dia.thb-dia.th/2", 4, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "dia.Zb-dia.th/2", 4, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "shell.Ri", 5, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", "dia.Zb-dia.th/2", 5, 1);
    model.component("comp1").geom("geom1").run("pol3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("pol3");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("uni3");
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("fil5", "Fillet");
    model.component("comp1").geom("geom1").feature("fil5").selection("point").set("dif1", 1, 2, 8, 10);
    model.component("comp1").geom("geom1").feature("fil5").set("radius", "dia.th/4");
    model.component("comp1").geom("geom1").nodeGroup().create("grp4");
    model.component("comp1").geom("geom1").nodeGroup("grp4").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("pol3");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("dif1");
    model.component("comp1").geom("geom1").nodeGroup("grp4").add("fil5");
    model.component("comp1").geom("geom1").run("fil5");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").nodeGroup("grp4").remove("r4", false);
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"shell.Ri*0.1", "1"});
    model.component("comp1").geom("geom1").feature("r4").setIndex("size", "shell.th*1.4", 1);
    model.component("comp1").geom("geom1").feature("r4")
         .set("pos", new String[]{"0", "shell.hsf+shell.hi-0.2*shell.th"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("fil1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("r4");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("pol4", "Polygon");
    model.component("comp1").geom("geom1").feature("pol4").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", "5[mm]", 0, 0);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", "shell.hsf+shell.hi", 0, 1);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", "5[mm]", 1, 0);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", "shell.hsf+shell.hi+8[mm]", 1, 1);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", "8[mm]", 2, 0);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", "shell.hsf+shell.hi+8[mm]", 2, 1);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", "8[mm]", 3, 0);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", "shell.hsf+shell.hi+13[mm]", 3, 1);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", "12[mm]", 4, 0);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", "shell.hsf+shell.hi+13[mm]", 4, 1);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", "12[mm]", 5, 0);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", "shell.hsf+shell.hi", 5, 1);
    model.component("comp1").geom("geom1").run("pol4");
    model.component("comp1").geom("geom1").create("dif3", "Difference");
    model.component("comp1").geom("geom1").feature("dif3").selection("input").set("pol4");
    model.component("comp1").geom("geom1").feature("dif3").selection("input2").set("dif2");
    model.component("comp1").geom("geom1").feature("dif3").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif3");
    model.component("comp1").geom("geom1").create("cha1", "Chamfer");
    model.component("comp1").geom("geom1").feature("cha1").selection("point").set("dif2", 2);
    model.component("comp1").geom("geom1").feature("cha1").selection("point").set("dif3", 4, 6);
    model.component("comp1").geom("geom1").feature("cha1").set("dist", "0.5[mm]");
    model.component("comp1").geom("geom1").nodeGroup().create("grp5");
    model.component("comp1").geom("geom1").nodeGroup("grp5").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp5").add("r4");
    model.component("comp1").geom("geom1").nodeGroup("grp5").add("dif2");
    model.component("comp1").geom("geom1").nodeGroup("grp5").add("pol4");
    model.component("comp1").geom("geom1").nodeGroup("grp5").add("dif3");
    model.component("comp1").geom("geom1").nodeGroup("grp5").add("cha1");
    model.component("comp1").geom("geom1").run("cha1");
    model.component("comp1").geom("geom1").create("pol5", "Polygon");
    model.component("comp1").geom("geom1").feature("pol5").set("source", "table");
    model.component("comp1").geom("geom1").nodeGroup("grp5").remove("pol5", false);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", "7[mm]", 0, 0);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", "-shell.Ro*cos(shell.alpha_bp)", 0, 1);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", "7[mm]", 1, 0);
    model.component("comp1").geom("geom1").feature("pol5")
         .setIndex("table", "-shell.Ro*cos(shell.alpha_bp)-8[mm]", 1, 1);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", "11[mm]", 2, 0);
    model.component("comp1").geom("geom1").feature("pol5")
         .setIndex("table", "-shell.Ro*cos(shell.alpha_bp)-8[mm]", 2, 1);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", "12[mm]", 3, 0);
    model.component("comp1").geom("geom1").feature("pol5")
         .setIndex("table", "-shell.Ro*cos(shell.alpha_bp)-7[mm]", 3, 1);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", "12[mm]", 4, 0);
    model.component("comp1").geom("geom1").feature("pol5")
         .setIndex("table", "-shell.Ro*cos(shell.alpha_bp)-4[mm]", 4, 1);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", "11[mm]", 5, 0);
    model.component("comp1").geom("geom1").feature("pol5")
         .setIndex("table", "-shell.Ro*cos(shell.alpha_bp)-3[mm]", 5, 1);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", "11[mm]", 6, 0);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", "-shell.Ro*cos(shell.alpha_bp)", 6, 1);
    model.component("comp1").geom("geom1").run("pol5");
    model.component("comp1").geom("geom1").create("cha2", "Chamfer");
    model.component("comp1").geom("geom1").feature("cha2").selection("point").set("pol5", 1);
    model.component("comp1").geom("geom1").feature("cha2").set("dist", "0.3[mm]");
    model.component("comp1").geom("geom1").nodeGroup().create("grp6");
    model.component("comp1").geom("geom1").nodeGroup("grp6").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp6").add("pol5");
    model.component("comp1").geom("geom1").nodeGroup("grp6").add("cha2");
    model.component("comp1").geom("geom1").run("cha2");
    model.component("comp1").geom("geom1").create("uni4", "Union");
    model.component("comp1").geom("geom1").nodeGroup("grp6").remove("uni4", false);
    model.component("comp1").geom("geom1").feature("uni4").selection("input").set("cha1", "cha2", "fil5");
    model.component("comp1").geom("geom1").feature("uni4").set("selresult", true);
    model.component("comp1").geom("geom1").run("uni4");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("uni3", 2);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("uni3", 1);
    model.component("comp1").geom("geom1").nodeGroup().create("grp7");
    model.component("comp1").geom("geom1").nodeGroup("grp7").placeAfter(null);
    model.component("comp1").geom("geom1").nodeGroup("grp7").add("uni4");
    model.component("comp1").geom("geom1").nodeGroup("grp7").add("sel1");
    model.component("comp1").geom("geom1").nodeGroup("grp7").add("sel2");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("igv1", "IgnoreVertices");
    model.component("comp1").geom("geom1").feature("igv1").selection("input").set("fin", 16, 45);
    model.component("comp1").geom("geom1").run("igv1");

    model.title(null);

    model.description("");

    model.label("diaphragm_accumulator_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
