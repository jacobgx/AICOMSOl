/*
 * jelly_roll_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:24 by COMSOL 6.3.0.290. */
public class jelly_roll_geom_sequence {

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
    model.param().set("L_tab", "5[mm]", "\u6781\u8033\u957f\u5ea6\uff08\u5bbd\u5ea6\uff09");
    model.param().set("H_jr", "60[mm]", "\u5706\u67f1\u5377\u7ed5\u5f0f\u7535\u6c60\u7684\u9ad8\u5ea6");
    model.param()
         .set("H_tab_in_jr", "2/3*H_jr", "\u5706\u67f1\u5377\u7ed5\u5f0f\u7535\u6c60\u4e2d\u7684\u6781\u8033\u9ad8\u5ea6");
    model.param()
         .set("H_tab_outside_jr", "5[mm]", "\u5706\u67f1\u5377\u7ed5\u5f0f\u7535\u6c60\u5916\u7684\u6781\u8033\u9ad8\u5ea6");
    model.param().set("r0", "r_mandrel+D_tab_pos", "\u87ba\u65cb\u8d77\u59cb\u534a\u5f84");
    model.param().set("w_tab_pos", "1.86", "\u56de\u8f6c\u89d2\u5ea6\uff0c\u6b63\u6781\u6781\u8033");
    model.param().set("w_tab_neg", "0.48", "\u56de\u8f6c\u89d2\u5ea6\uff0c\u8d1f\u6781\u6781\u8033");
    model.param().set("w_start_pos", "2*pi*r_mandrel/D_cell", "\u8d77\u59cb\u89d2\u5ea6\uff0c\u6b63\u6781 cc");
    model.param()
         .set("w_end_pos", "2*pi*(r_can-(D_cc_pos+D_pos+D_sep+D_neg+D_cc_neg)-D_tab_pos-D_tab_neg)/D_cell", "\u7ec8\u6b62\u89d2\u5ea6\uff0c\u6b63\u6781 cc");
    model.param().set("w_tot_ccs", "w_end_pos-w_start_pos", "\u56de\u8f6c\u89d2\u5ea6\uff0c\u96c6\u6d41\u4f53");

