/*
 * perforated_muffler_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:25 by COMSOL 6.3.0.290. */
public class perforated_muffler_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Automotive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1")
         .set("semiaxes", new double[]{0.145, 0.05});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("e1").set("angle", 180);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 0.225, 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "0.225+0.128", 1);
    model.component("comp1").geom("geom1").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "0.225+0.128+0.098", 2);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "0.225+0.128+0.098+0.098", 3);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "0.225+0.128+0.098+0.098+0.171", 4);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0", "0", "0"});
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp2").set("quickx", -0.1);
    model.component("comp1").geom("geom1").feature("wp2").set("unite", false);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "28.5[mm]");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1")
         .set("pos", new String[]{"-22.5[mm]", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c2").set("r", "22.5[mm]");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c2").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c2")
         .set("pos", new String[]{"63[mm]", "0"});
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("ext2").selection("input").set("wp2.c1");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 0.1, 0);
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 0.128, 1);
    model.component("comp1").geom("geom1").feature("ext2").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext2").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext2").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 0.272, 2);
    model.component("comp1").geom("geom1").feature("ext2")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext2")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext2").set("twist", new String[]{"0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 0.325, 3);
    model.component("comp1").geom("geom1").feature("ext2")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext2")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext2").set("twist", new String[]{"0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 0.453, 4);
    model.component("comp1").geom("geom1").feature("ext2")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext2")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext2").set("twist", new String[]{"0", "0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 0.47, 5);
    model.component("comp1").geom("geom1").feature("ext2")
         .set("displ", new String[][]{{"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext2")
         .set("scale", new String[][]{{"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext2").set("twist", new String[]{"0", "0", "0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 0.542, 6);
    model.component("comp1").geom("geom1").feature("ext2")
         .set("displ", new String[][]{{"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext2")
         .set("scale", new String[][]{{"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext2")
         .set("twist", new String[]{"0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 0.551, 7);
    model.component("comp1").geom("geom1").feature("ext2")
         .set("displ", new String[][]{{"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext2")
         .set("scale", new String[][]{{"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext2")
         .set("twist", new String[]{"0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 0.564, 8);
    model.component("comp1").geom("geom1").feature("ext2")
         .set("displ", new String[][]{{"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext2")
         .set("scale", new String[][]{{"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext2")
         .set("twist", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("ext3").selection("input").set("wp2.c2");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", 0.1, 0);
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", 0.325, 1);
    model.component("comp1").geom("geom1").feature("ext3").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext3").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext3").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", 0.347, 2);
    model.component("comp1").geom("geom1").feature("ext3")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext3")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext3").set("twist", new String[]{"0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", 0.437, 3);
    model.component("comp1").geom("geom1").feature("ext3")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext3")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext3").set("twist", new String[]{"0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", 0.453, 4);
    model.component("comp1").geom("geom1").feature("ext3")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext3")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext3").set("twist", new String[]{"0", "0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", 0.551, 5);
    model.component("comp1").geom("geom1").feature("ext3")
         .set("displ", new String[][]{{"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext3")
         .set("scale", new String[][]{{"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext3").set("twist", new String[]{"0", "0", "0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", 0.649, 6);
    model.component("comp1").geom("geom1").feature("ext3")
         .set("displ", new String[][]{{"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext3")
         .set("scale", new String[][]{{"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext3")
         .set("twist", new String[]{"0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", 0.73, 7);
    model.component("comp1").geom("geom1").feature("ext3")
         .set("displ", new String[][]{{"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext3")
         .set("scale", new String[][]{{"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext3")
         .set("twist", new String[]{"0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").add("ext1", 18, 13, 8, 3, 5, 10, 15, 20);

    model.component("comp1").geom("geom1").feature("wp3").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp3").selection("face").set("ext1", 6);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1")
         .set("x", "-117[mm]  -137.5[mm]  -137.5[mm] -117[mm] -108[mm] -96.5[mm] -96.5[mm] -117[mm]");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1")
         .set("y", "-25[mm] -25[mm] -13.5[mm] -13.5[mm] -4.5[mm] -4.5[mm] -25[mm] -25[mm]");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("cha1", "Chamfer");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cha1").selection("pointinsketch")
         .set("pol1", 2, 7);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("cha1").set("dist", "2.5[mm]");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("cha1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol2").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol2")
         .set("x", "136.5[mm] 136.5[mm] 136.5[mm] 131[mm] 131[mm] 121[mm] 121[mm] 113.5[mm] 113.5[mm] 105.5[mm] 105.5[mm] 97.3[mm] 97.3[mm] 90[mm] 90[mm] 80.8[mm] 80.8[mm] 72.4[mm] 72.4[mm] 72.4[mm] 72.4[mm] 60[mm] 60[mm] 55[mm] 55[mm] 45.9[mm] 45.9[mm] 37[mm] 37[mm] 28[mm] 28[mm] 28[mm] 28[mm] 37[mm] 37[mm] 45.9[mm] 45.9[mm] 55[mm] 55[mm] 63.8[mm] 63.8[mm] 72.4[mm] 72.4[mm] 80.8[mm] 80.8[mm] 89[mm] 89[mm] 97.3[mm] 97.3[mm] 105.5[mm] 105.5[mm] 113.5[mm] 113.5[mm] 121[mm] 121[mm] 126[mm] 126[mm] 123.3[mm] 123.3[mm] 123.3[mm] 123.3[mm] 136.5[mm]");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol2")
         .set("y", "-25[mm] -20.5[mm] -20.5[mm] -12.2[mm] -12.2[mm] -4[mm] -4[mm] -0.1[mm] -0.1[mm] 3.3[mm] 3.3[mm] 6[mm] 6[mm] 8[mm] 8[mm] 10.3[mm] 10.3[mm] 12[mm] 12[mm] 12[mm] 12[mm] 14[mm] 14[mm] 15[mm] 15[mm] 15.7[mm] 15.7[mm] 16.8[mm] 16.8[mm] 17.5[mm] 17.5[mm] 12.5[mm] 12.5[mm] 11.8[mm] 11.8[mm] 10.7[mm] 10.7[mm] 10[mm] 10[mm] 8.5[mm] 8.5[mm] 7[mm] 7[mm] 5.3[mm] 5.3[mm] 3.5[mm] 3.5[mm] 1[mm] 1[mm] -1.7[mm] -1.7[mm] -5.1[mm] -5.1[mm] -9[mm] -9[mm] -12.2[mm] -12.2[mm] -20.5[mm] -20.5[mm] -25[mm] -25[mm] -25[mm]");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp4").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp4").selection("face").set("ext1", 11);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1")
         .set("x", "-137.5[mm] -137.5[mm] -135[mm] -117[mm] -108[mm] -96.5[mm] -90[mm] -81[mm] -69.5[mm] -63[mm] -60.5[mm] -63[mm] -63[mm] -69.5[mm] -69.5[mm] -96.5[mm] -117[mm] -137.5[mm]");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol1")
         .set("y", "-25[mm] -13.5[mm] -13.5[mm] -13.5[mm] -4.5[mm] -4.5[mm] -4.5[mm] 4.5[mm] 4.5[mm] 4.5[mm] 2[mm] -0.5[mm] -3.5[mm] -9[mm] -25[mm] -25[mm] -25[mm] -25[mm]");
    model.component("comp1").geom("geom1").feature("wp4").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol2").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol2")
         .set("x", "136.5[mm] 136.5[mm] 136.5[mm] 131[mm] 131[mm] 121[mm] 121[mm] 113.5[mm] 113.5[mm] 105.5[mm] 105.5[mm] 97.3[mm] 97.3[mm] 90[mm] 90[mm] 80.8[mm] 80.8[mm] 72.4[mm] 72.4[mm] 72.4[mm] 72.4[mm] 60[mm] 60[mm] 55[mm] 55[mm] 45.9[mm] 45.9[mm] 37[mm] 37[mm] 28[mm] 28[mm] 28[mm] 28[mm] 37[mm] 37[mm] 45.9[mm] 45.9[mm] 55[mm] 55[mm] 63.8[mm] 63.8[mm] 72.4[mm] 72.4[mm] 80.8[mm] 80.8[mm] 89[mm] 89[mm] 97.3[mm] 97.3[mm] 105.5[mm] 105.5[mm] 113.5[mm] 113.5[mm] 121[mm] 121[mm] 126[mm] 126[mm] 123.3[mm] 123.3[mm] 123.3[mm] 123.3[mm] 136.5[mm]");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pol2")
         .set("y", "-25[mm] -20.5[mm] -20.5[mm] -12.2[mm] -12.2[mm] -4[mm] -4[mm] -0.1[mm] -0.1[mm] 3.3[mm] 3.3[mm] 6[mm] 6[mm] 8[mm] 8[mm] 10.3[mm] 10.3[mm] 12[mm] 12[mm] 12[mm] 12[mm] 14[mm] 14[mm] 15[mm] 15[mm] 15.7[mm] 15.7[mm] 16.8[mm] 16.8[mm] 17.5[mm] 17.5[mm] 12.5[mm] 12.5[mm] 11.8[mm] 11.8[mm] 10.7[mm] 10.7[mm] 10[mm] 10[mm] 8.5[mm] 8.5[mm] 7[mm] 7[mm] 5.3[mm] 5.3[mm] 3.5[mm] 3.5[mm] 1[mm] 1[mm] -1.7[mm] -1.7[mm] -5.1[mm] -5.1[mm] -9[mm] -9[mm] -12.2[mm] -12.2[mm] -20.5[mm] -20.5[mm] -25[mm] -25[mm] -25[mm]");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("wp4");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", 0.098);
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("copy2", "Copy");
    model.component("comp1").geom("geom1").feature("copy2").selection("input").set("wp4");
    model.component("comp1").geom("geom1").feature("copy2").set("displx", "2*0.098");
    model.component("comp1").geom("geom1").run("copy2");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("copy2", 2);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ext2", "ext3");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input")
         .set("copy1", "del1", "ext1", "wp3", "wp4");
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("uni2");
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("uni1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 6);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 1);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u7a7f\u5b54\u7ba1");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 50, 52, 74, 76);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u5e26\u5957\u7b52\u7684\u7a7f\u5b54\u7ba1");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("fin", 26, 28);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u5165\u53e3\u6321\u677f\u7a7f\u5b54\u677f");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("fin", 43, 67, 91);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("\u51fa\u53e3\u6321\u677f\u7a7f\u5b54\u677f");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("fin", 36, 60, 84, 101);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").label("\u7ba1\u7aef");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").set("fin", 97, 108);
    model.component("comp1").geom("geom1").run("sel7");
    model.component("comp1").geom("geom1").create("sel8", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel8").label("\u51e0\u4f55");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").set("fin");
    model.component("comp1").geom("geom1").run("sel8");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"sel8"});
    model.component("comp1").geom("geom1").feature("adjsel1").set("exterior", false);
    model.component("comp1").geom("geom1").feature("adjsel1").set("interior", true);
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u58c1");
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").feature("difsel1")
         .set("subtract", new String[]{"sel3", "sel4", "sel5", "sel6", "sel7"});
    model.component("comp1").geom("geom1").feature("difsel1").label("\u5185\u90e8\u786c\u58c1");
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u5bf9\u79f0");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", 0.01);
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.title(null);

    model.description("");

    model.label("perforated_muffler_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
