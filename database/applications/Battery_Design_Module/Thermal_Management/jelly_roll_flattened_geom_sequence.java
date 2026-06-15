/*
 * jelly_roll_flattened_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:24 by COMSOL 6.3.0.290. */
public class jelly_roll_flattened_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Thermal_Management");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("r_mandrel", "2[mm]", "\u82af\u8f74\u534a\u5f84");
    model.param().set("r_can", "8.5[mm]", "\u5305\u58f3\u5185\u534a\u5f84");
    model.param().set("D_sep", "30[um]", "\u9694\u819c\u539a\u5ea6");
    model.param().set("D_pos", "200[um]", "\u6b63\u6781\u539a\u5ea6");
    model.param().set("D_neg", "200[um]", "\u8d1f\u6781\u539a\u5ea6");
    model.param().set("D_cc_pos", "10[um]", "\u6b63\u6781\u96c6\u6d41\u4f53\u94dd\u7b94\u539a\u5ea6");
    model.param().set("D_cc_neg", "10[um]", "\u8d1f\u6781\u96c6\u6d41\u4f53\u94dd\u7b94\u539a\u5ea6");
    model.param().set("D_cell", "2*(D_neg+D_sep+D_pos)+D_cc_pos+D_cc_neg", "\u7535\u6c60\u5355\u5143\u539a\u5ea6");
    model.param().set("D_tab_neg", "100[um]", "\u6b63\u6781\u6781\u8033\u539a\u5ea6");
    model.param().set("D_tab_pos", "100[um]", "\u8d1f\u6781\u6781\u8033\u539a\u5ea6");
    model.param()
         .set("theta_start_pos", "2*pi*r_mandrel/D_cell", "\u6b63\u6781\u96c6\u6d41\u4f53\u94dd\u7b94\u7684\u8d77\u59cb\u89d2\u5ea6");
    model.param()
         .set("theta_end_pos", "2*pi*(r_can-(D_cc_pos+D_pos+D_sep+D_neg+D_cc_neg)-D_tab_pos-D_tab_neg)/D_cell", "\u6b63\u6781\u96c6\u6d41\u4f53\u94dd\u7b94\u7684\u7ec8\u6b62\u89d2\u5ea6");
    model.param()
         .set("L_cc_pos", "s_spiral(D_cell, theta_end_pos)-s_spiral(D_cell, theta_start_pos)", "\u6b63\u6781\u96c6\u6d41\u4f53\u94dd\u7b94\u7684\u957f\u5ea6");
    model.param()
         .set("L_unmatched_pos", "s_spiral(D_cell, theta_start_pos+2*pi)-s_spiral(D_cell, theta_start_pos)", "\uff08\u5355\u9762\uff09\u6b63\u6781\u957f\u5ea6\u4e0d\u5339\u914d");
    model.param()
         .set("theta_start_neg", "2*pi*(r_mandrel+D_tab_pos+D_neg+D_sep+D_pos+D_cc_pos)/D_cell", "\u8d1f\u6781\u96c6\u6d41\u4f53\u94dd\u7b94\u7684\u8d77\u59cb\u89d2\u5ea6");
    model.param()
         .set("theta_end_neg", "2*pi*(r_can-D_cc_neg-D_tab_neg)/D_cell", "\u8d1f\u6781\u96c6\u6d41\u4f53\u94dd\u7b94\u7684\u7ec8\u6b62\u89d2\u5ea6");
    model.param()
         .set("L_cc_neg", "s_spiral(D_cell, theta_end_neg)-s_spiral(D_cell, theta_start_neg)", "\u8d1f\u6781\u96c6\u6d41\u4f53\u94dd\u7b94\u7684\u957f\u5ea6");
    model.param()
         .set("L_unmatched_neg", "s_spiral(D_cell, theta_end_neg)-s_spiral(D_cell, theta_end_neg-2*pi)", "\uff08\u5355\u9762\uff09\u8d1f\u6781\u957f\u5ea6\u4e0d\u5339\u914d");
    model.param()
         .set("D_offset", "max(D_neg,D_pos)", "\u51e0\u4f55\u4e2d\u7684\u5c42\u95f4\u865a\u62df\u504f\u79fb\u91cf");
    model.param().set("L_tab", "4[mm]", "\u6781\u8033\u957f\u5ea6\uff08\u5bbd\u5ea6\uff09");
    model.param().set("H_jr", "60[mm]", "\u5706\u67f1\u5377\u7ed5\u5f0f\u7535\u6c60\u7684\u9ad8\u5ea6");
    model.param()
         .set("H_tab_in_jr", "2/3*H_jr", "\u5706\u67f1\u5377\u7ed5\u5f0f\u7535\u6c60\u4e2d\u7684\u6781\u8033\u9ad8\u5ea6");
    model.param()
         .set("H_tab_outside_jr", "5[mm]", "\u5706\u67f1\u5377\u7ed5\u5f0f\u7535\u6c60\u5916\u7684\u6781\u8033\u9ad8\u5ea6");

    model.func().create("an1", "Analytic");
    model.func("an1").label("\u89e3\u6790 - \u963f\u57fa\u7c73\u5fb7\u87ba\u65cb\u957f\u5ea6");
    model.func("an1").set("funcname", "s_spiral");
    model.func("an1").set("expr", "a/(2*pi)/2*(theta*sqrt(1+theta^2)+log(theta+sqrt(1+theta^2)))");
    model.func("an1").set("args", "a, theta");
    model.func("an1").set("fununit", "m");
    model.func("an1").setIndex("argunit", "m", 0);
    model.func("an1").setIndex("argunit", 1, 1);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"L_cc_pos", "D_cc_pos"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"L_cc_pos-L_unmatched_pos", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("size", "D_pos", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"L_unmatched_pos", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("pos", "-D_pos", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");

    model.component("comp1").view("view2").axis().set("viewscaletype", "manual");
    model.component("comp1").view("view2").axis().set("yscale", 25);

    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"L_cc_pos-L_unmatched_pos", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").setIndex("size", "D_sep/2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"L_unmatched_pos", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").setIndex("pos", "-D_pos-D_sep/2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("size", new String[]{"L_cc_pos", "D_pos"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("pos", new String[]{"0", "D_cc_pos"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5")
         .set("size", new String[]{"L_cc_pos", "D_sep/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5")
         .set("pos", new String[]{"0", "D_cc_pos+D_pos"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r5");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6")
         .set("size", new String[]{"L_cc_neg", "D_sep/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6")
         .set("pos", new String[]{"0", "D_cc_pos+D_pos+D_offset + D_sep/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r6");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7")
         .set("size", new String[]{"L_cc_neg", "D_neg"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7")
         .set("pos", new String[]{"0", "D_cc_pos+D_pos+D_offset + D_sep"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r7");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r8", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r8")
         .set("size", new String[]{"L_cc_neg", "D_cc_neg"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r8")
         .set("pos", new String[]{"0", "D_cc_pos+D_pos+D_offset +D_sep+D_neg"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r8");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r9", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r9")
         .set("size", new String[]{"L_cc_neg-L_unmatched_neg", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r9").setIndex("size", "D_neg", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r9")
         .set("pos", new String[]{"0", "D_cc_pos+D_pos+D_offset +D_sep+D_neg+D_cc_neg"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r9");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r10", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r10")
         .set("size", new String[]{"L_cc_neg-L_unmatched_neg", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r10").setIndex("size", "D_sep/2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r10")
         .set("pos", new String[]{"0", "D_cc_pos+D_pos+D_offset +D_sep+2*D_neg+D_cc_neg"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r10");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").label("\u8d1f\u6781\u96c6\u6d41\u4f53\u548c\u7535\u6781");
    model.component("comp1").geom("geom1").feature("ext1").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext1").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext1").selection("inputface").set("wp1", 5, 6, 7);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "H_jr", 0);
    model.component("comp1").geom("geom1").feature("ext1").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").feature().duplicate("ext2", "ext1");
    model.component("comp1").geom("geom1").feature("ext2").label("\u6b63\u6781\u96c6\u6d41\u4f53\u548c\u7535\u6781");
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").clear("wp1");
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").set("wp1", 1, 2, 10);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").feature().duplicate("ext3", "ext2");
    model.component("comp1").geom("geom1").feature("ext3").label("\u9694\u819c");
    model.component("comp1").geom("geom1").feature("ext3").selection("inputface").clear("wp1");
    model.component("comp1").geom("geom1").feature("ext3").selection("inputface").set("wp1", 8, 9, 3, 4);
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", "H_jr+H_tab_outside_jr");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new String[]{"L_tab", "D_tab_pos"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("pos", new String[]{"0", "-D_tab_pos"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").label("\u6b63\u6781\u8033");
    model.component("comp1").geom("geom1").feature("ext4").setIndex("distance", "H_tab_outside_jr+H_tab_in_jr", 0);
    model.component("comp1").geom("geom1").feature("ext4").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext4").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickz", "-H_tab_outside_jr");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("size", new String[]{"L_tab", "D_tab_neg"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("pos", new String[]{"L_cc_neg-L_tab", "0"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .setIndex("pos", "D_cc_pos+D_pos+D_offset +D_sep+D_neg+D_cc_neg", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("r1");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext5", "Extrude");
    model.component("comp1").geom("geom1").feature("ext5").label("\u8d1f\u6781\u8033");
    model.component("comp1").geom("geom1").feature("ext5").setIndex("distance", "H_tab_outside_jr+H_tab_in_jr", 0);
    model.component("comp1").geom("geom1").feature("ext5").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext5");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"ext1", "ext5"});
    model.component("comp1").geom("geom1").feature("unisel1").set("selshow", false);
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").named("unisel1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"ext2", "ext4"});
    model.component("comp1").geom("geom1").feature("unisel2").set("selshow", false);
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").named("unisel2");
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u8d1f\u6781\u96c6\u6d41\u4f53");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 10);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u6b63\u6781\u96c6\u6d41\u4f53");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 2);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u8d1f\u6781");
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"ext1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"sel1"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("difsel2", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel2").label("\u6b63\u6781");
    model.component("comp1").geom("geom1").feature("difsel2").set("add", new String[]{"ext2"});
    model.component("comp1").geom("geom1").feature("difsel2").set("subtract", new String[]{"sel2"});
    model.component("comp1").geom("geom1").run("difsel2");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3")
         .label("\u8d1f\u6781\u96c6\u6d41\u4f53\u548c\u6781\u8033");
    model.component("comp1").geom("geom1").feature("unisel3").set("input", new String[]{"ext5", "sel1"});
    model.component("comp1").geom("geom1").run("unisel3");
    model.component("comp1").geom("geom1").create("unisel4", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel4")
         .label("\u6b63\u6781\u96c6\u6d41\u4f53\u548c\u6781\u8033");
    model.component("comp1").geom("geom1").feature("unisel4").set("input", new String[]{"ext4", "sel2"});
    model.component("comp1").geom("geom1").run("unisel4");
    model.component("comp1").geom("geom1").create("unisel5", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel5").label("\u96c6\u6d41\u4f53\u548c\u6781\u8033");
    model.component("comp1").geom("geom1").feature("unisel5")
         .set("input", new String[]{"ext4", "ext5", "sel1", "sel2"});
    model.component("comp1").geom("geom1").run("unisel5");
    model.component("comp1").geom("geom1").create("unisel6", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel6").label("\u7535\u6781");
    model.component("comp1").geom("geom1").feature("unisel6").set("input", new String[]{"difsel1", "difsel2"});
    model.component("comp1").geom("geom1").run("unisel6");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u8d1f\u7535\u6d41\u7aef\u5b50");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 66);
    model.component("comp1").geom("geom1").feature().duplicate("sel4", "sel3");
    model.component("comp1").geom("geom1").runPre("sel4");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").clear("fin");
    model.component("comp1").geom("geom1").feature("sel4").label("\u6b63\u7535\u6d41\u7aef\u5b50");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("fin", 4);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u56db\u8fb9\u5f62\u7f51\u683c\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection")
         .set("fin", 6, 8, 9, 21, 58, 63, 65, 67);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("\u51b7\u5374\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("fin", 63, 69);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").label("\u76ee\u6807\u8fb9\u754c 1");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").set("fin", 31);
    model.component("comp1").geom("geom1").feature().duplicate("sel8", "sel7");
    model.component("comp1").geom("geom1").feature("sel8").label("\u76ee\u6807\u8fb9\u754c 2");
    model.component("comp1").geom("geom1").runPre("sel8");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").clear("fin");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").set("fin", 41);
    model.component("comp1").geom("geom1").run("sel8");
    model.component("comp1").geom("geom1").feature().duplicate("sel9", "sel8");
    model.component("comp1").geom("geom1").feature("sel9").label("\u6e90\u8fb9\u754c 1");
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").clear("fin");
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").set("fin", 29);
    model.component("comp1").geom("geom1").feature().duplicate("sel10", "sel9");
    model.component("comp1").geom("geom1").feature("sel10").label("\u6e90\u8fb9\u754c 2");
    model.component("comp1").geom("geom1").runPre("sel10");
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").clear("fin");
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").set("fin", 39);
    model.component("comp1").geom("geom1").run("sel10");
    model.component("comp1").geom("geom1").create("unisel7", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel7").label("\u62c9\u4f38\u548c\u8026\u5408\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("unisel7").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel7")
         .set("input", new String[]{"sel7", "sel8", "sel9", "sel10"});
    model.component("comp1").geom("geom1").run("unisel7");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmin", "-0.01*H_jr");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", "0.01*H_jr");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("boxsel1").set("selshow", false);
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel2", "boxsel1");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmin", "0.99*H_jr");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmax", "1.01*H_jr");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("unisel8", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel8").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel8").label("\u6620\u5c04\u7f51\u683c\u5206\u5e03\u8fb9 1");
    model.component("comp1").geom("geom1").feature("unisel8").set("input", new String[]{"boxsel1", "boxsel2"});
    model.component("comp1").geom("geom1").run("unisel8");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"unisel7"});
    model.component("comp1").geom("geom1").feature("adjsel1").set("outputdim", 1);
    model.component("comp1").geom("geom1").feature("adjsel1").set("selshow", false);
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("difsel3", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel3").label("\u6620\u5c04\u7f51\u683c\u5206\u5e03\u8fb9 2");
    model.component("comp1").geom("geom1").feature("difsel3").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("difsel3").set("add", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").feature("difsel3").set("subtract", new String[]{"unisel8"});
    model.component("comp1").geom("geom1").run("difsel3");
    model.component("comp1").geom("geom1").create("sel11", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel11").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel11").label("\u7f51\u683c\u5927\u5c0f\u8fb9");
    model.component("comp1").geom("geom1").feature("sel11").selection("selection").set("fin", 13, 123);

    model.title(null);

    model.description("");

    model.label("jelly_roll_flattened_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