    model.func().create("an1", "Analytic");
    model.func("an1").label("\u89e3\u6790 - \u87ba\u65cb X");
    model.func("an1").set("funcname", "spiralX");
    model.func("an1").set("expr", "-(s/(2*pi)*D_cell+R)*sin(s)");
    model.func("an1").set("args", "s, R");
    model.func("an1").set("fununit", "m");
    model.func("an1").setIndex("argunit", 1, 0);
    model.func("an1").setIndex("argunit", "m", 1);
    model.func().duplicate("an2", "an1");
    model.func("an2").label("\u89e3\u6790 - \u87ba\u65cb Y");
    model.func("an2").set("funcname", "spiralY");
    model.func("an2").set("expr", "-(s/(2*pi)*D_cell+R)*cos(s)");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc1").set("parmax", "w_tot_ccs");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc1")
         .set("coord", new String[]{"spiralX(s,r0)", ""});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc1")
         .setIndex("coord", "spiralY(s,r0)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc1").set("rtol", 1.0E-9);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc1").set("maxknots", 10000);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pc1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("pc2", "pc1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc2")
         .setIndex("coord", "spiralX(s,r0+D_cc_pos)", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc2")
         .setIndex("coord", "spiralY(s,r0+D_cc_pos)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pc2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("pc3", "pc2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc3")
         .setIndex("coord", "spiralX(s,r0+D_cc_pos+D_pos)", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc3")
         .setIndex("coord", "spiralY(s,r0+D_cc_pos+D_pos)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pc3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("pc4", "pc3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc4")
         .setIndex("coord", "spiralX(s,r0+D_cc_pos+D_pos+D_sep/2)", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc4")
         .setIndex("coord", "spiralY(s,r0+D_cc_pos+D_pos+D_sep/2)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pc4");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("pc5", "pc4");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc5").set("parmin", "2*pi");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc5")
         .setIndex("coord", "spiralX(s,r0-D_pos-D_sep/2)", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc5")
         .setIndex("coord", "spiralY(s,r0-D_pos-D_sep/2)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pc5");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("pc6", "pc5");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc6")
         .setIndex("coord", "spiralX(s,r0-D_pos)", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc6")
         .setIndex("coord", "spiralY(s,r0-D_pos)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pc6");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").selection("vertex1").set("pc1", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").selection("vertex2").set("pc4", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2").selection("vertex1").set("pc5", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2")
         .set("coord2", new String[]{"0", "-(r0+D_cell)"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls3").selection("vertex1").set("pc4", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls3").selection("vertex2").set("pc5", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pt1")
         .setIndex("p", "spiralX(w_tab_pos,r0)", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pt1")
         .setIndex("p", "spiralY(w_tab_pos,r0)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pt1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("csol1").selection("input")
         .set("ls1", "ls2", "ls3", "pc1", "pc2", "pc3", "pc4", "pc5", "pc6", "pt1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("csol1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").label("\u6b63\u6781\u57df");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "H_jr", 0);
    model.component("comp1").geom("geom1").feature("ext1").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", "H_jr+H_tab_outside_jr");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pc1").set("parmax", "w_tab_pos");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pc1")
         .set("coord", new String[]{"spiralX(s,r0)", ""});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pc1")
         .setIndex("coord", "spiralY(s,r0)", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pc1").set("rtol", 1.0E-9);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pc1").set("maxknots", 10000);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pc1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature().duplicate("pc2", "pc1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pc2")
         .setIndex("coord", "spiralX(s,r0-D_tab_pos)", 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pc2")
         .setIndex("coord", "spiralY(s,r0-D_tab_pos)", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pc2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls1").selection("vertex1").set("pc1", 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls1").selection("vertex2").set("pc2", 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls2").selection("vertex1").set("pc1", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls2").selection("vertex2").set("pc2", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("ls2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("csol1").selection("input")
         .set("ls1", "ls2", "pc1", "pc2");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("csol1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").label("\u6b63\u6781\u8033");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "H_tab_outside_jr+H_tab_in_jr", 0);
    model.component("comp1").geom("geom1").feature("ext2").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext2").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ext1", "ext2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pc1").set("parmax", "w_tot_ccs");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pc1")
         .set("coord", new String[]{"spiralX(s,r0+D_cc_pos+D_pos+D_sep/2)", ""});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pc1")
         .setIndex("coord", "spiralY(s,r0+D_cc_pos+D_pos+D_sep/2)", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pc1").set("rtol", 1.0E-9);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pc1").set("maxknots", 10000);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("pc1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature().duplicate("pc2", "pc1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pc2")
         .setIndex("coord", "spiralX(s,r0+D_cc_pos+D_pos+D_sep)", 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pc2")
         .setIndex("coord", "spiralY(s,r0+D_cc_pos+D_pos+D_sep)", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("pc2");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature().duplicate("pc3", "pc2");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pc3")
         .setIndex("coord", "spiralX(s,r0+D_cc_pos+D_pos+D_sep+D_neg)", 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pc3")
         .setIndex("coord", "spiralY(s,r0+D_cc_pos+D_pos+D_sep+D_neg)", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("pc3");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature().duplicate("pc4", "pc3");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pc4")
         .setIndex("coord", "spiralX(s,r0+D_cc_pos+D_pos+D_sep+D_neg+D_cc_neg)", 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pc4")
         .setIndex("coord", "spiralY(s,r0+D_cc_pos+D_pos+D_sep+D_neg+D_cc_neg)", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("pc4");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature().duplicate("pc5", "pc4");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pc5").set("parmax", "w_tot_ccs-2*pi");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pc5")
         .setIndex("coord", "spiralX(s,r0+D_cc_pos+D_pos+D_sep+D_neg+D_cc_neg+D_neg)", 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pc5")
         .setIndex("coord", "spiralY(s,r0+D_cc_pos+D_pos+D_sep+D_neg+D_cc_neg+D_neg)", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("pc5");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature().duplicate("pc6", "pc5");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pc6")
         .setIndex("coord", "spiralX(s,r0+D_cc_pos+D_pos+D_sep+D_neg+D_cc_neg+D_neg+D_sep/2)", 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pc6")
         .setIndex("coord", "spiralY(s,r0+D_cc_pos+D_pos+D_sep+D_neg+D_cc_neg+D_neg+D_sep/2)", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("pc6");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls1").selection("vertex1").set("pc1", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls1").selection("vertex2").set("pc6", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls2").selection("vertex1").set("pc4", 2);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls2").selection("vertex2").set("pc1", 2);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("ls2");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls3").selection("vertex1").set("pc6", 2);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls3").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls3")
         .set("coord2", new String[]{"spiralX(w_tot_ccs-2*pi,r0+D_cc_pos+D_pos+D_sep+D_neg+D_cc_neg)", "0"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls3")
         .setIndex("coord2", "spiralY(w_tot_ccs-2*pi,r0+D_cc_pos+D_pos+D_sep+D_neg+D_cc_neg)", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("ls3");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pt1")
         .setIndex("p", "spiralX(w_tot_ccs-w_tab_neg,r0+D_cc_pos+D_pos+D_sep+D_neg+D_cc_neg)", 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pt1")
         .setIndex("p", "spiralY(w_tot_ccs-w_tab_neg,r0+D_cc_pos+D_pos+D_sep+D_neg+D_cc_neg)", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("pt1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("csol1").selection("input")
         .set("ls1", "ls2", "ls3", "pc1", "pc2", "pc3", "pc4", "pc5", "pc6", "pt1");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("csol1");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").label("\u8d1f\u6781\u57df");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "H_jr", 0);
    model.component("comp1").geom("geom1").feature("ext3").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("quickz", "-H_tab_outside_jr");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pc1").set("parmax", "w_tab_pos");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pc1").set("parmin", "w_tot_ccs-w_tab_neg");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pc1").set("parmax", "w_tot_ccs");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pc1")
         .set("coord", new String[]{"spiralX(s,r0+D_cc_pos+D_pos+D_sep+D_neg+D_cc_neg+D_tab_neg)", ""});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pc1")
         .setIndex("coord", "spiralY(s,r0+D_cc_pos+D_pos+D_sep+D_neg+D_cc_neg+D_tab_neg)", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pc1").set("rtol", 1.0E-9);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pc1").set("maxknots", 10000);
    model.component("comp1").geom("geom1").feature("wp4").geom().run("pc1");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature().duplicate("pc2", "pc1");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pc2")
         .setIndex("coord", "spiralX(s,r0+D_cc_pos+D_pos+D_sep+D_neg+D_cc_neg)", 0);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pc2")
         .setIndex("coord", "spiralY(s,r0+D_cc_pos+D_pos+D_sep+D_neg+D_cc_neg)", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().run("pc2");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("ls1").selection("vertex1").set("pc1", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("ls1").selection("vertex2").set("pc2", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("ls2").selection("vertex1").set("pc1", 2);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("ls2").selection("vertex2").set("pc2", 2);
    model.component("comp1").geom("geom1").feature("wp4").geom().run("ls2");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("csol1").selection("input")
         .set("ls1", "ls2", "pc1", "pc2");
    model.component("comp1").geom("geom1").feature("wp4").geom().run("csol1");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature().create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").label("\u8d1f\u6781\u8033");
    model.component("comp1").geom("geom1").feature("ext4").setIndex("distance", "H_tab_outside_jr+H_tab_in_jr", 0);
    model.component("comp1").geom("geom1").feature("ext4").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("ext3", "ext4");
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u9694\u819c");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("uni1", 4, 5);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("uni2", 1, 5);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u8d1f\u6781");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("uni2", 2, 4);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u6b63\u6781");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("uni1", 3, 6);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u8d1f\u6781\u96c6\u6d41\u4f53");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("uni2", 3);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u6b63\u6781\u96c6\u6d41\u4f53");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("uni1", 1);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel6").label("\u8d1f\u7535\u6d41\u7aef\u5b50");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("uni2", 24);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").label("\u6b63\u7535\u6d41\u7aef\u5b50");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").set("uni1", 9);
    model.component("comp1").geom("geom1").run("sel7");
    model.component("comp1").geom("geom1").create("sel8", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel8").label("\u51b7\u5374\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").set("uni2", 25, 27, 29);
    model.component("comp1").geom("geom1").run("sel8");
    model.component("comp1").geom("geom1").create("sel9", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel9").label("\u521d\u59cb\u7f51\u683c\u9762");
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").set("uni1", 1, 2, 5, 7);
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").set("uni2", 23, 25, 26, 27);
    model.component("comp1").geom("geom1").run("sel9");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("uni1", "uni2");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp5").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp5").selection("face").set("rot1(1)", 24);
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("rot1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", "-1[um]");
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", "1[um]");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", "-(r_can-D_neg-D_sep/2)");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", 0);
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("boxsel1").set("selshow", false);
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel2", "boxsel1");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymin", "r_mandrel+D_cell-D_cc_pos/2+D_tab_pos");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymax", Double.POSITIVE_INFINITY);
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"boxsel1", "boxsel2"});
    model.component("comp1").geom("geom1").feature("unisel1").set("selshow", false);
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("mcf1", "MeshControlFaces");
    model.component("comp1").geom("geom1").feature("mcf1").selection("input").named("unisel1");
    model.component("comp1").geom("geom1").run("mcf1");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").set("mcf1", 48, 126);
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"ext2", "sel5"});
    model.component("comp1").geom("geom1").feature("unisel2")
         .label("\u6b63\u6781\u96c6\u6d41\u4f53\u548c\u6781\u8033");
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3")
         .label("\u8d1f\u6781\u96c6\u6d41\u4f53\u548c\u6781\u8033");
    model.component("comp1").geom("geom1").feature("unisel3").set("input", new String[]{"ext4", "sel4"});
    model.component("comp1").geom("geom1").run("unisel3");
    model.component("comp1").geom("geom1").create("unisel4", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel4").label("\u96c6\u6d41\u4f53\u548c\u6781\u8033");
    model.component("comp1").geom("geom1").feature("unisel4").set("input", new String[]{"unisel2", "unisel3"});
    model.component("comp1").geom("geom1").run("unisel4");
    model.component("comp1").geom("geom1").create("unisel5", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel5").label("\u7535\u6781");
    model.component("comp1").geom("geom1").feature("unisel5").set("input", new String[]{"sel2", "sel3"});
    model.component("comp1").geom("geom1").run("unisel5");
    model.component("comp1").geom("geom1").create("unisel6", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel6").label("\u6781\u8033");
    model.component("comp1").geom("geom1").feature("unisel6").set("input", new String[]{"ext2", "ext4"});

    model.title(null);

    model.description("");

    model.label("jelly_roll_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
